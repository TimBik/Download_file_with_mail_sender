package ru.itis.javalab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long id;
    private String login;
    private String password;
    private String mail;
    private State state;
    private String confirmCode;
}
