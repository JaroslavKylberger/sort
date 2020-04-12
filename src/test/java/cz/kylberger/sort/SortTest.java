package cz.kylberger.sort;

import org.junit.*;
import org.junit.rules.TestName;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {

    public static final int TEST_ARRAY_SIZE = 10000;
    private static int[] testArray;
    private static int[] testArraySorted;

    @Rule
    public final TestName name = new TestName();

    @BeforeClass
    public static void beforeClass() {
        testArray = new int[TEST_ARRAY_SIZE];
        Random rd = new Random();
        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            testArray[i] = rd.nextInt();
        }
        testArraySorted = cloneArray(testArray);
        long startTime = System.nanoTime();
        Arrays.sort(testArraySorted);
        long endTime = System.nanoTime();
        System.out.println(String.format("%,d elements", TEST_ARRAY_SIZE));
        System.out.println(String.format("%-30s: %,10d  nanos", "Arrays.sort", (endTime - startTime)));
    }

    private static int[] cloneArray(int[] arr) {
        int[] arrCopy = new int[arr.length];
        System.arraycopy(arr, 0, arrCopy, 0, arr.length);
        return arrCopy;
    }

    @Test
    public void quickSortTest() {
        sortTest(new QuickSort());
    }

    @Test
    public void heapSortTest() {
        sortTest(new HeapSort());
    }

    @Test
    public void mergeSortInPlaceTest() {
        sortTest(new MergeSort(true));
    }

    @Test
    public void mergeSortNotInPlaceTest() {
        sortTest(new MergeSort(false));
    }

    private void sortTest(Sort sortAlg) {
        int[] arr = cloneArray(testArray);
        long startTime = System.nanoTime();
        sortAlg.sort(arr);
        long endTime = System.nanoTime();
        assertArrayEquals(arr, testArraySorted);
        System.out.println(String.format("%-30s: %,10d  nanos", name.getMethodName(), (endTime - startTime)));
    }

    private void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
