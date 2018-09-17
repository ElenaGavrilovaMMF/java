package library.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.util.resources.LocaleData;

import java.util.Locale;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewBookDto {

    private Long id;
    private String name;
    private Long idUser;
    private Boolean bookWhole;
    private String description;
    private String url;
    private int genreId;

}

