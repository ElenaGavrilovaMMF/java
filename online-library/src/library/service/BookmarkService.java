package library.service;

import library.dao.BookDao;
import library.dao.BookmarkDao;
import library.dao.ChapterDao;
import library.dao.CommentDao;
import library.dto.book.CreateNewBookDto;
import library.dto.book.ViewBookBasicInfoDto;
import library.dto.book.ViewBookFullInfoDto;
import library.dto.book.ViewBookTextDto;
import library.dto.bookmark.CreateNewBookmarkDto;
import library.dto.bookmark.ViewBookmarkBasicInfoDto;
import library.dto.bookmark.ViewBookmarkFullInfoDto;
import library.dto.chapter.ViewChapterBasicInfoDto;
import library.dto.comment.CreateNewCommentDto;
import library.dto.comment.ViewCommentBasicInfoDto;
import library.entity.*;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkService {

    private static final BookmarkService INSTANCE = new BookmarkService();

    public ViewBookmarkBasicInfoDto save(CreateNewBookmarkDto dto) {
        Bookmark savedBookmark = BookmarkDao.getInstance().save(
                        Bookmark.builder()
                                .bookId(Book.builder().id(dto.getBookId()).build())
                                .userId(User.builder().id(dto.getIdUser()).build())
                                .build());
        return new ViewBookmarkBasicInfoDto(savedBookmark.getId(), savedBookmark.getUserId().getId(),savedBookmark.getBookId().getId());
    }

    public ViewBookmarkBasicInfoDto deleteMark(Long bookId) {
        return BookmarkDao.getInstance().deleteMark(bookId)
                .map(it -> ViewBookmarkBasicInfoDto.builder()
                        .id(it.getId())
                        .userId(it.getUserId().getId())
                        .bookId(it.getBookId().getId())
                        .build())
                .orElse(null);
    }


    public List<ViewBookmarkFullInfoDto> findBookUserId(Long userId) {
        return BookmarkDao.getInstance().findAllUserId(userId).stream()
                .map(it -> new ViewBookmarkFullInfoDto(it.getId(), it.getUserId().getId(), BookService.getInstance().findById(it.getBookId().getId())))
                .collect(Collectors.toList());
    }
//    public List<ViewBookmarkFullInfoDto> findBookmarkUserId(Long userId) {
//        return BookmarkDao.getInstance().findAllUserId(userId).stream()
//                .map(it -> new ViewBookmarkFullInfoDto(it.getId(), it.getUserId().getId(),BookService.getInstance().findById(it.getBookId().getId()))
//                .collect(Collectors.toList());
//    }

    public static BookmarkService getInstance() {
        return INSTANCE;
    }
}
