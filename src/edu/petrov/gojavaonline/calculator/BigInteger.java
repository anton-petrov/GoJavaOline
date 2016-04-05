package edu.petrov.gojavaonline.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 05/04/16.
 */
public class BigInteger {
    private static int BASE = 1000 * 1000 * 1000;
    private static String STRING_BASE_VALUE = Integer.toString(BASE);
    private static int BASE_LENGTH = STRING_BASE_VALUE.length() - 1;

    private List<Integer> digits = new ArrayList<>();


    public BigInteger() {
    }

    public BigInteger(String stringValue) {
        parse(stringValue);
    }

    public static BigInteger parseBigInteger(String stringValue) {
        BigInteger result = new BigInteger();
        int k = 0;
        for (int i = stringValue.length(); i >= BASE_LENGTH; i -= BASE_LENGTH, k = i) {
            result.digits.add(Integer.parseInt(stringValue.substring(i - BASE_LENGTH, i)));
        }
        result.digits.add(Integer.parseInt(stringValue.substring(0, k)));
        return result;
    }

    public BigInteger parse(String stringValue) {
        digits = parseBigInteger(stringValue).digits;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; i--) {
            result.append(digits.get(i).toString());
        }
        return result.toString();
    }
}
