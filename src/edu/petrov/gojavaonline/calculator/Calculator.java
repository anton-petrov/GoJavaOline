package edu.petrov.gojavaonline.calculator;

/**
 * Created by anton on 05/04/16.
 */

public class Calculator {

    private static String A = "3345678991";
    private static String B = "3122999009";

    private static java.math.BigInteger bigIntegerA = new java.math.BigInteger(A);
    private static java.math.BigInteger bigIntegerB = new java.math.BigInteger(B);

    private static BigInteger a = BigInteger.parseBigInteger(A);
    private static BigInteger b = BigInteger.parseBigInteger(B);
    private static BigInteger c = new BigInteger("99");
    private static BigInteger d = new BigInteger("100");


    public static void main(String[] args) {
        System.out.println(b.add(a));

        a = BigInteger.parseBigInteger(A);
        b = BigInteger.parseBigInteger(B);
        System.out.println("OK =   \t" + bigIntegerA.subtract(bigIntegerB));
        System.out.println("RESULT = " + a.subtract(b));

        System.out.println(c);

        System.out.println(c.compareTo(d));

        System.out.println("99 - 100 = " + c.subtract(d));

        long x = (long) Integer.MAX_VALUE * Integer.MAX_VALUE;

        System.out.format("a=%s b=%s\n", a.toString(), b.toString());

        System.out.println("a*b=" + a.multiply(b).toString());
    }
}
