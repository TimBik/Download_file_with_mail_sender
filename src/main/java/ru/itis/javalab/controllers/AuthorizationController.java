package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.State;
import ru.itis.javalab.services.SignInService;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthorizationController {
    @Autowired
    private SignInService signInService;

    @RequestMapping(value = "signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signIn");
        return modelAndView;
    }

    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public String signIn(@ModelAttribute(name = "userObj") UserDto user, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Optional<UserDto> optionalUserDto = signInService.signIn(user);
        if (optionalUserDto.isPresent()) {
            UserDto userDto = optionalUserDto.get();
            if (userDto.getState().equals(State.CONFIRMED)) {
                session.setAttribute("current_user", userDto);
                return "redirect:" + req.getScheme() + "://localhost:8080/file-load";
            }
            //поменять на сообщение о не активированном аккаунте
            return "redirect:" + req.getScheme() + "://signIn";
        }
        return "redirect:" + req.getScheme() + "://signIn";
    }
}
