package library.dto.bookmark;

import library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewBookmarkBasicInfoDto {

    private Long id;
    private Long userId;
    private Long bookId;
}
