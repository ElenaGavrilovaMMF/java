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

public class BookmarkDao {

    private static final BookmarkDao INSTANCE = new BookmarkDao();

    private static final String FIND_ALL =
            "SELECT " +
                    "b.id AS book_id, " +
                    "b.name AS book_name, " +
                    "g.id AS genre_id, " +
                    "bm.id AS bookmark_id, " +
                    "g.name AS genre_name, " +
                    "b.whole_book, " +
                    "b.description, " +
                    "b.date, " +
                    "b.url, " +
                    "u.name AS user_name, " +
                    "u.id AS user_id " +
                    "FROM online_library.book b " +
                    "INNER JOIN online_library.book_user bu " +
                    "ON b.id = bu.book_id INNER JOIN online_library.user u " +
                    "ON u.id = bu.user_id INNER JOIN online_library.genre g " +
                    "ON b.genre_id=g.id INNER JOIN online_library.bookmark bm "+
                    "ON bm.book_id = b.id";


    private static final String FIND_BY_ID_USER = FIND_ALL + " WHERE bm.user_id= ?";

    private static final String SAVE_BOOKMARK =
            "INSERT INTO online_library.bookmark (book_id, user_id) " +
                    "VALUES (?, ?)";

    private static final String DELETE = "DELETE FROM online_library.bookmark WHERE book_id=?";



    public Bookmark save(Bookmark bookmark) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_BOOKMARK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, bookmark.getBookId().getId());
            preparedStatement.setLong(2, bookmark.getUserId().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                bookmark.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookmark;
    }

    public Optional<Bookmark> deleteMark(Long bookId) {
        Optional<Bookmark> bookmark = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bookmark = Optional.of(getBookmarkFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookmark;
    }


    public List<Bookmark> findAllUserId(Long userId) {
        List<Bookmark> bookmarks = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_USER)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bookmark bookmark = getBookmarkFromResultSet(resultSet);
                bookmarks.add(bookmark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookmarks;
    }


    private Bookmark getBookmarkFromResultSet(ResultSet resultSet) throws SQLException {
        return Bookmark.builder()
                .id(resultSet.getLong("bookmark_id"))
                .bookId(Book.builder()
                        .id(resultSet.getLong("book_id"))
                        .build())
                .userId(User.builder()
                        .id(resultSet.getLong("user_id"))
                        .name(resultSet.getString("user_name")).build())
                .build();
    }


    public static BookmarkDao getInstance() {
        return INSTANCE;
    }

}
