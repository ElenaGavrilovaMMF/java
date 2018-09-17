package library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private Long id;
    private String name;
    private Long idUser;
    private Boolean bookWhole;
    private String description;
    private LocalDate date;
    private String url;
    private Genre genre;
}
