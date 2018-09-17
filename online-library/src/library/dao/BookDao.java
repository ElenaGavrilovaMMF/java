package library.dao;

import library.connection.ConnectionPool;
import library.entity.Book;
import library.entity.Chapter;
import library.entity.Genre;
import library.entity.User;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class BookDao {

    private static final BookDao INSTANCE = new BookDao();

    private static final String FIND_ALL_USER =
            "SELECT " +
                    "b.id AS book_id, " +
                    "b.name AS book_name, " +
                    "g.id AS genre_id, " +
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
                    "ON b.genre_id=g.id";

    private static final String SAVE_BOOK =
            "INSERT INTO online_library.book (name, whole_book, description, date,url,genre_id) " +
                    "VALUES (?, ?, ?, (SELECT CURRENT_DATE), ?, ?)";

    private static final String SAVE_BOOK_USER =
            "INSERT INTO online_library.book_user (book_id, user_id) " +
                    "VALUES (?, ?)";

    private static final String FIND_BY_ID = FIND_ALL_USER + " WHERE b.id = ?";

    private static final String FIND_BY_GENRE = FIND_ALL_USER + " WHERE g.id= ?";

    private static final String FIND_BY_NAME = FIND_ALL_USER + " WHERE b.name= ?";

    private static final String FIND_BY_USER = FIND_ALL_USER + " WHERE u.name= ?";

    private static final String FIND_BY_USER_ID = FIND_ALL_USER + " WHERE user_id= ?";

    private static final String FIND_BY_USER_GENRE = FIND_ALL_USER + " WHERE u.name= ? AND g.id=?";

    private static final String FIND_BY_BOOK_GENRE = FIND_ALL_USER + " WHERE b.name= ? AND g.id=?";

    private static String COUNT_BOOK ="SELECT COUNT(*) AS count FROM online_library.book";

    private static String FIND_BY_BOOK_PAGE ="SELECT * FROM (SELECT \n" +
            "b.id AS book_id,\n" +
            "b.name AS book_name,\n" +
            "g.id AS genre_id,\n" +
            "g.name AS genre_name,\n" +
            "b.whole_book,\n" +
            "b.description, \n" +
            "b.date,\n" +
            "b.url,\n" +
            "u.name AS user_name, \n" +
            "u.id AS user_id\n" +
            " FROM online_library.book b \n" +
            " INNER JOIN online_library.book_user bu\n" +
            " ON b.id = bu.book_id INNER JOIN online_library.user u \n" +
            " ON u.id = bu.user_id INNER JOIN online_library.genre g \n" +
            " ON b.genre_id=g.id) AS book_result \n" +
            " OFFSET ? LIMIT 10";

//    private static String FIND_BY_BOOK_SHEATCH_USER_GENRE =FIND_BY_USER_GENRE+" OFFSET ? LIMIT 10";
//
//    private static String FIND_BY_BOOK_SHEATCH_BOOK_GENRE =FIND_BY_BOOK_GENRE+" OFFSET ? LIMIT 10";
//
//    private static String FIND_BY_BOOK_SHEATCH_USER =FIND_BY_USER+" OFFSET ? LIMIT 10";
//
//    private static String FIND_BY_BOOK_SHEATCH_GENRE =FIND_BY_GENRE+" OFFSET ? LIMIT 10";
//
//    private static String FIND_BY_BOOK_SHEATCH_BOOK =FIND_BY_NAME+" OFFSET ? LIMIT 10";
//


    public Book save(Book book) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setBoolean(2, book.getBookWhole());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setString(4, book.getUrl());
            preparedStatement.setInt(5,book.getGenre().getId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public void saveBook_User(Long idBook, Long idUser) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_BOOK_USER)) {
            preparedStatement.setLong(1, idBook);
            preparedStatement.setLong(2, idUser);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USER);
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    public List<Book> findAllPage(int countPage) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_BOOK_PAGE)) {
            preparedStatement.setInt(1, countPage*10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
//
//    public List<Book> findAllPageGenre(int genreId,int countPage) {
//        List<Book> books = new ArrayList<>();
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_BOOK_SHEATCH_GENRE)) {
//            preparedStatement.setInt(1,genreId);
//            preparedStatement.setInt(2, countPage*10);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Book book = getBookFromResultSet(resultSet);
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return books;
//    }
//    public List<Book> findAllPageUser(int countPage) {
//        List<Book> books = new ArrayList<>();
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_BOOK_SHEATCH_USER)) {
//            preparedStatement.setInt(1, countPage*10);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Book book = getBookFromResultSet(resultSet);
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return books;
//    }
//    public List<Book> findAllNameBook(int countPage) {
//        List<Book> books = new ArrayList<>();
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_BOOK_SHEATCH_BOOK)) {
//            preparedStatement.setInt(1, countPage*10);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Book book = getBookFromResultSet(resultSet);
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return books;
//    }
//    public List<Book> findAllPageGenreUser(int countPage) {
//        List<Book> books = new ArrayList<>();
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_BOOK_SHEATCH_USER_GENRE)) {
//            preparedStatement.setInt(1, countPage*10);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Book book = getBookFromResultSet(resultSet);
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return books;
//    }
//    public List<Book> findAllPageGenreBook(int countPage) {
//        List<Book> books = new ArrayList<>();
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_BOOK_SHEATCH_BOOK_GENRE)) {
//            preparedStatement.setInt(1, countPage*10);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Book book = getBookFromResultSet(resultSet);
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return books;
//    }



    public int countBook() {
        int count=0;
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT_BOOK);
            while (resultSet.next()) {
                count = getCountFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

//    public int countBook() {
//        int count=0;
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_BOOK)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            count = resultSet.getInt("count");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }

    public List<Book> findAllBookUser() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USER);
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Optional<Book> findById(Long bookId) {
        Optional<Book> book = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = Optional.of(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }


    public List<Book> findByIdGenre(int genreId) {
            List<Book> books = new ArrayList<>();
            try (Connection connection = ConnectionPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_GENRE)) {
                preparedStatement.setInt(1, genreId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Book book = getBookFromResultSet(resultSet);
                    books.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return books;
        }

    public List<Book> findByName(String nameBook) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, nameBook);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> findByUser(String nameUser) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER)) {
            preparedStatement.setString(1, nameUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> findBy(int genreId, String nameUser) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_GENRE)) {
            preparedStatement.setString(1, nameUser);
            preparedStatement.setInt(2, genreId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<Book> findByBookGenre(String nameBook, int genreId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_BOOK_GENRE)) {
            preparedStatement.setString(1, nameBook);
            preparedStatement.setInt(2, genreId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    public Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        return Book.builder()
                .id(resultSet.getLong("book_id"))
                .name(resultSet.getString("book_name"))
                .idUser(resultSet.getLong("user_id"))
                .bookWhole(resultSet.getBoolean("whole_book"))
                .description(resultSet.getString("description"))
                .date(LocalDateFormat.format(String.valueOf(resultSet.getDate("date"))))
                .url(resultSet.getString("url"))
                .genre(Genre.builder()
                        .id(resultSet.getInt("genre_id"))
                        .name(resultSet.getString("genre_name"))
                        .build())
                .build();
    }
    public int getCountFromResultSet(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("count");
    }
    public static BookDao getInstance() {
        return INSTANCE;
    }

    public List<Book> findBookUserId(Long userId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
