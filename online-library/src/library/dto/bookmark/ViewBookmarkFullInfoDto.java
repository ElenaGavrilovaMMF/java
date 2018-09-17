package library.dto.bookmark;

import library.dto.book.ViewBookFullInfoDto;
import library.entity.Book;
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
public class ViewBookmarkFullInfoDto {

    private Long id;
    private Long userId;
    private ViewBookFullInfoDto book;

}
