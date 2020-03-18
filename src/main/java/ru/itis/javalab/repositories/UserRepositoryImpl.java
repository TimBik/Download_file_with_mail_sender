package ru.itis.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.javalab.model.State;
import ru.itis.javalab.model.User;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UsersRepository {
    private String SQL_SELECT_BY_CODE = "select * from users where code=?";

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .confirmCode(row.getString("code"))
                    .login(row.getString("login"))
                    .mail(row.getString("mail"))
                    .password(null)
                    .state(State.valueOf(row.getString("state")))
                    .build();

    @Override
    public User findByConfirmCode(String code) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_CODE, new Object[]{code}, userRowMapper);
            return user;
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    //language=sql
    private static final String SQL_INSERT =
            "INSERT INTO users (login, password, mail, state, code) values (?,?,?,?,?)";

    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getMail());
            statement.setString(4, entity.getState().toString());
            statement.setString(5, entity.getConfirmCode());
            return statement;
        }, keyHolder);
        entity.setId((Long) keyHolder.getKey());
    }

    @Override
    public void delete(Long aLong) {

    }

    private final String SQL_UPDATE_STATE = "UPDATE users SET state = ? WHERE code=?";

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE_STATE
                , new Object[]{entity.getState().toString(), entity.getConfirmCode()});
    }
}
