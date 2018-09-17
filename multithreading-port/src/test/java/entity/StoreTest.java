package entity;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StoreTest {

    @BeforeClass
    private void setUp() {
        new BerthTest();
    }

    @DataProvider(name = "dateTestGet")
    private Object[][] dateTestGet() {
        return new Object[][]{{0.0, 10.0},
                {0.0,0.0}};
    }

    @DataProvider(name = "dateTestPut")
    private Object[][] dateTestPut() {
        return new Object[][]{{10.0, 10.0},
                {10.0,0.0}};
    }

    @Test(dataProvider = "dateTestGet")
    public void testGet(final Double productResult, final Double productGet) {
        Store.getInstance().giveProducts(productGet);
        Assert.assertEquals(Store.getInstance().getProduct(),productResult);
    }

    @Test(dataProvider = "dateTestPut")
    public void testPut(final Double productResult, final Double productPut) {
        Store.getInstance().acceptProducts(productPut);
        Assert.assertEquals(Store.getInstance().getProduct(),productResult);
    }

    @Test
    public void testGetInstance() {
        Assert.assertEquals(Store.getInstance().getClass().toString(), "class entity.Store");

    }
}