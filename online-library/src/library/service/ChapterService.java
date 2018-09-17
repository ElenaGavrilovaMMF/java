package library.service;

import library.dao.BanDao;
import library.dao.BookDao;

import library.dao.ChapterDao;
import library.dto.ban.ViewBanDto;
import library.dto.book.CreateNewBookDto;
import library.dto.book.ViewBookBasicInfoDto;

import library.dto.chapter.CreateNewChapterDto;
import library.dto.chapter.ViewChapterBasicInfoDto;
import library.dto.chapter.ViewChapterFullInfoDto;
import library.entity.Book;
import library.entity.Chapter;
import library.entity.Genre;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChapterService {

    private static final ChapterService INSTANCE = new ChapterService();

    public List<ViewChapterBasicInfoDto> findAll(Long bookId) {
        return ChapterDao.getInstance().findAllBook(bookId).stream()
                .map(it -> new ViewChapterBasicInfoDto(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    public ViewChapterFullInfoDto findById(Long chapterId) {
        return ChapterDao.getInstance().findById(chapterId)
                .map(it -> ViewChapterFullInfoDto.builder()
                        .id(it.getId())
                        .name(it.getName())
                        .url(it.getUrl())
                        .date(LocalDateFormat.format(it.getDate()))
                        .build())
                .orElse(null);
    }

    public ViewChapterBasicInfoDto deleteChapter(Long chapterId) {
        return ChapterDao.getInstance().deleteChapter(chapterId)
                .map(it -> ViewChapterBasicInfoDto.builder()
                        .id(it.getId())
                        .name(it.getName())
                        .build())
                .orElse(null);
    }

    public ViewChapterBasicInfoDto save(CreateNewChapterDto dto) {
        Chapter savedChapter = ChapterDao.getInstance().save(
                Chapter.builder()
                        .name(dto.getName())
                        .url(dto.getUrl())
                        .book(Book.builder().id(dto.getBookId()).build())
                        .build());

        return new ViewChapterBasicInfoDto(savedChapter.getId(), savedChapter.getName());
    }

    public static ChapterService getInstance() {
        return INSTANCE;
    }
}
