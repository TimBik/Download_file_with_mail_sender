package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.model.Document;
import ru.itis.javalab.model.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.FileService;
import ru.itis.javalab.services.UsersService;

import java.util.List;

@Controller
public class ImageFilesController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "myImages", method = RequestMethod.GET)
    public ModelAndView findFiles(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myImages");
        User user = usersService.find(((UserDetailsImpl) authentication.getPrincipal()).getUser().getId());
        List<Document> documents = user.getDocuments();
        modelAndView.addObject("images", documents);
        return modelAndView;
    }

}
