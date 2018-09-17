package library.dao;

import library.connection.ConnectionPool;
import library.entity.Book;
import library.entity.Genre;
import library.entity.Role;
import library.entity.User;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE =
            "INSERT INTO online_library.user (login, password, name, role_id) " +
                    "VALUES (?, ?, ?, 1)";

    private static final String FIND_ALL =
            "SELECT \n" +
                    "u.id AS user_id, \n" +
                    "u.name AS user_name, \n" +
                    "u.role_id AS role_id, \n" +
                    "r.name AS role_name, \n" +
                    "u.login, \n" +
                    "u.password\n" +
                    "FROM online_library.user u \n" +
                    "INNER JOIN online_library.role r ON u.role_id = r.id" ;

    private static final String FIND_BY_ID = FIND_ALL + " WHERE u.id = ?";

    private static final String FIND_BY_LOGIN = FIND_ALL + " WHERE u.login = ?";



    public User save(User user) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                User user = getBookFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Optional<User> findById(Long userId) {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Optional<User> findByLogin(String login) {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getBookFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("user_id"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("user_name"))
                .role(Role.builder()
                        .id(resultSet.getInt("role_id"))
                        .name(resultSet.getString("role_name"))
                        .build())
                .build();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
