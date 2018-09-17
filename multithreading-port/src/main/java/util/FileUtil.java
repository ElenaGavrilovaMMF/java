package util;

import exception.FileException;
import log.FileLogger;
import validator.FileValidator;
import validator.ShipDataValidator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class {@code FileUtil} has methods for
 * reading data from a file.
 */
public final class FileUtil {

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private FileUtil() {
    }

    /**
     * The method reads data from a file.
     * Returns a list of data rows.
     * If the file is empty, then the exception
     * {@code FileException}is thrown.
     *
     * @param path finding a file.
     * @return {@code List<String>} list of data rows.
     */
    public static List<String> readFile(final String path)  {
        final List<String> result = new ArrayList<String>();
            FileValidator.validate(path);
            try {
                Files.lines(Paths.get(path), StandardCharsets.UTF_8)
                        .map(ShipDataValidator::validate)
                        .filter(Objects::nonNull)
                        .forEach(result::add);
                FileLogger.logInfo("The file has been successfully read.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (result.isEmpty()) {
                try {
                    throw new FileException("The file is empty");
                } catch (FileException e) {
                    FileLogger.logError(e.getMessage());
                }
            }
        return result;
    }
}
