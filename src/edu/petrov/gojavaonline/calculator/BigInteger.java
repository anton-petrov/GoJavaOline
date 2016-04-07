package edu.petrov.gojavaonline.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anton on 05/04/16.
 * BigInteger is immutable type.
 */

public class BigInteger implements Comparable<BigInteger> {

    private final static int BASE = 1000 * 1000 * 1000;
    private final static String STRING_BASE_VALUE = Integer.toString(BASE);
    private final static int BASE_LENGTH = STRING_BASE_VALUE.length() - 1;
    // Минимальная длина массива разрядов длинного числа, на которой запускать алгоритм Карацубы
    public final int KARATSUBA_MIN = 8;
    private final List<Integer> digits = new ArrayList<>();
    private Sign sign = Sign.POSITIVE;

    public BigInteger() {
    }

    public BigInteger(String stringValue) {
        parse(stringValue);
    }

    public BigInteger(int intValue) {
        parse(String.valueOf(intValue));
    }

    public BigInteger(BigInteger bigInteger) {
        assign(bigInteger);
    }

    private BigInteger(List<Integer> integerList) {
        digits.addAll(integerList);
    }

    private BigInteger(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            digits.add(array[i]);
        }
    }
    private BigInteger(int[] array) {
        for (int i = 0; i < array.length; i++) {
            digits.add(array[i]);
        }
    }

    /**
     * Пример:
     * Входная строка "123456789987654321" будет преобразована в массив 0->987654321, 1->123456789
     * Таким образом, разряды идут слева направо от младших к старшим. В каждом элементе хранится один разряд
     * по основанию миллиард, максимально содержащий 9 десятичных знаков.
     *
     * @param stringValue
     * @return Большое целое число
     */
    public static BigInteger parseBigInteger(String stringValue) throws NumberFormatException {

        if (stringValue == null || stringValue == "") {
            throw new NumberFormatException();
        }

        BigInteger result = new BigInteger();

        // sign parsing
        result.setSign(stringValue.charAt(0) == '-' ? Sign.NEGATIVE : Sign.POSITIVE);
        if (result.getSign() == Sign.NEGATIVE) {
            stringValue = stringValue.substring(1);
        }

        int k = stringValue.length();
        for (int i = stringValue.length(); i >= BASE_LENGTH; i -= BASE_LENGTH, k = i) {
            result.digits.add(Integer.parseInt(stringValue.substring(i - BASE_LENGTH, i)));
        }
        if (k > 0) {
            result.digits.add(Integer.parseInt(stringValue.substring(0, k)));
        }
        removeLeadingZeroes(result.digits);

        return result;
    }

