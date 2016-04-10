package edu.petrov.gojavaonline.calculator;

public class Calculator {

    private static String A = "1234530";
    private static String B = "1";

    private static BigInteger a = new BigInteger(A);
    private static BigInteger b = new BigInteger(B);

    private final java.math.BigInteger test = new java.math.BigInteger("1");


    public static void main(String[] args) {

        System.out.println(new BigInteger("10").subtract(new BigInteger("2000000000")) + "\n");

        System.out.println(new BigInteger(A).multiplyKaratsuba(new BigInteger(B)));
        System.out.println(new BigInteger(A).multiply(new BigInteger(B)));

        System.out.println(new BigInteger("123456789123456789123456789123456789123456789123456789123456789123456789123456789").stringValue(25));
    }
}