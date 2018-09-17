package validator;

import exception.FileException;


import java.io.File;

/**
 * Class-validator for the existence of a file.
 */
public final class FileValidator {

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private FileValidator() {
    }

    /**
     * The method will verify the existence of
     * the file on this path.
     * If such a file does not exist, then exception
     * {@code FileException} is thrown.
     *
     * @param pathFile path file.
     */
    public static void validate(final String pathFile) {
        final File file = new File(pathFile);
        if (!file.exists()) {
            try {
                throw new FileException("The file does not exist.");
            } catch (FileException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
