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

    private int sign = 0;

    public BigInteger() {
    }


    public BigInteger(String stringValue) {
        parse(stringValue);
    }

    /**
     * Пример:
     * Входная строка "123456789987654321" будет преобразована в массив 0->987654321, 1->123456789
     * Таким образом, младшие разряды идут слева направо. В каждом елементе хранится один разряд по основанию
     * миллирад, максимально содержащий 9 десятичных знаков.
     *
     * @param stringValue
     * @return Большое целое число
     */
    public static BigInteger parseBigInteger(String stringValue) {
        BigInteger result = new BigInteger();
        int k = stringValue.length();
        for (int i = stringValue.length(); i >= BASE_LENGTH; i -= BASE_LENGTH, k = i) {
            result.digits.add(Integer.parseInt(stringValue.substring(i - BASE_LENGTH, i)));
        }
        result.digits.add(Integer.parseInt(stringValue.substring(0, k)));

        // remove leading zeroes
        for (int i = 0; i < result.digits.size() - 1; i++) {
            if (result.digits.get(i) == 0) {
                result.digits.remove(0);
            }
        }

        return result;
    }

    private int size() {
        return digits.size();
    }

    public BigInteger add(BigInteger a) {
        int carry = -1;
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < Math.max(this.size(), a.size()) || carry >= 0; i++) {
            int sum = 0;

            if (carry >= 0) {
                sum += 1;
            }

            sum += i < a.size() ? a.digits.get(i) : 0;
            sum += i < this.size() ? this.digits.get(i) : 0;

            carry = sum - BASE;

            if (carry >= 0)
                sum -= BASE;

            result.add(sum);
        }

        digits = result;
        return this;
    }

    public BigInteger subtract(BigInteger a) {
        int carry = 0;
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.size() || carry == 1; i++) {

            int diff = -carry;
            carry = 0;

            diff -= i < a.size() ? a.digits.get(i) : 0;
            diff += i < this.size() ? this.digits.get(i) : 0;

            if (diff < 0) {
                carry = 1;
                diff += BASE;
            }
            result.add(diff);
        }

        digits = result;
        return this;
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
