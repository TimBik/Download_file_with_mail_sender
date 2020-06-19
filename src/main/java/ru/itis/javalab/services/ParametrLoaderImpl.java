package ru.itis.javalab.services;

import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.SignUpDto;

import java.util.Map;

@Component
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
