package enam;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StateTest {

    @Test
    private void testState(){
        Assert.assertEquals(State.DONE,
                State.valueOf("DONE"));
    }

}