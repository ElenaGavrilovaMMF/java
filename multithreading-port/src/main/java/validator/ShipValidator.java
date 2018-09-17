package validator;

import exception.ShipException;
import log.ShipLogger;
import entity.Ship;

/**
 * Class-validator for the existence of a ship.
 */
public final class ShipValidator {

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private ShipValidator() {
    }

    /**
     * The method takes {@code Ship} and checks its existence.
     * If the current volume of the ship is greater than
     * the maximum volume of the ship, then an exception
     * {@code ShipException} is thrown.
     * If the loading volume is greater than the maximum,
     * {@code ShipException} an exception is thrown.
     * If the unloaded volume volume is greater than the
     * current volume, then the exception {@code ShipException}
     * is thrown.
     *
     * @param ship ship that is being checked.
     */
    public static void validate(final Ship ship) {
        if (ship.getVolumeCurrent() > ship.getVolumeMax()) {
            try {
                throw new ShipException();
            } catch (ShipException e) {
                ShipLogger.logError(e.getMessageVolumeCurrentMax());
            }
        }
        if (ship.getVolumeLoading() > ship.getVolumeMax()) {
            try {
                throw new ShipException();
            } catch (ShipException e) {
                ShipLogger.logError(e.getMessageVolumeLoadingMax());
            }
        }
        if (ship.getVolumeUnloading() > ship.getVolumeCurrent()) {
            try {
                throw new ShipException();
            } catch (ShipException e) {
                ShipLogger.logError(e.getMessageVolumeUnloadingCurrent());
            }
        }
    }
}
