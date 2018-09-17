package entity;

import creator.CreatorShip;
import creator.CreatorShipTest;
import enam.PurposeTest;
import enam.StateTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class ShipTest {
    private static Ship firstShip;
    private static Ship secondShip;

    @BeforeClass
    private void setUpTest() {
        new CreatorShipTest();
        new PurposeTest();
        new StateTest();
    }

    @BeforeClass
    private void setUp() {
        firstShip = CreatorShip.createShip("1 70.0 10.0 load 30.0 0.0");
        secondShip = CreatorShip.createShip("1 70.0 10.0 unload 0.0 10.0");
    }

    @DataProvider(name = "dateTestCall")
    private Object[][] dateTestCall() {
        return new Object[][]{{"Information after work: Ship{name='1', state=DONE, " +
                "volumeMax=70.0, volumeCurrent=40.0, purpose=LOAD, volumeLoading=0.0, " +
                "volumeUnloading=0.0}", firstShip},
                {"Information after work: Ship{name='1', state=DONE, volumeMax=70.0," +
                        " volumeCurrent=0.0, purpose=UNLOAD, volumeLoading=0.0, " +
                        "volumeUnloading=0.0}", secondShip}};
    }

    @DataProvider(name = "dateTestEquals")
    private Object[][] dateTestEquals() {
        return new Object[][]{{true, firstShip, firstShip},
                {false, firstShip, secondShip},
                {false, firstShip, null}};
    }

    @DataProvider(name = "dataHashCode")
    private Object[][] dataHashCode() {
        return new Object[][]{{1989722743, firstShip},
                {1068252669, secondShip}};
    }

    @Test(dataProvider = "dateTestCall")
    public void testCall(final String stringResult, final Ship ship) {
        final String shipActual = ship.call();
        System.out.println(shipActual);
        Assert.assertEquals(shipActual, stringResult);
    }

    @Test
    public void testToString() {
        Assert.assertEquals(firstShip.toString(), "Ship{name='1', " +
                "state=" + firstShip.getState() + ", volumeMax=70.0, volumeCurrent=" + firstShip.getVolumeCurrent()
                + ", purpose=LOAD, " + "volumeLoading=" + firstShip.getVolumeLoading()
                + ", volumeUnloading=" + firstShip.getVolumeUnloading() + "}");
    }

    @Test(dataProvider = "dateTestEquals")
    public void testEquals(final boolean test, final Ship firstShip, final Ship secondShip) {
        Assert.assertEquals(firstShip.equals(secondShip), test);
    }

    @Test(dataProvider = "dataHashCode")
    public void testHashCode(final Integer hashCode,final Ship ship) {
        final Integer code = ship.hashCode();
        Assert.assertEquals(code.getClass(), hashCode.getClass());
    }
}