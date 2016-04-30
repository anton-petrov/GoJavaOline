package edu.petrov.gojavaonline.calculator;

public class Calculator {

    public static void main(String[] args) {

        //Integer.toBinaryString();

//        System.out.format("%s + %s = %s\n", a, b, a.add(b));
//        System.out.format("%s - %s = %s\n", a, b, a.subtract(b));
//        System.out.format("%s * %s = %s\n", a, b, a.multiply(b));
//        System.out.format("%s / %s = %s\n", a, b, a.divide(b));
//        System.out.format("%s / %s = %s\n", a, 2, a.divide(new BigInteger(2)));
//        System.out.format("%s^16=%s\n", a, a.pow(new BigInteger("16")));

        final BigInteger bigInteger = new BigInteger("10");


        // System.out.println(bigInteger.pow(new BigInteger("10000")));
        // System.out.println(bigInteger.pow(100000).toString().length());
        System.out.println(new BigInteger("-3").add(new BigInteger("2")));


    }
}