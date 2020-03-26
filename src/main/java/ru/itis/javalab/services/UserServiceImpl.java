package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public boolean checkUser(String login) {
        return userRepository.findByLogin(login) != null;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login).get();
    }

    @Override
    public boolean checkPassword(String password1, String password2) {
        return encoder.matches(password1, password2);
    }
}
