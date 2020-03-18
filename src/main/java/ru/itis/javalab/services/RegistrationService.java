package ru.itis.javalab.services;

import ru.itis.javalab.dto.SignUpDto;
import ru.itis.javalab.dto.UserDto;


public interface RegistrationService {
    UserDto loadUserFromParameters(SignUpDto params);
    void chageStateAccept(String url);
}
