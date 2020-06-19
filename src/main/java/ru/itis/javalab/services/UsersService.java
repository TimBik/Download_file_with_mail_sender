package ru.itis.javalab.services;

import ru.itis.javalab.model.User;

public interface UsersService {

    public boolean checkUser(String login);


    public User findUserByLogin(String login);

    public boolean checkPassword(String password1, String password2);

    User find(Long id);
}
