package ru.itis.javalab.repositories;

import ru.itis.javalab.model.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Long, User> {
    User findByConfirmCode(String code);

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}
