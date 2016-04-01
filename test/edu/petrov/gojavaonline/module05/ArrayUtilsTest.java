package edu.petrov.gojavaonline.module05;

import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayUtilsTest {

    private final Integer[] sortedArray1 = {-5, 0, 1, 2, 2, 3, 7, 11, 25, 100};
    private final Integer[] sortedArray2 = {-1, -1, 0, 0, 1, 1, 2, 2, 3, 10000};
    private Integer[] unsortedArray1Copy;
    private Integer[] unsortedArray1 = {3, 1, 2, 2, 100, -5, 7, 0, 11, 25};
    private Integer[] unsortedArray2Copy;
    private Integer[] unsortedArray2 = {0, 1, 2, 3, 2, 1, 0, -1, -1, 10000};
    private Integer[] unsortedArray3Copy;
    private Integer[] unsortedArray3 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    private Integer[] sortedArray3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    @AfterClass
    public static void AfterClass() {

    }

    @BeforeClass
    public static void BeforeClass() {

    }

    @Before
    public void Before() {
        // copy unsorted data to temporary array
        unsortedArray1Copy = Arrays.copyOf(unsortedArray1, unsortedArray1.length);
        unsortedArray2Copy = Arrays.copyOf(unsortedArray2, unsortedArray2.length);
        unsortedArray3Copy = Arrays.copyOf(unsortedArray3, unsortedArray3.length);

    }

    @After
    public void After() {
        // restore unsorted data for future using
        unsortedArray1 = Arrays.copyOf(unsortedArray1Copy, unsortedArray1Copy.length);
        unsortedArray2 = Arrays.copyOf(unsortedArray2Copy, unsortedArray2Copy.length);
        unsortedArray3 = Arrays.copyOf(unsortedArray3Copy, unsortedArray3Copy.length);
    }

    @Test(timeout = 100)
    public void findMax() throws Exception {
        assertEquals(100, ArrayUtils.findMax(sortedArray1));
    }

    @Test(timeout = 100)
    public void quickSort() throws Exception {
        ArrayUtils.quickSort(unsortedArray1, ArrayUtils.ASCENDING_INTEGER);
        assertArrayEquals(sortedArray1, unsortedArray1);

        ArrayUtils.quickSort(unsortedArray2, ArrayUtils.ASCENDING_INTEGER);
        assertArrayEquals(sortedArray2, unsortedArray2);

        ArrayUtils.quickSort(unsortedArray3, ArrayUtils.ASCENDING_INTEGER);
        assertArrayEquals(sortedArray3, unsortedArray3);
    }

    @Test(timeout = 100)
    public void reverse() throws Exception {
        ArrayUtils.reverse(unsortedArray3);
        assertArrayEquals(sortedArray3, unsortedArray3);

    }

    @Test(timeout = 100)
    public void bubbleSort() throws Exception {
        ArrayUtils.bubbleSort(unsortedArray1, ArrayUtils.ASCENDING_INTEGER);
        assertArrayEquals(sortedArray1, unsortedArray1);
    }

    @Test(timeout = 100)
    public void insertionSort() throws Exception {
        ArrayUtils.insertionSort(unsortedArray1);
        assertArrayEquals(sortedArray1, unsortedArray1);
    }
}