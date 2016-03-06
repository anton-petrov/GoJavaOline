package edu.petrov.gojavaonline.module04.task02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by anton on 04/03/16.
 */
public class TemperatureDegreeConverterTest {
    @Before
    public void Before() {

    }

    @After
    public void After() {

    }

    @Test
    public void testConvertAsDouble() throws Exception {
        assertEquals(TemperatureDegreeConverter.convertAsDouble("-55C"), -67, 0);
        assertEquals(TemperatureDegreeConverter.convertAsDouble("0C"), 32, 0);
        assertEquals(TemperatureDegreeConverter.convertAsDouble("68f"), 20, 0);
        assertEquals(TemperatureDegreeConverter.convertAsDouble("32F"), 0, 0);
    }

    @Test
    public void testConvertAsString() throws Exception {
        assertEquals(TemperatureDegreeConverter.convertAsString("-55C", 0), "-67F");
        assertEquals(TemperatureDegreeConverter.convertAsString("0c", 0), "32F");
        assertEquals(TemperatureDegreeConverter.convertAsString("68f", 0), "20C");
    }

    @Test
    public void testConvertCelsiusToFahrenheit() throws Exception {
        double[] expecteds = {
                -67,
                32,
                68
        };
        double[] actuals = {
                TemperatureDegreeConverter.convertCelsiusToFahrenheit(-55),
                TemperatureDegreeConverter.convertCelsiusToFahrenheit(0),
                TemperatureDegreeConverter.convertCelsiusToFahrenheit(20)
        };
        assertArrayEquals(expecteds, actuals, 0);
    }

    @Test
    public void testConvertFahrenheitToCelsius() throws Exception {
        double[] expecteds = {
                -55,
                0,
                20
        };
        double[] actuals = {
                TemperatureDegreeConverter.convertFahrenheitToCelsius(-67),
                TemperatureDegreeConverter.convertFahrenheitToCelsius(32),
                TemperatureDegreeConverter.convertFahrenheitToCelsius(68)
        };
        assertArrayEquals(expecteds, actuals, 0);
    }
}