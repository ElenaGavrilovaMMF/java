package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.Ship;

/**
 * Class for logging File.
 */
public final class ShipLogger {

    /**
     * Logger constant.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(ShipLogger.class);

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private ShipLogger() {
    }

    /**
     * The method writes information about the error to the log file.
     *
     * @param message Error message.
     */
    public static void logError(final String message) {
        LOGGER.error(message);
    }

    /**
     * The method writes information about the info to the log file.
     *
     * @param ship info about ship.
     */
    public static void logInfo(final Ship ship) {
        LOGGER.info(ship.toString() + "has arrived.");
    }

    /**
     * The method writes information about the info to the log file.
     *
     * @param message info message.
     */
    public static void logInfo(final String message) {
        LOGGER.info(message);
    }
}
