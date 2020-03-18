package ru.itis.javalab.services;


import ru.itis.javalab.dto.SignUpDto;

import java.util.Map;

public interface ParametrLoader {
    public SignUpDto getRegistrationParams(Map map);
}
