package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.UserDto;

import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

@Controller
public class FilesController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/file-load", method = RequestMethod.GET)
    public ModelAndView loadFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("file-load");
        return modelAndView;
    }

    @Autowired
    private FileService fileService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile multipartFile, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserDto userDto = UserDto.from(userDetails.getUser());
        if (multipartFile != null) {
            fileService.uploadFile(multipartFile, userDto);
        }
        return null;
    }

    // localhost:8080/files/123809183093qsdas09df8af.jpeg

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/files/{file-name:.+}", method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        File file = fileService.findFile(fileName);
        //вынести в отдел метод
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        try {
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            //LOG.info("Error writing file to output stream. Filename was '{}'" + fileName, e);
            throw new RuntimeException("IOError writing file to output stream");
        }
        return null;
    }
}
