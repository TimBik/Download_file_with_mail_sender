package ru.itis.javalab.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.Optional;


@Component(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        return new UserDetailsImpl(user);
    }
}
