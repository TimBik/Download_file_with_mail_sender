package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.javalab.dto.SignUpDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.model.State;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.UUID;

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
                .password(passwordEncoder.encode(userData.getPassword()))
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .login(userData.getLogin())
                .build();

        usersRepository.save(user);

        mailService.sendMessage("Confirm", user.getMail(), prepareMailModel.prepareMailModel(user.getConfirmCode(), user.getLogin()));

        return UserDto.from(user);
    }

    @Override
    public void chageStateAccept(String code) {
        User user = usersRepository.findByConfirmCode(code);
        if (user != null) {
            user.setState(State.CONFIRMED);
            usersRepository.update(user);
        }
    }
}
