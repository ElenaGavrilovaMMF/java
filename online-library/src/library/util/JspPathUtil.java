package library.util;

import library.dao.BookDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by 1 on 11.03.2018.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JspPathUtil {

    private static final String FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String get(String jspName) {
        return String.format(FORMAT, jspName);
    }

}