//    private static void removeLeadingZeroes(BigInteger bigInteger) {
//        // remove leading zeroes
//        for (int i = bigInteger.size() - 1; i >= 1; i--) {
//            if (bigInteger.getDigit(bigInteger.size() - 1) == 0) {
//                bigInteger.removeDigit(bigInteger.size() - 1);
//            } else {
//                break;
//            }
//        }
//    }

    private static void removeLeadingZeroes(List<Integer> digits) {
        // remove leading zeroes
        for (int i = digits.size() - 1; i >= 1; i--) {
            if (digits.get(digits.size() - 1) == 0) {
                digits.remove(digits.size() - 1);
            } else {
                break;
            }
        }
    }

    public BigInteger assign(BigInteger bigInteger) {
        digits.clear();
        digits.addAll(bigInteger.digits);
        sign = bigInteger.sign;
        return this;
    }

    public BigInteger parse(String stringValue) {
        BigInteger bigInteger = parseBigInteger(stringValue);
        assign(bigInteger);
        return this;
    }

    public int absCompareTo(BigInteger o) {
        int compare = 0;
        if (this.size() > o.size()) {
            compare = 1;
        } else if (o.size() > this.size()) {
            compare = -1;
        } else {
            for (int i = size() - 1; i >= 0; i--) {
                int t = this.getDigit(i) - o.getDigit(i);
                if (t > 0) {
                    compare = 1;
                    break;
                } else if (t < 0) {
                    compare = -1;
                    break;
                }
            }
        }
        return compare;
    }

    @Override
    public int compareTo(BigInteger val) {
        int compare = 0;
        if (this.getSign() == val.getSign()) {
            compare = absCompareTo(val);
        } else if (this.getSign() == Sign.POSITIVE && val.getSign() == Sign.NEGATIVE) {
            compare = 1;
        } else if (this.getSign() == Sign.NEGATIVE && val.getSign() == Sign.POSITIVE) {
            compare = -1;
        }
        return compare;
    }

    public BigInteger max(BigInteger val) {
        return compareTo(val) > 0 ? this : val;
    }

    public BigInteger min(BigInteger val) {
        return compareTo(val) < 0 ? this : val;
    }

    private int getDigit(int index) {
        if (index >= digits.size() || index < 0) {
            return 0;
        }
        return digits.get(index);
    }

    private void setDigit(int index, Integer value) {
        if (index < digits.size()) {
            digits.set(index, value);
        } else {
            digits.add(index, value);
        }
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public BigInteger Low() {
        if (size() == 0) {
            return new BigInteger();
        } else if (size() == 1) {
            return new BigInteger(this);
        } else {
            int halfSize = (int) Math.ceil(digits.size() / 2.0);
            return new BigInteger(Arrays.copyOfRange(digits.toArray(new Integer[0]), 0, halfSize));
        }
    }

    public BigInteger High() {
        if (size() == 0) {
            return new BigInteger();
        } else if (size() == 1) {
            return new BigInteger(this);
        } else {
            int halfSize = (int) Math.ceil(digits.size() / 2.0);
            BigInteger result = new BigInteger(Arrays.copyOfRange(digits.toArray(new Integer[0]), halfSize, digits.size()));
            result.shiftDigitsRight(halfSize);
            return result;
        }
    }

    private void shiftDigitsLeft(int n) {
        if (digits.size() == 0) {
            return;
        }
        List<Integer> shiftedDigits = new ArrayList<>(digits.subList(n, digits.size()));
        digits.clear();
        digits.addAll(shiftedDigits);
    }

    private void shiftDigitsRight(int n) {
        for (int i = 0; i < n; i++) {
            digits.add(0, 0);
        }
    }

    // TODO Реализовать алогоритм Карацубы для быстрого умножения

    /**
     * Количество разрядов (размер массива digits)
     *
     * @return
     */
    private int size() {
        return digits.size();
    }

    public BigInteger karatsubaMultiply(BigInteger n) {
        final BigInteger result = new BigInteger();

        if (n.size() <= KARATSUBA_MIN) {
            return this.multiply(n);
        }
        return result;
    }

    public BigInteger multiply(BigInteger arg) {
        final BigInteger result = new BigInteger();
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0, carry = 0; j < arg.size() || carry > 0; j++) {
                long current = (long) (this.getDigit(i))
                        * (j < arg.size() ? arg.getDigit(j) : 0)
                        + result.getDigit(i + j)
                        + carry;
                result.setDigit(i + j, (int) (current % BASE));
                carry = (int) (current / BASE);
            }
        }
        return result;
    }

    public BigInteger multiply(int arg) {
        final BigInteger result = new BigInteger();
        for (int i = 0, carry = 0; i < this.size() || carry > 0; i++) {
            long current = (long) arg * (long) getDigit(i) + carry;
            result.addDigit((int) (current % BASE));
            carry = (int) (current / BASE);
        }
        removeLeadingZeroes(result.digits);
        return result;
    }

    public BigInteger divide(int divider) {
        final BigInteger result = new BigInteger(this);
        int carry = 0;
        for (int i = result.size() - 1; i >= 0; --i) {
            long current = result.getDigit(i) + (long) carry * BASE;
            result.setDigit(i, (int) (current / divider));
            carry = (int) (current % divider);
        }
        removeLeadingZeroes(result.digits);
        if (this.sign == Sign.NEGATIVE && divider > 0 || this.sign == Sign.POSITIVE && divider < 0) {
            result.setSign(Sign.NEGATIVE);
        }
        return result;
    }

    public BigInteger abs() {
        final BigInteger result = new BigInteger(this);
        result.setSign(Sign.POSITIVE);
        return result;
    }

    // Naive method
    @Deprecated
    public BigInteger divideClassic(BigInteger arg) {
        BigInteger counter = new BigInteger();
        BigInteger dividend = new BigInteger(this).abs();
        BigInteger divisor = arg.abs();
        while (dividend.compareTo(divisor) >= 0) {
            dividend = dividend.subtract(divisor);
            counter = counter.add(1);
        }
        if (arg.getSign() == Sign.NEGATIVE && this.getSign() == Sign.POSITIVE ||
                arg.getSign() == Sign.POSITIVE && this.getSign() == Sign.NEGATIVE) {
            counter.setSign(Sign.NEGATIVE);
        }
        return counter;
    }

    public boolean isZero() {
        return (size() == 0 || (size() == 1 && getDigit(0) == 0));
    }

    public BigInteger shiftLeft(int n) {
        return new BigInteger(this).multiply((int) Math.pow(2, n));
    }

    public BigInteger shiftRight(int n) {
        return new BigInteger(this).divide((int) Math.pow(2, n));
    }

    public BigInteger mod(BigInteger divider) {
        return divideAndRemainder(divider)[1];
    }

    public BigInteger[] divideAndRemainder(BigInteger divider) throws ArithmeticException {

        if (divider.isZero()) {
            throw new IllegalArgumentException("Divide by zero!");
        }

        if (isZero()) {
            return new BigInteger[]{new BigInteger(), new BigInteger()};
        }

        BigInteger u = new BigInteger(this);
        BigInteger v = new BigInteger(divider);
        BigInteger q;
        BigInteger r;

        int n = v.size();
        int m = u.size() - v.size();
        int[] tempArray = new int[m + 1];
        tempArray[m] = 1;
        q = new BigInteger(tempArray);

        int d = (BASE / (v.getDigit(n - 1) + 1));
        u = u.multiply(d);
        v = v.multiply(d);
        if (u.size() == n + m)
        {
            u.addDigit(0);
        }

        int j = m;

        while (j >= 0) {
            long cur = (long) (u.getDigit(j + n)) * (long) (BASE) + u.getDigit(j + n - 1);
            int Q = (int) (cur / v.getDigit(n - 1));
            int R = (int) (cur % v.getDigit(n - 1));
            do {
                if (Q == BASE ||
                        (long) Q * (long) v.getDigit(n - 2) > (long) BASE * (long) R + u.getDigit(j + n - 2)) {
                    Q--;
                    R += v.getDigit(n - 1);
                } else {
                    break;
                }
            } while (R < BASE);
            BigInteger u2 = new BigInteger(u.digits.subList(j, j + n + 1));
            u2 = u2.subtract(v.multiply(Q));
            q.setDigit(j, Q);
            for (int h = j; h < j + n; h++) {
                if (h - j >= u2.size()) {
                    u.setDigit(h, 0);
                } else {
                    u.setDigit(h, u2.getDigit(h - j));
                }
            }
            j--;

        }
        removeLeadingZeroes(q.digits);
        // остаток от деления
        r = this.subtract(q.multiply(divider));
        return new BigInteger[]{q, r};
    }

    /**
     * Fast Knuth divide algorithm
     */
    public BigInteger divide(BigInteger divider) {
        return divideAndRemainder(divider)[0];
    }

    private void removeDigit(int index) {
        digits.remove(index);
    }


    public BigInteger pow(int a) {
        a = Math.abs(a);
        if (a == 0) {
            return new BigInteger(0);
        }
        BigInteger result = new BigInteger(this);
        while (a > 1) {
            result = result.multiply(result);
            a--;
        }
        return result;
    }

    private void addDigit(int digit) {
        digits.add(digit);
    }

    public BigInteger add(int arg) {
        return new BigInteger(String.valueOf(arg)).add(this);
    }

    public BigInteger inc() {
        return new BigInteger(1).add(this);
    }

    public BigInteger add(BigInteger arg) {

        if (this.getSign() == Sign.POSITIVE && arg.getSign() == Sign.NEGATIVE) {
            return subtract(arg);
        } else if (this.getSign() == Sign.NEGATIVE && arg.getSign() == Sign.POSITIVE) {
            return arg.subtract(this);
        }
        long carry = -1;
        final BigInteger result = new BigInteger();
        for (int i = 0; i < Math.max(this.size(), arg.size()) || carry >= 0; i++) {
            long sum = 0;
            if (carry >= 0) {
                sum += 1;
            }
            sum += i < arg.size() ? arg.digits.get(i) : 0;
            sum += i < this.size() ? this.digits.get(i) : 0;
            carry = sum - BASE;
            if (carry >= 0)
                sum -= BASE;
            result.addDigit((int) sum);
        }
        return result;
    }

    public BigInteger subtract(final BigInteger arg) {

        BigInteger result = new BigInteger();
        // this - arg
        // b - a
        BigInteger a = new BigInteger(arg);
        BigInteger b = new BigInteger(this);
        if (b.compareTo(a) == 0) {
            return new BigInteger();
        }
        if (b.getSign() == Sign.POSITIVE && a.getSign() == Sign.POSITIVE) {
            if (b.compareTo(a) < 0) {
                BigInteger t = a;
                a = b;
                b = t;
                result.setSign(Sign.NEGATIVE);
            }
        } else if (b.getSign() == Sign.POSITIVE && a.getSign() == Sign.NEGATIVE) {
            return b.add(a.abs());
        } else if (b.getSign() == Sign.NEGATIVE && a.getSign() == Sign.POSITIVE) {
            result = a.add(b.abs());
            result.setSign(Sign.NEGATIVE);
            return result;
        } else if (b.getSign() == Sign.NEGATIVE && a.getSign() == Sign.NEGATIVE) {
            a.setSign(Sign.POSITIVE);
            return a.subtract(b);
        }

        // diff = a - b, a >=, b >= 0, a > b
        for (int i = 0, carry = 0; i < b.size() || carry == 1; i++) {

            int diff = -carry;
            carry = 0;

            diff -= i < a.size() ? a.digits.get(i) : 0;
            diff += i < b.size() ? b.digits.get(i) : 0;

            if (diff < 0) {
                carry = 1;
                diff += BASE;
            }
            result.addDigit(diff);
        }
        removeLeadingZeroes(result.digits);
        return result;
    }


    @Override
    public String toString() {
        if (digits.size() == 0)
            return "0";
        StringBuilder result = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; i--) {
            if (i == digits.size() - 1) {
                result.append(String.format("%d", digits.get(i)));
            } else {
                result.append(String.format("%0" + BASE_LENGTH + "d", digits.get(i)));
            }
        }
        return (sign == Sign.NEGATIVE ? "-" : "") + result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigInteger that = (BigInteger) o;

        if (sign != that.sign) return false;
        return digits.equals(that.digits);

    }

    @Override
    public int hashCode() {
        int result = digits.hashCode();
        result = 31 * result * sign.value();
        return result;
    }

    public enum Sign {
        POSITIVE,
        NEGATIVE;

        public int value() {
            switch (this) {
                case POSITIVE:
                    return 1;
                case NEGATIVE:
                    return -1;
                default:
                    return 0;
            }
        }
    }

}
