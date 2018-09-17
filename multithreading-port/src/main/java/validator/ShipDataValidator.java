package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class-validator initial data for creating objects.
 */
public final class ShipDataValidator {

    /**
     * Regular expression, for filtering the data of ships.
     */
    private static final String REGEX =
            "[\\w\\d]+ (\\d+\\.\\d+ ){2}(load|unload|load-unload)"
                    + "( \\d+\\.\\d+){2}";
    /**
     * A pattern that compiles a regular expression.
     */
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private ShipDataValidator() {
    }

    /**
     * The method checks the string for a regular expression.
     *
     * @param data information about the ship.
     * @return {@code String} a string that matches
     * a regular expression.
     */
    public static String validate(final String data) {
            final Matcher matcher = PATTERN.matcher(data);
            String result = null;
            if (matcher.matches()) {
                result = data;
            }
            return result;
    }
}
