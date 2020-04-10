package cz.kylberger.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {

    private final int[] testArray1 = new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
    private final int[] testArray1Sorted = new int[]{1, 3, 4, 5, 6, 8, 9, 10, 13, 15, 17};
    private final int[] testArray2 = new int[]{1, 5, 3, 30078, 4, 2};
    private final int[] testArray2Sorted = new int[]{1, 2, 3, 4, 5, 30078};

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
        sortAlg.sort(testArray1);
        assertArrayEquals(testArray1Sorted, testArray1);
        sortAlg.sort(testArray2);
        assertArrayEquals(testArray2Sorted, testArray2);
    }

    private void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
