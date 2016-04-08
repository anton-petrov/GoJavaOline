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
    public final int KARATSUBA_MIN = 2;
    private final List<Integer> digits = new ArrayList<>();
    private Sign sign = Sign.POSITIVE;
    private int integerRemainder = 0;

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

    public long longValue() {
        return getDigit(0) + getDigit(1) * BASE;
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
     * Получить десятичную цифру длинного числа.
     *
     * @param i Позиция десятичной цифры
     * @return Возвращает десятичную цифру от 0 до 9
     */
    public int getNumeral(int i) {
        if (size() == 0) {
            return 0;
        }

        return 0;
    }

    public int numeralCount() {
        return getSign() == Sign.POSITIVE ? this.toString().length() : this.toString().length() - 1;
    }

    private int halfSize() {
        return (digits.size() + 1) / 2;
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

    public BigInteger Low() {
        if (size() == 0) {
            return new BigInteger();
        } else if (size() == 1) {
            return new BigInteger(this);
        } else {
            return new BigInteger(Arrays.copyOfRange(digits.toArray(new Integer[0]), 0, halfSize()));
        }
    }

    public BigInteger High() {
        if (size() == 0) {
            return new BigInteger();
        } else if (size() == 1) {
            return new BigInteger(); // High part doesn't exist, but returning 0
        } else {

            BigInteger result = new BigInteger(Arrays.copyOfRange(digits.toArray(new Integer[0]), halfSize(), digits.size()));
            return result;
        }
    }

    private void addDigits(List<Integer> _digits) {
        digits.addAll(_digits);
    }

    public BigInteger normalizedHigh() {
        BigInteger result = this.High();
        result.shiftDigitsRight(halfSize());
        return result;
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

    /**
     * Количество разрядов (размер массива digits)
     *
     * @return
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
        removeLeadingZeroes(digits);
        result.computeSignForMulDiv(val);
        return result;
    }

    public BigInteger multiply(BigInteger val) {
        /*
        if (size() >= KARATSUBA_MIN || val.size() >= KARATSUBA_MIN) {
            return karatsubaMultiply(val);
        }
        */
        return _multiply(val);
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
     * Алгоритм умножения Карацубы. Работает эффективно на очень больших числах.
     *
     * @param n
     * @return
     */
    public BigInteger karatsubaMultiply(BigInteger n) {
        BigInteger product = new BigInteger();
        int N = Math.max(this.size(), n.size());

        if (N < KARATSUBA_MIN) {
            return this._multiply(n);
        }

        BigInteger Xr = this.Low();
        BigInteger Xl = this.High();
        BigInteger Yr = n.Low();
        BigInteger Yl = n.High();

        BigInteger Xlr = Xr.add(Xl);
        BigInteger Ylr = Yr.add(Yl);

        BigInteger P1 = Xl.karatsubaMultiply(Yl);
        BigInteger P2 = Xr.karatsubaMultiply(Yr);
        BigInteger P3 = Xlr.karatsubaMultiply(Ylr);
        BigInteger P4 = P3.subtract(P1).subtract(P2);

//        System.out.println("====");
//        System.out.println("P1="+P1);
//        System.out.println("P2="+P2);
//        System.out.println("P3="+P3);
//        System.out.println("P4="+P4);
//        System.out.println("====");

        // Prod1 * 10 ^ n + (P4) * 10 ^ (n / 2) + P2

        final BigInteger base = new BigInteger(STRING_BASE_VALUE);

        // 65536000000256000000000

        P1.shiftDigitsRight(N);
        P4.shiftDigitsRight(N / 2);
        product = P1.add(P4).add(P2);
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
        BigInteger[] result = divideAndRemainder(divider);
        return result[1];
    }

    /**
     * Fast Knuth divide algorithm
     */
    public BigInteger divide(BigInteger divider) {
        BigInteger[] result = divideAndRemainder(divider);
        result[0].computeSignForMulDiv(divider);
        return result[0];
    }

    public BigInteger[] divideAndRemainder(BigInteger divider) throws ArithmeticException {

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
        // остаток от деления
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

    public BigInteger pow(BigInteger k/*, BigInteger n*/) {
        BigInteger a = new BigInteger(this);
        BigInteger b = new BigInteger(1);
        while (k.compareTo(0) > 0) {
            BigInteger Q = k.divide(2);
            if (Q.integerRemainder == 0) {
                k = Q;
                a = a._multiply(a);//.mod(n);// [ a = (a*a)%n; ]
            } else {
                k = k.subtract(new BigInteger(1));
                b = b._multiply(a);//.mod(n);// [ b = (b*a)%n; ]
            }
        }
        return b;
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

    public BigInteger add(final BigInteger arg) {
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
