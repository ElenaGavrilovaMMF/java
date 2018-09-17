package library.dao;

import library.connection.ConnectionPool;
import library.entity.Comment;
import library.entity.Genre;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreDao {

    private static final GenreDao INSTANCE = new GenreDao();

    private static final String FIND_ALL = "SELECT id, name FROM online_library_repository.online_library.genre";

    private static final String SAVE = "INSERT INTO online_library.genre (name) VALUES (?)";

    public Genre save(Genre genre) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, genre.getName());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                genre.setId(generatedKeys.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genre;
    }


    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Genre genre = Genre.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

    public static GenreDao getInstance() {
        return INSTANCE;
    }
}
