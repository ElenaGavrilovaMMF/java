package exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The class {@code FileException} and its subclasses are a form of
 * {@code Exception} that indicates conditions that a reasonable
 * application might want to catch.
 * <p>
 * <p>The class {@code FileException} and any subclasses that are not also
 * subclasses of {@link RuntimeException} are <em>checked
 * exceptions</em>.  Checked exceptions need to be declared in a
 * method or constructor's {@code throws} clause if they can be thrown
 * by the execution of the method or constructor and propagate outside
 * the method or constructor boundary.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FileException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public FileException(final String message) {
        super(message);
    }
}
