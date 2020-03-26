package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.services.RegistrationService;

@Controller
public class ConfirmConroller {

    @Autowired
    RegistrationService registrationService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/confirm/{code}", method = RequestMethod.GET)
    public ModelAndView updateStep(@PathVariable("code") String code) {
        registrationService.chageStateAccept(code);
        return null;
    }
}
