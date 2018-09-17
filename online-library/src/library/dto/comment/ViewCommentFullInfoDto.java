package library.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by 1 on 11.03.2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewCommentFullInfoDto {

    private Long id;
    private String nameUser;
    private String date;
    private String text;

}
