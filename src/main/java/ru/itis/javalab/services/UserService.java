package ru.itis.javalab.services;


import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.User;

import java.util.Optional;

public interface UserService {
    boolean checkUser(String login);

    boolean checkPassword(String password1, String password2);

    User findUserByLogin(String login);
}
