package edu.petrov.gojavaonline.module05;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Integer[] test = {1, 3, -7, 0, 189, 3, 300};
        Integer a = ArrayUtils.findMax(test);

        System.out.println(a);

        ArrayUtils.quickSort(test, ArrayUtils.ASCENDING_INTEGER);

        System.out.println(Arrays.toString(test));
    }
}
