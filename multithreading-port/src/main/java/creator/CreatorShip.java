package creator;

import entity.Ship;
import enam.Purpose;
import enam.State;

/**
 * The {@code CreatorShip} is a utility class that
 * includes methods for creating a {@code Ship} object.
 *
 * @author Elena Gavrilova
 */
public final class CreatorShip {

    /**
     * The index of the name in the data array.
     */
    private static final int ID_NAME = 0;
    /**
     * The index of the maximum volume in the data array.
     */
    private static final int ID_VOLUME_MAX = 1;
    /**
     * The index of the current volume in the data array.
     */
    private static final int ID_VOLUME_CURRENT = 2;
    /**
     * The index of the loading volume in the data array.
     */
    private static final int ID_VOLUME_LOAD = 4;
    /**
     * The index of the unloading volume in the data array.
     */
    private static final int ID_VOLUME_UNLOAD = 5;
    /**
     * The index of the purpose volume in the data array.
     */
    private static final int ID_PURPOSE = 3;

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private CreatorShip() {
    }

    /**
     * The method takes the {@code String} data and returns
     * the Ship object. The {@code String} data should be
     * represented as follows:
     * "name number number unload/load/load-unload number number".
     *
     * @param data ship data.
     * @return {@code Ship}.
     */
    public static Ship createShip(final String data) {
        final String[] res = data.split(" ");
        new Ship();
        final Ship.ShipBuilder ship = Ship.builder()
                .name(res[ID_NAME])
                .volumeMax(Double.valueOf(res[ID_VOLUME_MAX]))
                .volumeCurrent(Double.valueOf(res[ID_VOLUME_CURRENT]))
                .volumeLoading(Double.valueOf(res[ID_VOLUME_LOAD]))
                .volumeUnloading(Double.valueOf(res[ID_VOLUME_UNLOAD]))
                .state(State.NOT_DONE);
        final Ship build = ship.build();
        if (res[ID_PURPOSE].equals("load")) {
            build.setPurpose(Purpose.LOAD);
        }
        if (res[ID_PURPOSE].equals("unload")) {
            build.setPurpose(Purpose.UNLOAD);
        }
        if (res[ID_PURPOSE].equals("load-unload")) {
            build.setPurpose(Purpose.LOAD_UNLOAD);
        }
        return build;
    }
}
