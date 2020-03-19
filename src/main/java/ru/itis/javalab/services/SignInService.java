package ru.itis.javalab.services;


import ru.itis.javalab.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface SignInService {
    Optional<UserDto> signIn(UserDto userDto);
}
