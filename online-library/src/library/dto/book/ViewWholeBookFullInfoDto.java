package library.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 1 on 11.03.2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewWholeBookFullInfoDto {

    private Long id;
    private String name;
    private String nameUser;
    private String description;
    private String date;
    private boolean bookWhole;
    private String genreName;
    private String url;

}
