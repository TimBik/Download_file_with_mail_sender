package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.User;
import ru.itis.javalab.services.FileService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FilesController {

    @RequestMapping(value = "/file-load", method = RequestMethod.GET)
    public ModelAndView loadFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("file-load");
        return modelAndView;
    }

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session) {
        if (session.getAttribute("current_user") != null) {
            fileService.uploadFile(multipartFile);
        }
        return null;
    }

    // localhost:8080/files/123809183093qsdas09df8af.jpeg

    @RequestMapping(value = "/files/{file-name:.+}", method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("file-name") String fileName, HttpSession session, HttpServletResponse response) {
        // TODO: найти на диске
        UserDto user = (UserDto) session.getAttribute("current_user");
        if (user != null) {
            File file = fileService.findFile(fileName);
            // TODO: отдать пользователю
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");
            try {
                Files.copy(file.toPath(), response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                //LOG.info("Error writing file to output stream. Filename was '{}'" + fileName, e);
                throw new RuntimeException("IOError writing file to output stream");
            }
        }
        return null;
    }
}
