package library.dao;

import library.connection.ConnectionPool;
import library.entity.*;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDao {

    private static final RoleDao INSTANCE = new RoleDao();


    private static final String FIND_ALL = "SELECT id, name FROM online_library_repository.online_library.role r";

    private static final String FIND_BY_ID= FIND_ALL + " WHERE r.id= ?";

    private static final String UPDATE = "UPDATE online_library.user SET role_id=? WHERE id=?";


    public User save(Long userId, int roleId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, roleId);
            preparedStatement.setLong(2, userId);


            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return User.builder()
                .id(userId)
                .role(Role.builder()
                        .id(roleId)
                        .build())
                .build();
    }

    public Optional<Role> findById(int roleId) {
        Optional<Role> role = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, roleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = Optional.of(getRoleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }


    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Role role = Role.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    private Role getRoleFromResultSet(ResultSet resultSet) throws SQLException {
        return Role.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    public static RoleDao getInstance() {
        return INSTANCE;
    }
}
