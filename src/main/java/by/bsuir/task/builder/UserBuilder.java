package by.bsuir.task.builder;

import by.bsuir.task.entity.Role;
import by.bsuir.task.entity.User;
import by.bsuir.task.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    @Override
    public User build(ResultSet resultSet) throws RepositoryException {
        try {
            Integer id = resultSet.getInt(ID);
            String login = resultSet.getString(USERNAME);
            String password = resultSet.getString(PASSWORD);
            Role role = Role.valueOf(resultSet.getString(ROLE).toUpperCase());

            return new User(id, login, password, role);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
