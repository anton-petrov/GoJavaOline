package edu.petrov.gojavaonline.calculator;

/**
 * Created by anton on 05/04/16.
 */

public class Calculator {

    private static String A = "1234530";
    private static String B = "1";

    private static BigInteger a = new BigInteger(A);
    private static BigInteger b = new BigInteger(B);

    private final java.math.BigInteger test = new java.math.BigInteger("1");


    public static void main(String[] args) {

//        System.out.println(new BigInteger("-123").subtract(new BigInteger("23")));

//        System.out.println("a = " + a);
//        System.out.println("a.Low() = " + a.getLow());
//        System.out.println("a.High() = " + a.getHigh());
//
        System.out.println(new BigInteger(A).multiplyKaratsuba(new BigInteger(B)));
        System.out.println(new BigInteger(A).multiply(new BigInteger(B)));

    }
}

/*


*/
