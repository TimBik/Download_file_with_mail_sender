package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where user.confirmCode = :code group by user.id")
    User findByConfirmCode(@Param("code") String code);

    @Query("select user from User user where user.login = :login group by user.id")
    User findByLogin(@Param("login") String login);

    @Query("select user from User user where user.id = :id group by user.id")
    User find(@Param("id") Long id);
}
