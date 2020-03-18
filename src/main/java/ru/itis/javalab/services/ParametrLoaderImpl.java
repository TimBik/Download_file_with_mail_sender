package ru.itis.javalab.services;

import ru.itis.javalab.dto.SignUpDto;

import java.util.Map;

public class ParametrLoaderImpl implements ParametrLoader {

    @Override
    public SignUpDto getRegistrationParams(Map map) {
        return SignUpDto.builder()
                .mail((String) map.get("email"))
                .login((String) map.get("login"))
                .password((String) map.get("password"))
                .build();
    }

}
