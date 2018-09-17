package library.service;

import library.dao.GenreDao;
import library.entity.Genre;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreService {
    private static final GenreService INSTANCE = new GenreService();


    public Genre save(Genre dto) {
        Genre savedGenre = GenreDao.getInstance().save(
                Genre.builder()
                        .name(dto.getName())
                        .build());
        return new Genre(savedGenre.getId(), savedGenre.getName());
    }

    public List<Genre> findAll() {
        return GenreDao.getInstance().findAll();
    }

    public static GenreService getInstance() {
        return INSTANCE;
    }
}
