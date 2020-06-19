package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.dto.SignUpDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.Role;
import ru.itis.javalab.model.State;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.UUID;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PrepareMailModel prepareMailModel;

    @Override
    public UserDto loadUserFromParameters(SignUpDto userData) {
        User user = User.builder()
                .mail(userData.getMail())
                .hashPaswword(passwordEncoder.encode(userData.getPassword()))
                .state(State.NOT_CONFIRMED)
                .role(Role.USER)
                .confirmCode(UUID.randomUUID().toString())
                .login(userData.getLogin())
                .build();

        usersRepository.save(user);

        mailService.sendMessage("Confirm", user.getMail(), prepareMailModel.prepareMailModel(user.getConfirmCode(), user.getLogin()), "mail.ftl");

        return UserDto.from(user);
    }

    @Override
    @Transactional
    public void chageStateAccept(String code) {
        User user = usersRepository.findByConfirmCode(code);
        user.setState(State.CONFIRMED);
    }
}
