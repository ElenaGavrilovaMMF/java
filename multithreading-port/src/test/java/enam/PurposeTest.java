package enam;

import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;

@NoArgsConstructor
public class PurposeTest {

    @Test
    private void testPurpose(){
        Assert.assertEquals(Purpose.LOAD,
                Purpose.valueOf("LOAD"));
    }

}