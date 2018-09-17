package library.service;

import library.dao.CommentDao;
import library.dto.comment.CreateNewCommentDto;
import library.dto.comment.ViewCommentBasicInfoDto;
import library.dto.comment.ViewCommentFullInfoDto;
import library.entity.Book;
import library.entity.Comment;
import library.entity.User;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentService {

    private static final CommentService INSTANCE = new CommentService();


    public ViewCommentBasicInfoDto save(CreateNewCommentDto dto) {
        Comment savedComment = CommentDao.getInstance().save(
                Comment.builder()
                        .bookId(Book.builder().id(dto.getBookId()).build())
                        .userId(User.builder().id(dto.getUserId()).build())
                        .text(dto.getText())
                        .build());
        return new ViewCommentBasicInfoDto(savedComment.getId(), savedComment.getText());
    }

    public List<ViewCommentFullInfoDto> findAllBookId(Long bookId) {
        return CommentDao.getInstance().findAllCommentId(bookId).stream()
                .map(it -> new ViewCommentFullInfoDto(it.getId(), it.getUserId().getName(),LocalDateFormat.format(it.getDate()),it.getText()))
                .collect(Collectors.toList());
    }

    public static CommentService getInstance() {
        return INSTANCE;
    }
}
