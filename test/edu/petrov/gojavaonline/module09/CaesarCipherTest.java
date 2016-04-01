package edu.petrov.gojavaonline.module09;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class CaesarCipherTest {

    private char inputChar;     // 0
    private char outputChar;    // 1
    private int key;            // 2

    public CaesarCipherTest(char inputChar, char outputChar, int key) {
        this.inputChar = inputChar;
        this.outputChar = outputChar;
        this.key = key;
    }

    @Parameters(name = "{index}: shift({0}, {2}) = {1}")
    public static Iterable<Object[]> getParametrizedData() {
        return Arrays.asList(new Object[][]{
                {'A', 'D', 3},
                {'H', 'M', 5},
                {'a', 'k', 10}
        });
    }

    @Test
    public void testShift() throws Exception {
        assertEquals(CaesarCipher.shift(CaesarCipher.shift('A', 3), -3), 'A');
        assertEquals(CaesarCipher.shift(CaesarCipher.shift('Я', 300), -300), 'Я');
    }

    @Test
    public void testShiftParametrized() {
        assertEquals(outputChar, CaesarCipher.shift(inputChar, key));
    }
}
