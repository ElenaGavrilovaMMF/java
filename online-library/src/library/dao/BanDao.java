package library.dao;

import library.connection.ConnectionPool;
import library.entity.Ban;
import library.entity.Book;
import library.entity.Chapter;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class BanDao {

    private static final BanDao INSTANCE = new BanDao();

    private static final String FIND_ALL =
            "SELECT " +
                    "b.book_id AS book_id, " +
                    "b.date " +
                    "FROM online_library.ban b ";


    private static final String SAVE =
            "INSERT INTO online_library.ban (book_id, date) " +
                    "VALUES (?, (SELECT CURRENT_DATE))";

    private static final String FIND_BY_ID = FIND_ALL + " WHERE b.book_id= ?";

    private static final String DELETE = "DELETE FROM online_library.ban WHERE book_id=?";


//    private static final String FIND_BY_ID_BOOK = FIND_ALL + " WHERE c.book_id= ?";


    public Ban save(Ban ban) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setLong(1, ban.getBookId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ban;
    }

    public Optional<Ban> deleteBan(Long bookId) {
        Optional<Ban> ban = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ban = Optional.of(getBanFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ban;
    }


    public Optional<Ban> findByBookId(Long bookId) {
        Optional<Ban> ban = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ban = Optional.of(getBanFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ban;
    }

    private Ban getBanFromResultSet(ResultSet resultSet) throws SQLException {
        return Ban.builder()
                .bookId(resultSet.getLong("book_id"))
                .date(LocalDateFormat.format(String.valueOf(resultSet.getDate("date"))))
                .build();
    }

    public static BanDao getInstance() {
        return INSTANCE;
    }
}
