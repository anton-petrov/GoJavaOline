package edu.petrov.gojavaonline.calculator;

/**
 * Created by anton on 05/04/16.
 */

public class Calculator {

    private static String A = "-2";
    private static String B = "-2";

    private static java.math.BigInteger bigIntegerA = new java.math.BigInteger("12345");
    private static java.math.BigInteger bigIntegerB = new java.math.BigInteger("567890");

    private static BigInteger a = BigInteger.parseBigInteger(A);
    private static BigInteger b = BigInteger.parseBigInteger(B);
    private static BigInteger c = new BigInteger("26");
    private static BigInteger d = new BigInteger("2");


    public static void main(String[] args) {
//        System.out.println(b.add(a));

        a = BigInteger.parseBigInteger(A);
        b = BigInteger.parseBigInteger(B);

        System.out.println(bigIntegerA.shiftLeft(1));

//        System.out.format("%d", (long)1e9);


        System.out.format("%s / %s = %s\n", c, d, c.divide(d));
        System.out.format("%s * %d = %s\n", c, 999000, c.multiply(999000));

//        System.out.println(new BigInteger("98798769567857456654876").pow(10));

    }
}
