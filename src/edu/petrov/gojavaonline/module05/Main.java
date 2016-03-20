package edu.petrov.gojavaonline.module05;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by anton on 11/03/16.
 */

class InternetDownloader {

    public static byte[] getSite(String url) throws IOException {
        return new String("<html><body>Nothing! It's stub!</body></html>").getBytes();
    }
}

public class Main {
    public static void main(String[] args) {

        Integer[] test = {1,3,-7,0,189,3,300};
        Integer a = ArrayUtils.findMax(test);

        System.out.println(a);

        ArrayUtils.quickSort(test, ArrayUtils.ASCENDING_INTEGER);

        System.out.println(Arrays.toString(test));
    }
}
