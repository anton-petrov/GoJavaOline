package edu.petrov.gojavaonline.module05;

import java.util.Comparator;

/**
 * Created by anton on 11/03/16.
 */
public class ArrayUtils {

    public static Comparator<Integer> ASCENDING_INTEGER = (a, b) -> a < b ? -1 : (a > b ? 1 : 0);

    public static Comparator<Integer> DESCENDING_INTEGER = (a, b) -> a > b ? -1 : (a < b ? 1 : 0);

    /**
     *
     * @param array
     * @return max element of the array
     */
    public static int findMax(Integer[] array) {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < array.length - 1; i++) {
            maxValue = Math.max(array[i], array[i+1]);
        }
        return maxValue;
    }

    public static <T> void quickSort(T[] array, Comparator<? super T> c) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSort(array, 0, array.length - 1, c);
    }

    private static <T> void quickSort(T[] array, int leftIndex, int rightIndex, Comparator<? super T> c) {
        int i = leftIndex;
        int j = rightIndex;
        T pivot = array[i + (j - i) / 2];
        while (i <= j) {
            while(c.compare(array[i], pivot) < 0) {
                i++;
            }
            while(c.compare(array[j], pivot) > 0) {
                j--;
            }
            if(i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (leftIndex < j) {
            quickSort(array, leftIndex, j, c);
        }
        if(rightIndex > i) {
            quickSort(array, i, rightIndex, c);
        }
    }

    public static <T> void reverse(T[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            swap(array, i, array.length - i - 1);
        }
    }

    public static <T> void bubbleSort(T array[], Comparator<? super T> c) {
        if (array == null || array.length == 0) {
            return;
        }
        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (c.compare(array[i], array[k]) > 0) {
                    swap(array, i, k);
                }
            }
        }
    }

    public static <T> void insertionSort(Comparable[] array) {
        int N = array.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--) {
                swap(array, j, j - 1);
            }
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

}
