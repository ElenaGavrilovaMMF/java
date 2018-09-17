package library.service;

import library.dao.BanDao;
import library.dao.ChapterDao;
import library.dto.ban.CreateBanDto;
import library.dto.ban.ViewBanDto;
import library.dto.bookmark.ViewBookmarkFullInfoDto;
import library.dto.chapter.CreateNewChapterDto;
import library.dto.chapter.ViewChapterBasicInfoDto;
import library.dto.chapter.ViewChapterFullInfoDto;
import library.entity.Ban;
import library.entity.Book;
import library.entity.Chapter;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BanService {

    private static final BanService INSTANCE = new BanService();

//    public List<ViewChapterBasicInfoDto> findAll(Long bookId) {
//        return ChapterDao.getInstance().findAllBook(bookId).stream()
//                .map(it -> new ViewChapterBasicInfoDto(it.getId(), it.getName()))
//                .collect(Collectors.toList());
//    }
//
    public ViewBanDto findById(Long bookId) {
        return BanDao.getInstance().findByBookId(bookId)
                .map(it -> ViewBanDto.builder()
                        .bookId(it.getBookId())
                        .date(LocalDateFormat.format(it.getDate()))
                        .build())
                .orElse(null);
    }

    public ViewBanDto deleteBan(Long bookId) {
        return BanDao.getInstance().deleteBan(bookId)
                .map(it -> ViewBanDto.builder()
                        .bookId(it.getBookId())
                        .date(LocalDateFormat.format(it.getDate()))
                        .build())
                .orElse(null);
    }

    public CreateBanDto save(CreateBanDto dto) {
        Ban savedBan = BanDao.getInstance().save(
                Ban.builder()
                        .bookId(dto.getBookId())
                        .build());

        return new CreateBanDto(savedBan.getBookId());
    }

    public static BanService getInstance() {
        return INSTANCE;
    }
}
