package ru.itis.javalab.services;


import ru.itis.javalab.dto.UserDto;

import java.util.Optional;

public interface SignInService {
    Optional<UserDto> signIn(UserDto userDto);
}
