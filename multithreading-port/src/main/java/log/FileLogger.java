package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for logging File.
 */
public final class FileLogger {
    /**
     * Logger constant.
     */
    private static final Logger LOGGER = LogManager.getLogger(FileLogger.class);

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private FileLogger() {
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
     * @param message info message.
     */
    public static void logInfo(final String message) {
        LOGGER.info(message);
    }
}
