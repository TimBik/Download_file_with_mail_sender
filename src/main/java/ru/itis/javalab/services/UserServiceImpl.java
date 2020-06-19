package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UsersService {

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
        return userRepository.findByLogin(login);
    }

    @Override
    public boolean checkPassword(String password1, String password2) {
        return encoder.matches(password1, password2);
    }

    @Override
    public User find(Long id) {
        return userRepository.find(id);
    }


}
