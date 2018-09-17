package creator;

import enam.Purpose;
import enam.PurposeTest;
import enam.State;
import entity.Ship;
import entity.Store;
import entity.StoreTest;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@NoArgsConstructor
public class CreatorShipTest {

    @BeforeClass
    private void setUp() {
        new PurposeTest();
        new StoreTest();
    }

    @DataProvider(name = "dateTestCreatorShip")
    private Object[][] dateTestCreatorShip() {
        return new Object[][]{{new Ship("1",70.0,
                70.0, Purpose.UNLOAD,0.0,
                70.0, State.NOT_DONE),
                "1 70.0 70.0 unload 0.0 70.0"}};
    }

    @Test(dataProvider = "dateTestCreatorShip")
    public void testCreateShip(final Ship ship,final String date){
        final Ship shipActual = CreatorShip.createShip(date);
        Assert.assertEquals(shipActual,ship);
    }
}