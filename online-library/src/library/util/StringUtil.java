package library.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    private static final String EMPTY = "";

    public static boolean isEmpty(String value) {
        return value == null || EMPTY.equals(value);
    }
}
