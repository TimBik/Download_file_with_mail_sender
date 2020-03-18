package ru.itis.javalab.repositories;

import ru.itis.javalab.model.User;

public interface UsersRepository extends CrudRepository<Long, User> {
    User findByConfirmCode(String code);
}
