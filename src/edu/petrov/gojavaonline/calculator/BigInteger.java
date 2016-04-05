package edu.petrov.gojavaonline.calculator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 05/04/16.
 */
public class BigInteger implements Comparable<BigInteger> {

    private static int BASE = 1000 * 1000 * 1000;
    private static String STRING_BASE_VALUE = Integer.toString(BASE);
    private static int BASE_LENGTH = STRING_BASE_VALUE.length() - 1;
    private List<Integer> digits = new ArrayList<>();
    private Sign sign = Sign.POSITIVE;
    public BigInteger() {
        //digits.add(0);
        //System.out.println("BigInteger()");
    }
    public BigInteger(String stringValue) {
        //System.out.println("BigInteger(String)");
        parse(stringValue);
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
        result.digits.add(Integer.parseInt(stringValue.substring(0, k)));

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

    @Override
    public int compareTo(BigInteger o) {
        int compare = 0;
        if (this.getSign() == o.getSign()) {
            if (this.size() > o.size()) {
                compare = -1;
            } else if (o.size() > this.size()) {
                compare = 1;
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
        } else if (this.getSign() == Sign.POSITIVE && o.getSign() == Sign.NEGATIVE) {
            compare = 1;
        } else if (this.getSign() == Sign.NEGATIVE && o.getSign() == Sign.POSITIVE) {
            compare = -1;
        }

        return compare;
    }

    private int getDigit(int index) {
        if (index >= digits.size()) {
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

    private int size() {
        return digits.size();
    }

    public BigInteger multiply(BigInteger arg) {
        BigInteger result = new BigInteger();

        for (int i = 0, carry = 0; i < this.size(); i++) {
            for (int j = 0; j < arg.size() || carry > 0; j++) {
                long current = (long) this.getDigit(i) * (j < arg.size() ? getDigit(j) : 0) + result.getDigit(i + j) + carry;
                result.setDigit(i + j, (int) (current % BASE));
                carry = (int) (current / BASE);
            }
        }
        digits = result.digits;
        return this;
    }

    public BigInteger divide(BigInteger arg) {
        throw new NotImplementedException();
    }

    public BigInteger add(BigInteger arg) {


        if (this.getSign() == Sign.POSITIVE && arg.getSign() == Sign.NEGATIVE) {
            return subtract(arg);
        } else if (this.getSign() == Sign.NEGATIVE && arg.getSign() == Sign.POSITIVE) {
            return arg.subtract(this);
        }

        int carry = -1;
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < Math.max(this.size(), arg.size()) || carry >= 0; i++) {
            int sum = 0;

            if (carry >= 0) {
                sum += 1;
            }

            sum += i < arg.size() ? arg.digits.get(i) : 0;
            sum += i < this.size() ? this.digits.get(i) : 0;

            carry = sum - BASE;

            if (carry >= 0)
                sum -= BASE;

            result.add(sum);
        }

        digits = result;

        return this;
    }

    public BigInteger subtract(BigInteger arg) {

        BigInteger a = arg;
        BigInteger b = this;

        if (b.compareTo(a) < 0) {
            /*
                Меняем местами уменшаемое и вычитаемое, устанавливаем знак 'минус' и производим обычное вычитание
            */
            a = this;
            b = arg;
            setSign(Sign.NEGATIVE);
        } else if (b.compareTo(a) == 0) {
            /*
                Возвращаем нуль, поскольку оба числа равны
            */
            return new BigInteger();
        }

        int carry = 0;
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < b.size() || carry == 1; i++) {

            int diff = -carry;
            carry = 0;

            diff -= i < a.size() ? a.digits.get(i) : 0;
            diff += i < b.size() ? b.digits.get(i) : 0;

            if (diff < 0) {
                carry = 1;
                diff += BASE;
            }
            result.add(diff);
        }

        digits = result;
        removeLeadingZeroes(digits);
        return this;
    }

    public BigInteger parse(String stringValue) {
        digits = parseBigInteger(stringValue).digits;
        return this;
    }

    @Override
    public String toString() {
        if (digits.size() == 0)
            return "0";

        StringBuilder result = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; i--) {
            result.append(digits.get(i).toString());
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
        result = 31 * result + sign.value();
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
