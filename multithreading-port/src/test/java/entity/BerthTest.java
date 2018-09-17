package entity;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.Semaphore;

public class BerthTest {

    @Test
    public void testGetCount(){
        final Berth berth = new Berth();
        Assert.assertEquals(berth.getCount(),
               2);
    }

    @Test
    public void testGetSem(){
        final Berth berth = new Berth();
        Assert.assertEquals(berth.getSem().drainPermits(),
                new Semaphore(2).drainPermits());
    }
}