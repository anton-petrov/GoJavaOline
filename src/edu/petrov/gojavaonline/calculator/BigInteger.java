package edu.petrov.gojavaonline.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton Petrov, 2016
 * @version 1.0
 * BigInteger, represents integers, that does not fit at standard primitive types.
 * BigInteger is immutable type.
 */

public class BigInteger extends Number implements Comparable<BigInteger> {

    public static final BigInteger ZERO = new BigInteger();
    private final static int BASE = 1000 * 1000 * 1000;
    private final static String STRING_BASE_VALUE = Integer.toString(BASE);
    private final static int BASE_LENGTH = STRING_BASE_VALUE.length() - 1;
    // Mininum length of digits to run Karatsuba algorithm
    private static final int KARATSUBA_THRESHOLD = 64;
    private static final BigInteger base = new BigInteger(STRING_BASE_VALUE);
    private static final BigInteger ONE = new BigInteger(1);
    private final List<Integer> digits = new ArrayList<>();
    private Sign sign = Sign.POSITIVE;
    private int integerRemainder = 0;

    public BigInteger() {
    }

    public BigInteger(String stringValue) {
        parse(stringValue);
    }

    public BigInteger(int intValue) {
        digits.clear();
        setDigit(0, intValue);
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
     * Convert String to BigInteger
     * @param stringValue
     * @return BigInteger
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

    private static BigInteger _pow(BigInteger a, BigInteger n) {
        if (n.isZero()) {
            return new BigInteger(1);
        }
        if (n.mod(2) == 1) {
            return a._multiply(_pow(a, n.subtract(1)));
        } else {
            BigInteger b = _pow(a, n.divide(2));
            return b._multiply(b);
        }
    }

    private static BigInteger sqrtFunction(final BigInteger a, final BigInteger b, int s, int n) {
        int r = 0;
        BigInteger result = (b.add(a.divide(b))).divide(2);
        if (s >= n || result.subtract(b).compareTo(BigInteger.ONE) == 0) {
            return result;
        }
        return sqrtFunction(a, result, s + 1, n);
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.isZero()) {
            return a;
        } else {
            return gcd(b, a.mod(b));
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

    @Override
    public long longValue() {
        return getDigit(0) + getDigit(1) * BASE;
    }

    @Override
    public float floatValue() {
        return longValue();
    }

    @Override
    public double doubleValue() {
        return longValue();
    }

    public int intValue() {
        return getDigit(0);
    }

    public int compareTo(int val) {
        int compare = 0;
        if (this.size() > 1) {
            if (getSign() == Sign.POSITIVE) {
                compare = 1;
            } else {
                compare = -1;
            }
        } else if (size() <= 1) {
            compare = Integer.compare(getDigit(0), val);
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

    /**
     * Get decimal n<sup>th</sup> digit.
     *
     * @param i number of digit.
     * @return decimal form 0 to 9.
     */
    public int getNumeral(int i) {
        if (size() == 0) {
            return 0;
        }
        String bigIntegerString = toString();
        if (getSign() == Sign.NEGATIVE) {
            bigIntegerString = bigIntegerString.substring(1);
        }

        if (i < bigIntegerString.length() && i >= 0) {
            return Character.getNumericValue(bigIntegerString.charAt(i));
        }

        return 0;
    }

    public int numeralCount() {
        return getSign() == Sign.POSITIVE ? this.toString().length() : this.toString().length() - 1;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    private Sign computeSignForMulDiv(BigInteger val) {
        Sign sign;
        if (getSign() != val.getSign()) {
            sign = Sign.NEGATIVE;
        } else {
            sign = Sign.POSITIVE;
        }
        return sign;
    }

    private Sign computeSignForMulDiv(int val) {
        if ((getSign() == Sign.POSITIVE && val < 0) || (getSign() == Sign.NEGATIVE && val > 0)) {
            return Sign.NEGATIVE;
        } else {
            return Sign.POSITIVE;
        }
    }

    public BigInteger getLow(int n) {
        if (size() == 0) {
            return new BigInteger();
        } else if (size() == 1) {
            return new BigInteger(this);
        } else {
            if (n > size()) n = size();
            return new BigInteger(digits.subList(0, n));
        }
    }

    public BigInteger getHigh(int n) {
        if (size() == 0) {
            return new BigInteger();
        } else if (size() <= n) {
            return new BigInteger(); // High part doesn't exist, but returning 0
        } else {
            if (n > size()) n = size();
            return new BigInteger(digits.subList(size() - n + 1, size()));
        }
    }

    private void addDigits(List<Integer> _digits) {
        digits.addAll(_digits);
    }

    public void shiftDigitsRight(int n) {
        if (digits.size() == 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            if (digits.size() > 0) {
                digits.remove(0);
            } else {
                break;
            }
        }
    }

    public void shiftDigitsLeft(int n) {
        if (digits.size() == 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            digits.add(0, 0);
        }
    }

    /**
     * @return Count of digits ( digits.size() )
     */
    private int size() {
        return digits.size();
    }

    private void normalize() {
        for (int i = 0, carry = 0; i < size() || carry > 0; i++) {
            int current = getDigit(i) + carry;
            setDigit(i, current % BASE);
            carry = current / BASE;
        }
        removeLeadingZeroes(digits);
    }

    public BigInteger _multiply(BigInteger val) {
        final BigInteger result = new BigInteger();
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0, carry = 0; j < val.size() || carry > 0; j++) {
                long current = (long) (this.getDigit(i))
                        * (j < val.size() ? val.getDigit(j) : 0)
                        + result.getDigit(i + j)
                        + carry;
                result.setDigit(i + j, (int) (current % BASE));
                carry = (int) (current / BASE);
            }
        }
        removeLeadingZeroes(result.digits);
        result.computeSignForMulDiv(val);
        return result;
    }

    public BigInteger multiply(BigInteger val) {
        if (size() >= KARATSUBA_THRESHOLD || val.size() >= KARATSUBA_THRESHOLD) {
            return multiplyKaratsuba(val);
        } else {
        return _multiply(val);
        }
    }

    public BigInteger multiply(int val) {
        final BigInteger result = new BigInteger();
        for (int i = 0, carry = 0; i < this.size() || carry > 0; i++) {
            long current = (long) val * (long) getDigit(i) + carry;
            result.addDigit((int) (current % BASE));
            carry = (int) (current / BASE);
        }
        removeLeadingZeroes(result.digits);
        result.computeSignForMulDiv(val);
        return result;
    }

    /**
     * Implementation of Karatsuba algorithm.
     * Time complexity of the above solution is O(n<sup>1.59</sup>).
     * @param n
     * @return
     */
    public BigInteger multiplyKaratsuba(BigInteger n) {
        BigInteger product;
        BigInteger X = this;
        BigInteger Y = n;
        int xlen = X.size();
        int ylen = Y.size();

        int N = Math.max(xlen, ylen);
        int half = (N + 1) / 2;

        if (N < KARATSUBA_THRESHOLD) {
            return X.multiply(Y);
        }

        BigInteger xl = X.getLow(half);
        BigInteger xh = X.getHigh(half);
        BigInteger yl = Y.getLow(half);
        BigInteger yh = Y.getHigh(half);

        BigInteger Xlh = xh.add(xl);
        BigInteger Ylh = yh.add(yl);

        BigInteger p2 = xl.multiplyKaratsuba(yl);
        BigInteger p1 = xh.multiplyKaratsuba(yh);
        BigInteger p3 = Xlh.multiplyKaratsuba(Ylh);

        BigInteger p4 = p3.subtract(p1);
        p4 = p4.subtract(p2);

        p1.shiftDigitsLeft(half * 2);
        p4.shiftDigitsLeft(half);

        product = p1.add(p4).add(p2);

        return product;
    }

    public BigInteger divide(int divider) {
        final BigInteger result = new BigInteger(this);
        int remainder = 0;
        for (int i = result.size() - 1; i >= 0; --i) {
            long current = result.getDigit(i) + (long) remainder * BASE;
            result.setDigit(i, (int) (current / divider));
            remainder = (int) (current % divider);
        }
        removeLeadingZeroes(result.digits);
        setSign(computeSignForMulDiv(divider));
        result.integerRemainder = remainder;
        return result;
    }

    public BigInteger abs() {
        final BigInteger result = new BigInteger(this);
        result.setSign(Sign.POSITIVE);
        return result;
    }

    // Naive division method - VERY slow.
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

    public BigInteger sqrt(final BigInteger value) {
        return sqrtFunction(this, value, 1, 1000);
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
        BigInteger[] result = divideAndRemainder(divider);
        return result[1];
    }

    public int mod(int divider) {
        BigInteger result = divide(divider);
        return result.integerRemainder;
    }

    public BigInteger divide(BigInteger divider) {
        BigInteger[] result = divideAndRemainder(divider);
        result[0].computeSignForMulDiv(divider);
        return result[0];
    }

    /**
     * Fast Knuth divide algorithm.
     */
    public BigInteger[] divideAndRemainder(BigInteger divider) throws IllegalArgumentException {

        if (divider.isZero()) {
            throw new IllegalArgumentException("Divide by zero!");
        } else if (isZero() || absCompareTo(divider) < 0) {
            return new BigInteger[]{new BigInteger(), new BigInteger()};
        } else if (absCompareTo(divider) == 0) {
            return new BigInteger[]{new BigInteger(1), new BigInteger()};
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
        // calculate remainder
        r = this.subtract(q.multiply(divider));
        return new BigInteger[]{q, r};
    }

    private void removeDigit(int index) {
        if (index >= 0 && index < digits.size()) {
            digits.remove(index);
        }
    }

    public BigInteger pow(int a) {
        a = Math.abs(a);
        if (a == 0) {
            return new BigInteger(0);
        }
        BigInteger result = new BigInteger(this);
        while (a > 1) {
            result = result._multiply(result);
            a--;
        }
        return result;
    }

    public BigInteger pow(BigInteger n) {
        return _pow(this, n);
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

    public BigInteger add(final BigInteger value) {
        if (this.getSign() == Sign.POSITIVE && value.getSign() == Sign.NEGATIVE) {
            return subtract(value);
        } else if (this.getSign() == Sign.NEGATIVE && value.getSign() == Sign.POSITIVE) {
            return value.subtract(this);
        }
        long carry = -1;
        final BigInteger result = new BigInteger();
        for (int i = 0; i < Math.max(this.size(), value.size()) || carry >= 0; i++) {
            long sum = 0;
            if (carry >= 0) {
                sum += 1;
            }
            sum += i < value.size() ? value.digits.get(i) : 0;
            sum += i < this.size() ? this.digits.get(i) : 0;
            carry = sum - BASE;
            if (carry >= 0)
                sum -= BASE;
            result.addDigit((int) sum);
        }
        return result;
    }

    public BigInteger subtract(final int val) {
        return subtract(new BigInteger("1"));
    }

    public BigInteger subtract(final BigInteger value) {
        BigInteger result = new BigInteger();
        BigInteger a = new BigInteger(value);
        BigInteger b = new BigInteger(this);

        if (b.absCompareTo(a) == 0) {
            return new BigInteger();
        }
        if (b.getSign() == Sign.POSITIVE && a.getSign() == Sign.POSITIVE) {
            if (b.absCompareTo(a) < 0) {
                BigInteger t = a;
                a = b;
                b = t;
                result.setSign(Sign.NEGATIVE);
            }
        } else if (b.getSign() == Sign.POSITIVE && a.getSign() == Sign.NEGATIVE) {
            return b.add(a.abs());
        } else if (b.getSign() == Sign.NEGATIVE && a.getSign() == Sign.POSITIVE) {
            a.setSign(Sign.POSITIVE);
            b.setSign(Sign.POSITIVE);
            result = a.add(b);
            result.setSign(Sign.NEGATIVE);
            return result;
        } else if (b.getSign() == Sign.NEGATIVE && a.getSign() == Sign.NEGATIVE) {
            a.setSign(Sign.POSITIVE);
            b.setSign(Sign.POSITIVE);
            return a.subtract(b);
        }

        // diff = b - a, a >= 0, b >= 0, a > b
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
            if (i == digits.size() - 1 && digits.get(i) != 0) {
                result.append(String.format("%d", digits.get(i)));
            } else {
                result.append(String.format("%0" + BASE_LENGTH + "d", digits.get(i)));
            }
        }
        return (sign == Sign.NEGATIVE ? "-" : "") + result.toString();
    }

    /**
     * Returns String value of this BigInteger.
     *
     * @param width
     * @return formatted representation with specified <b>width</b>
     */
    public String stringValue(int width) {
        String value = toString();
        StringBuilder result = new StringBuilder();
        width = Math.min(width, value.length());
        int i;
        for (i = 0; i < value.length() - width; i += width) {
            result.append(String.format("%s\n", value.substring(i, i + width)));
        }
        result.append(value.substring(i, value.length()));
        return result.toString();
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