package parser;

import creator.CreatorShip;
import creator.CreatorShipTest;
import entity.Ship;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FileParserTest {
    private static final List<String> DATE_STRING = new ArrayList<>();
    private static final List<Ship> DATE_SHIP = new ArrayList<>();

    @BeforeClass
    private void setUpTest() {
        new CreatorShipTest();
    }

    @BeforeClass
    private void setUp() {
        DATE_STRING.add("1 70.0 70.0 unload 0.0 70.0");
        DATE_SHIP.add(CreatorShip.createShip("1 70.0 70.0 unload 0.0 70.0"));
    }

    @DataProvider(name = "dateTestParse")
    private Object[][] dateTestParse() {
        return new Object[][]{{DATE_SHIP, DATE_STRING}};
    }

    @Test(dataProvider = "dateTestParse")
    public void testParseDataList(final List<Ship> listShip, final List<String> listString) {
        final List<Ship> ships = FileParser.parseDataList(listString);
        Assert.assertEquals(ships, listShip);
    }

}