package edu.petrov.gojavaonline.module04.task02;

import edu.petrov.gojavaonline.module09.CaesarCipher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anton on 28.03.2016.
 */
public class CaesarCipherTest {

    @Test
    public void shiftTest() {
        assertEquals(CaesarCipher.shift(CaesarCipher.shift('A', 3), -3), 'A');
    }
}
