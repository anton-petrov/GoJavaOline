package edu.petrov.gojavaonline.calculator;

public class Calculator {

//    private static String A = "12345678912345678912345678901234567891234567891234567890";
//    private static String B = "19664839365930674759475";
//    private static BigInteger a = new BigInteger(A);
//    private static BigInteger b = new BigInteger(B);


    public static void main(String[] args) {

//        System.out.format("%s + %s = %s\n", a, b, a.add(b));
//        System.out.format("%s - %s = %s\n", a, b, a.subtract(b));
//        System.out.format("%s * %s = %s\n", a, b, a.multiply(b));
//        System.out.format("%s / %s = %s\n", a, b, a.divide(b));
//        System.out.format("%s / %s = %s\n", a, 2, a.divide(new BigInteger(2)));
//        System.out.format("%s^16=%s\n", a, a.pow(new BigInteger("16")));

        final BigInteger bigInteger = new BigInteger("10");


        // System.out.println(bigInteger.pow(new BigInteger("10000")));
        System.out.println(bigInteger.pow(100000).toString().length());

    }
}