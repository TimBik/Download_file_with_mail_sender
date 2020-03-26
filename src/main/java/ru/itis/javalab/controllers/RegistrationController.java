package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.SignUpDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.ParametrLoader;
import ru.itis.javalab.services.RegistrationService;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (authentication != null) {
            modelAndView.setViewName("redirect:/file-load");
        } else {
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute(name = "userObj") SignUpDto signUpData) {
        registrationService.loadUserFromParameters(signUpData);
        return null;
    }
}
