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

public class ChapterDao {

    private static final ChapterDao INSTANCE = new ChapterDao();

    private static final String FIND_ALL =
            "SELECT " +
                    "c.id AS chapter_id, " +
                    "b.id AS book_id, " +
                    "c.name AS chapter_name, " +
                    "c.date AS chapter_date, " +
                    "c.url AS chapter_url, " +
                    "b.name AS book_name, " +
                    "b.description, " +
                    "b.date, " +
                    "b.url " +
                    "FROM online_library.chapter c " +
                    "INNER JOIN online_library.book b " +
                    "ON c.book_id = b.id";


    private static final String SAVE =
            "INSERT INTO online_library.chapter (name, date, url, book_id) " +
                    "VALUES (?, (SELECT CURRENT_DATE), ?, ?)";

    private static final String FIND_BY_ID = FIND_ALL + " WHERE c.id= ?";

    private static final String FIND_BY_ID_BOOK = FIND_ALL + " WHERE c.book_id= ?";

    private static final String DELETE = "DELETE FROM online_library.chapter WHERE id=?";


    public Chapter save(Chapter chapter) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, chapter.getName());
            preparedStatement.setString(2, chapter.getUrl());
            preparedStatement.setLong(3, chapter.getBook().getId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                chapter.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapter;
    }

    public Optional<Chapter> deleteChapter(Long chapterId) {
        Optional<Chapter> chapter = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, chapterId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chapter = Optional.of(getChapterFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapter;
    }


    public List<Chapter> findAll() {
        List<Chapter> chapters = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Chapter chapter = getChapterFromResultSet(resultSet);
                chapters.add(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapters;
    }

    public List<Chapter> findAllBook(Long bookId) {
        List<Chapter> chapters = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_BOOK)) {
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Chapter chapter = getChapterFromResultSet(resultSet);
                chapters.add(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapters;
    }


    public Optional<Chapter> findById(Long chapterId) {
        Optional<Chapter> chapter = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, chapterId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chapter = Optional.of(getChapterFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapter;
    }

    private Chapter getChapterFromResultSet(ResultSet resultSet) throws SQLException {
        return Chapter.builder()
                .id(resultSet.getLong("chapter_id"))
                .name(resultSet.getString("chapter_name"))
                .date(LocalDateFormat.format(String.valueOf(resultSet.getDate("date"))))
                .url(resultSet.getString("chapter_url"))
                .book(Book.builder()
                    .id(resultSet.getLong("book_id"))
                    .name(resultSet.getString("book_name"))
                    .description(resultSet.getString("description"))
                    .date(LocalDateFormat.format(String.valueOf(resultSet.getDate("date"))))
                    .url(resultSet.getString("url"))
                     .build())
                .build();
    }

    public static ChapterDao getInstance() {
        return INSTANCE;
    }
}
