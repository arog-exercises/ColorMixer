package com.alex.dlassignment.color.robust;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RobustColorUnitTests {
    @Test
    public void mixesAdditiveColorsWithoutClipping() throws Exception {
        Color color1 = Color.rgb256(0, 20, 30);
        Color color2 = Color.rgb256(100, 150, 200);

        Color color = color1.mix(color2);
        assertEquals(color, Color.rgb256(100, 170, 230));

        System.out.println(color1 + " + " + color2 + " = " + color);
    }

    @Test
    public void mixesAdditiveColorsWithClipping() throws Exception {
        Color color1 = Color.rgb256(10, 20, 100);
        Color color2 = Color.rgb256(100, 240, 200);

        Color color = color1.mix(color2);
        assertEquals(color, Color.rgb256(110, 255, 255));

        System.out.println(color1 + " + " + color2 + " = " + color);
    }
}
