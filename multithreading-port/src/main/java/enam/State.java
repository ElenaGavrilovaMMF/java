package enam;

/**
 * The {@code State} class is an enumeration.
 * It contains the state of the Ship: done,
 * not done and partially done.
 */
public enum State {
    /**
     * Specifies that the ship was fully serviced.
     */
    DONE,
    /**
     * Specifies that the ship was not serviced.
     */
    NOT_DONE,
    /**
     * Specifies that the ship was partially serviced.
     */
    PARTIALLY_DONE;
}
