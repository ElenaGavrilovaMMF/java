package parser;

import creator.CreatorShip;
import entity.Ship;
import validator.ShipValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for parsing data.
 */
public final class FileParser {

    /**
     * The constructor of the class is private, since
     * the class is utility.
     */
    private FileParser() {
    }

    /**
     * The method parse {@code List<String>} that contain actual
     * information about the ships.
     * Returns the list of ships {@code List<Ship>}.
     *
     * @param data list with data about the ships.
     * @return {@code List<Ship>} list of ships.
     */
    public static List<Ship> parseDataList(
            final List<String> data) {
        final List<Ship> listData = new ArrayList<Ship>();
        for (final String datum : data) {
            final Ship ship = CreatorShip.createShip(datum);
            ShipValidator.validate(ship);
            listData.add(ship);
        }

        return listData;
    }

}
