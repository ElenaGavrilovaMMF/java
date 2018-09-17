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

public class CommentDao {

    private static final CommentDao INSTANCE = new CommentDao();

    private static final String FIND_ALL =
                    "SELECT " +
                    "b.id AS book_id, " +
                    "u.id AS user_id," +
                    "u.name AS user_name," +
                    "cb.id AS comment_id," +
                    "cb.text," +
                    "cb.date " +
                    "FROM online_library.book b " +
                    "INNER JOIN online_library.book_user bu "+
                    "ON b.id = bu.book_id INNER JOIN online_library.user u " +
                    "ON u.id = bu.user_id INNER JOIN online_library.comment_book cb "+
                    "ON cb.book_id=b.id";


    private static final String FIND_BY_ID_BOOK = FIND_ALL + " WHERE b.id=?";

    private static final String SAVE_COMMENT =
            "INSERT INTO online_library.comment_book (book_id, user_id, text, date) " +
                    "VALUES (?, ?, ?, (SELECT CURRENT_DATE))";



    public Comment save(Comment comment) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_COMMENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, comment.getBookId().getId());
            preparedStatement.setLong(2, comment.getUserId().getId());
            preparedStatement.setString(3, comment.getText());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                comment.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comment;
    }

    public List<Comment> findAllCommentId(Long bookId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_BOOK)) {
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = getCommentFromResultSet(resultSet);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }

    private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
        return Comment.builder()
                .id(resultSet.getLong("comment_id"))
                .bookId(Book.builder()
                        .id(resultSet.getLong("book_id"))
                        .build())
                .userId(User.builder()
                        .id(resultSet.getLong("user_id"))
                        .name(resultSet.getString("user_name")).build())
                .text(resultSet.getString("text"))
                .date(LocalDateFormat.format(String.valueOf(resultSet.getDate("date"))))
                .build();
    }


    public static CommentDao getInstance() {
        return INSTANCE;
    }

}
