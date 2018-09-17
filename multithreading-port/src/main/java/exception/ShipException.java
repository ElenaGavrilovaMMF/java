
package exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The class {@code ShipException} and its subclasses are a form of
 * {@code Exception} that indicates conditions that a reasonable
 * application might want to catch.
 * <p>
 * <p>The class {@code ShipException} and any subclasses that are not also
 * subclasses of {@link RuntimeException} are <em>checked
 * exceptions</em>.  Checked exceptions need to be declared in a
 * method or constructor's {@code throws} clause if they can be thrown
 * by the execution of the method or constructor and propagate outside
 * the method or constructor boundary.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ShipException extends Exception {

    /**
     * The method returns an error message when the
     * current volume of the ship is greater than the maximum.
     *
     * @return {@code String} message.
     */
    public final String getMessageVolumeCurrentMax() {
        return "Such a ship does not exist. "
                + "The maximum volume is less than the current one.";
    }

    /**
     * The method returns an error message when the
     * loading volume of the ship is greater than the maximum.
     *
     * @return {@code String} message.
     */
    public final String getMessageVolumeLoadingMax() {
        return "Such a ship does not exist. "
                + "The maximum volume is less than the load.";
    }

    /**
     * The method returns an error message when the
     * unloading volume of the ship is greater than the current.
     *
     * @return {@code String} message.
     */
    public final String getMessageVolumeUnloadingCurrent() {
        return "Such a ship does not exist. "
                + "The current volume is less than the unload.";
    }
}
