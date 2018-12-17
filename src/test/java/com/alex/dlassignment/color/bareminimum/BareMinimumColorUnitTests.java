package com.alex.dlassignment.color.bareminimum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BareMinimumColorUnitTests {
    @Test
    public void mixesAdditiveColorsWithoutClipping() throws Exception {
        assertEquals(new Rgb256Color(0, 20, 30).mix(new Rgb256Color(100, 150, 200)), new Rgb256Color(100, 170, 230));
    }

    @Test
    public void mixesAdditiveColorsWithClipping() throws Exception {
        assertEquals(new Rgb256Color(10, 20, 100).mix(new Rgb256Color(100, 240, 200)), new Rgb256Color(110, 255, 255));
    }

}
