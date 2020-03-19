package ru.itis.javalab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserService userService;

    @Override
    public Optional<UserDto> signIn(UserDto userDto) {
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        User user = userService.findUserByLogin(login);
        if (userService.checkPassword(password, user.getPassword())) {
            return Optional.of(UserDto.from(user));
        }
        return Optional.empty();
    }
}
