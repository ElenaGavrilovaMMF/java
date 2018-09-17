package enam;

/**
 * The {@code Purpose } class is an enumeration.
 * It contains the purpose of the Ship: load, unload,
 * load-unload.
 */
public enum Purpose {
    /**
     * Determines that the ship has arrived for
     * loading.
     */
    LOAD,
    /**
     * Determines that the ship has arrived for
     * unloading.
     */
    UNLOAD,
    /**
     * Determines that the ship has arrived for
     * loading and unloading.
     */
    LOAD_UNLOAD;
}
