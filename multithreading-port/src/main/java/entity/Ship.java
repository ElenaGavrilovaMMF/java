package entity;

import exception.ShipRunException;
import log.ShipLogger;
import enam.Purpose;
import enam.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * The {@code Ship} represents the ship object.
 * Implements {@code Callable<String>} interface.
 * Object type Ship contains fields, such as:
 * {@code Date}, {@code String}, {@code Double},
 * {@code Purpose}, {@code Store} end {@code State}.
 * <p>
 * In addition, the class provides several methods
 * for loading or unloading a ship.
 * And also methods for displaying information about
 * the ship and equals two ships.
 * <p>
 * The {@code Callable} interface allows us to
 * override the call() method, which calculates the result.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ship implements Callable<String> {
    /**
     * The maximum number of attempts given to the ship
     * in order to be serviced, if this can not be done
     * from the first time.
     */
    private static final int MAX_ATTEMPTS = 5;
    /**
     * A variable that stores the number of attempts
     * the ship has made in order to serve it.
     */
    private int attemptsCurrent = 0;

    /**
     * Time and date of arrival of the ship on the berth.
     */
    private Date time;
    /**
     * Ship name.
     */
    private String name;
    /**
     * The maximum volume of the ship.
     */
    private Double volumeMax;
    /**
     * Current volume of the ship.
     * How much it is now loaded.
     */
    private Double volumeCurrent;
    /**
     * The purpose of arrival at the berth:
     * Load, Unload, Load and Unload.
     */
    private Purpose purpose;
    /**
     * The volume that the ship needs to load.
     */
    private Double volumeLoading;
    /**
     * The volume that the ship needs to unload.
     */
    private Double volumeUnloading;
    /**
     * The store to which the ship is sailing.
     */
//    private Store store;
//    /**
//     * Ship Status: done, not done, partially done.
//     */
    private State state;

    /**
     * Initializes the newly created {@code Ship} object.
     *
     * @param nameShip            a {@code String}
     * @param volumeMaxShip       a {@code Double}
     * @param volumeCurrentShip   a {@code Double}
     * @param purposeShip         a {@code Purpose}
     * @param volumeLoad   a {@code Double}
     * @param volumeUnload a {@code Double}
     * @param stateShip           a {@code State}
     */
    public Ship(final String nameShip, final double volumeMaxShip,
                final double volumeCurrentShip, final Purpose purposeShip,
                final double volumeLoad,
                final double volumeUnload, final State stateShip) {
        name = nameShip;
        volumeMax = volumeMaxShip;
        volumeCurrent = volumeCurrentShip;
        purpose = purposeShip;
        volumeLoading = volumeLoad;
        volumeUnloading = volumeUnload;
        state = stateShip;
    }

    /**
     * The overridden method of the {@code Callable} interface.
     * Returns the result of the flow.
     *
     * @return a {@code String} information about the ship.
     */
    @Override
    public final String call() {
        try {
            Store.getInstance().getBerth().getSem().acquire();
            time = new Date();
            ShipLogger.logInfo(this);
            if (purpose.equals(Purpose.LOAD)) {
                load();
            } else if (purpose.equals(Purpose.UNLOAD)) {
                unload();
            } else if (purpose.equals(Purpose.LOAD_UNLOAD)) {
                unload();
                load();
                if (volumeUnloading != 0.0 || volumeLoading != 0.0) {
                    state = State.PARTIALLY_DONE;
                }
            }
            if (state.equals(State.PARTIALLY_DONE)) {
                runAgain();
            }
            Store.getInstance().getBerth().getSem().release();
        } catch (InterruptedException e) {
            ShipLogger.logError(e.getMessage());
        }
        return "Information after work: " + this.toString();
    }

    /**
     * The method is called if the ship could not be serviced
     * completely. Calls the {@code call} method again until
     * the number of attempts to service {@code attemptsToWork}
     * reaches its maximum value {@code MAX_ATTEMPTS}.
     * <p>
     * If the ship could not be serviced in full for the possible
     * number of attempts, then an exception {@code ShipRunException}
     * is thrown out and the ship frees the berth in the current state.
     */
    private void runAgain() {
        ShipLogger.logInfo("Ship " + name + " is trying to work again.");
        attemptsCurrent++;
        if (attemptsCurrent == MAX_ATTEMPTS) {
            try {
                Store.getInstance().getBerth().getSem().release();
                ShipLogger.logInfo("Information after work: "
                        + this.toString());
                throw new ShipRunException();
            } catch (ShipRunException e) {
                ShipLogger.logError(e.getMessage() + ": " + this.toString());
            }
        } else {
            Store.getInstance().getBerth().getSem().release();
            call();
        }
    }

    /**
     * The method loads the ship.
     * If the sum of the current volume and the load is
     * less than or equal to the maximum volume,
     * then method {@code giveProducts} is called to take
     * the products from the {@code Store} and method
     * {@code loadAll} to fully load the ship.
     * Otherwise, we take from the store as much as it
     * moves into the ship and we siphon method {@code loadPartially()}
     * for partial loading of the ship.
     */
    private void load() {
        ShipLogger.logInfo("Ship " + name + " being loaded at the Store.");
        if (volumeCurrent + volumeLoading <= volumeMax) {
            Store.getInstance().giveProducts(volumeLoading);
            loadAll();
        } else {
            Store.getInstance().acceptProducts(volumeMax - volumeCurrent);
            loadPartially();
        }
    }

    /**
     * The method partially performs the work.
     * Loads the ship to full, but the ship needs to load more,
     * so it changes status to {@code PARTIALLY_DONE}.
     */
    private void loadPartially() {
        volumeLoading = volumeLoading - (volumeMax - volumeCurrent);
        ShipLogger.logInfo("The ship " + name + " loaded: "
                + (volumeMax - volumeCurrent)
                + ". Remained load: " + volumeLoading);
        volumeCurrent = volumeMax;
        state = State.PARTIALLY_DONE;
    }

    /**
     * The method performs all work on loading the ship.
     * Parameter {@code volumeLoading} becomes zero,
     * and is updated {@code volumeCurrent}.
     * The state of the ship becomes {@code DONE}.
     */
    private void loadAll() {
        volumeCurrent += volumeLoading;
        ShipLogger.logInfo("The ship " + name + " loaded: "
                + volumeLoading + ". Remained load: 0.");
        volumeLoading = 0.0;
        state = State.DONE;
        ShipLogger.logInfo("Information after work: " + this.toString());
    }

    /**
     * The method unloads the ship. The method calls
     * method {@code acceptProducts} to add products
     * to the store.
     * If there is enough space to load products,
     * method {@code unloadAll} is called.
     * Otherwise, method {@code unloadPartially}.
     */
    private void unload() {
        ShipLogger.logInfo("Ship " + name + " being unloaded at the Store.");
        final Double storeVolume = Store.getInstance().getProduct();
        Store.getInstance().acceptProducts(volumeUnloading);
        if (Store.getInstance().getNominalVolume()
                - storeVolume >= volumeUnloading) {
            unloadAll();
        } else {
            final Double volumeEmpty = Store.getInstance().getNominalVolume()
                    - storeVolume;
            unloadPartially(volumeEmpty);
        }
    }

    /**
     * The method partially unloads the ship.
     * The new current volume {@code volumeCurrent}
     * of the ship is calculated, after partial discharge
     * and how much it remains to unload {@code volumeUnloading}.
     * The state of the ship becomes {@code PARTIALLY_DONE}.
     *
     * @param volumeEmpty the remaining place in the store.
     */
    private void unloadPartially(final Double volumeEmpty) {
        final Double allVolumeCurrent = volumeCurrent;
        volumeCurrent -= volumeEmpty;
        volumeUnloading -= volumeEmpty;
        state = State.PARTIALLY_DONE;
        ShipLogger.logInfo("The ship " + name + " unloaded: "
                + (allVolumeCurrent - volumeCurrent)
                + ". Remained unload: " + volumeUnloading);
    }

    /**
     * The method unloads the ship.
     * Updates {@code volumeCurrent}.
     * Parameter {@code volumeUnloading} becomes zero.
     * The status is {@code DONE}.
     */
    private void unloadAll() {
        volumeCurrent -= volumeUnloading;
        ShipLogger.logInfo("The ship " + name + " unloaded: "
                + volumeUnloading + ". Remained unload: 0.");
        volumeUnloading = 0.0;
        state = State.DONE;
        ShipLogger.logInfo("Information after work: " + this.toString());
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * <p>
     * Overridden method {@code toString} of class {@code Object}.
     * <p>
     * This method returns a string with the name of the class and
     * an enumeration of its parameters and their values when t
     * he method is called.
     *
     * @return return  a string representation of the object.
     */
    @Override
    public final String toString() {
        return "Ship{name='" + name + "'"
                + ", state=" + state
                + ", volumeMax=" + volumeMax
                + ", volumeCurrent=" + volumeCurrent
                + ", purpose=" + purpose
                + ", volumeLoading=" + volumeLoading
                + ", volumeUnloading=" + volumeUnloading
                + '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * Overridden method {@code equals} of class {@code Object}.
     * This method equals two objects of this class in all
     * parameters: {@code name},{@code volumeMax},{@code volumeCurrent},
     * {@code purpose}, {@code volumeLoading}, {@code volumeUnloading},
     * {@code store}, {@code state}, except for the time {@code time} of arrival
     * of the ship on the berth.
     *
     * @param object the reference object with which to compare.
     * @return {@code true} if this object is the same as the o argument;
     * {@code false} otherwise.
     */
    @Override
    public final boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Ship ship = (Ship) object;
        return Objects.equals(name, ship.name)
                && Objects.equals(volumeMax, ship.volumeMax)
                && Objects.equals(volumeCurrent, ship.volumeCurrent)
                && purpose == ship.purpose
                && Objects.equals(volumeLoading, ship.volumeLoading)
                && Objects.equals(volumeUnloading, ship.volumeUnloading)
                && state == ship.state;
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link java.lang.Object#equals(java.lang.Object)}
     * method, then calling the {@code hashCode} method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hash tables.
     * </ul>
     * Overridden method {@code hashCode} of class {@code Object}.
     * The hash code is computed based on the fields of the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public final int hashCode() {

        return Objects.hash(super.hashCode(), name, volumeMax, volumeCurrent,
                purpose, volumeLoading, volumeUnloading, state);
    }
}

