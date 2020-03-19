package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.model.State;
import ru.itis.javalab.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String login;
    private String password;
    private State state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getMail())
                .login(user.getLogin())
                .password(user.getPassword())
                .state(user.getState())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

}
