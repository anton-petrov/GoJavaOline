package edu.petrov.gojavaonline.calculator;

/**
 * Created by anton on 05/04/16.
 */

public class Calculator {

    private static String A = "911234567100000000000000000";
    private static String B = "123456789112345689123456789112345671";

    private static BigInteger a = new BigInteger(A);
    private static BigInteger b = new BigInteger(B);


    public static void main(String[] args) {
//        System.out.println(new BigInteger(T1).karatsubaMultiply(new BigInteger(T2)));
//        System.out.println(t1.multiply(t2));


        System.out.println(a.Low());
        System.out.println(a.High());

        System.out.println(new BigInteger(A).multiplyKaratsuba(new BigInteger(B)));
        System.out.println(new BigInteger(A).multiply(new BigInteger(B)));

        BigInteger b = new BigInteger("233333333332323123123123124355");
        b.shiftDigitsLeft(3);
        System.out.println(b);

//        System.out.println(new BigInteger(T1).add(new BigInteger(T2)));
//        System.out.println(t1.add(t2));
    }
}

/*


*/
