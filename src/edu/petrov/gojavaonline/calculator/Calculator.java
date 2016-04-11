package edu.petrov.gojavaonline.calculator;

public class Calculator {

    private static String A = "1234530";
    private static String B = "1";

    private static BigInteger a = new BigInteger(A);
    private static BigInteger b = new BigInteger(B);

    private final java.math.BigInteger test = new java.math.BigInteger("1");
    private final java.math.BigDecimal test2 = new java.math.BigDecimal("1");

    private static String binaryToString(int i) {
        String bin = "";
        int t = 31;
        do {
            bin = (i % 2) + bin;
            i /= 2;
            t--;
        } while (i > 0 || t > 0);
        return bin;
    }

    /**
     * Returns right part of the integer value <b>a</b>
     *
     * @param a 30-bit integer
     * @param r number of bits
     */
    private static int right(int a, int r) {
        return ~(a << (31 - r)) >> (31 - r * 2);
    }

    /**
     * Returns left part of the integer value <b>a</b>
     *
     * @param a 30-bit integer
     * @param l number of bits
     */
    private static int left(int a, int l) {
        return (a >> (30 - l)) << (30 - l);
    }

    public static void bitsOperations() {
        int a = 0b0011_1111_1111_1111_1111_1111_1111_1111;
        System.out.println(binaryToString(a));
        final int nBits = 30;
        int r = 5;
        int l = nBits - r;
        System.out.println(binaryToString(right(a, r)));
        System.out.println(binaryToString(left(a, l)));
    }


    public static void main(String[] args) {

//        bitsOperations();

        System.out.println(new java.math.BigInteger("123456789987654321").shiftRight(3));
        System.out.println(new BigInteger("123456789987654321").shiftRight(3));

        //System.out.println(new BigInteger("123456789123456789123456789123456789123456789123456789123456789123456789123456789").binaryValue().length());
    }
}