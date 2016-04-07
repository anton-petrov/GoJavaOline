package edu.petrov.gojavaonline.calculator;

import java.util.Arrays;

/**
 * Created by anton on 05/04/16.
 */

public class Calculator {

    private static String A = "910463872391046387239104638723";
    private static String B = "1850009341";

    private static String C = "900000000000000000";
    private static String D = "3000000000";
    private static String T1 = "3000000000";
    private static String T2 = "3000000000";

    private static java.math.BigInteger bigIntegerA = new java.math.BigInteger(A);
    private static java.math.BigInteger bigIntegerB = new java.math.BigInteger(B);
    private static java.math.BigInteger bigIntegerC = new java.math.BigInteger(C);
    private static java.math.BigInteger bigIntegerD = new java.math.BigInteger(D);

    private static BigInteger a = BigInteger.parseBigInteger(A);
    private static BigInteger b = BigInteger.parseBigInteger(B);
    private static BigInteger c = new BigInteger(C);
    private static BigInteger d = new BigInteger(D);

    private static BigInteger t1 = new BigInteger(T1);
    private static BigInteger t2 = new BigInteger(T2);


    public static void main(String[] args) {


//        System.out.println(bigIntegerA.shiftLeft(1));


//        System.out.format("%s * %s = %s\n", t1, t2, t1.multiply(t2));

//        System.out.format("%s * %s = %s\n", bigIntegerA, bigIntegerB, bigIntegerA.multiply(bigIntegerB));
//        System.out.format("%s * %s = %s\n", a, b, a.multiply(b));

        System.out.format("%s * %s = %s\n", bigIntegerC, bigIntegerD, bigIntegerC.multiply(bigIntegerD));
        System.out.format("%s * %s = %s\n", c, d, c.multiply(d));
//
//        System.out.format("%s / %s = %s\n", bigIntegerC, bigIntegerD, bigIntegerC.divide(bigIntegerD));
//        System.out.format("%s / %s = %s\n", c, d, c.divide(d));

        final int divider = 23847823;

        System.out.format("%s / %d = %s\n", bigIntegerA, divider, bigIntegerA.divide(new java.math.BigInteger(Integer.toString(divider))));
        System.out.format("%s / %d = %s\n", a, divider, a.divide(divider));
//
        System.out.format("%s / %s = %s\n", bigIntegerA, bigIntegerB, Arrays.toString(bigIntegerA.divideAndRemainder(bigIntegerB)));
        System.out.format("%s / %s = %s\n", a, b, Arrays.toString(a.divideAndRemainder(b)));
//

//        System.out.println(new BigInteger("9879876956785745665487600").pow(10));

        System.out.println(new BigInteger().Low());
        System.out.println(new BigInteger().High());

        //Arrays.copyOfRange()
    }
}
