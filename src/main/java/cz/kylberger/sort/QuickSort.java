package cz.kylberger.sort;

import java.util.Random;

public class QuickSort implements Sort {

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int start, int end) {
        // no need to sort one element array
        if (start == end)
            return;

        // reorganize input array to [pivot|M1|M2], where M1 members < pivot < M2 members
        // initially, M1 is empty and M2 includes all elements except pivot, so array is [pivot|M2]
        // select random element as pivot and move it to start of the array to prevent bad performance on sorted arrays
        int randomPivotIndex = new Random().nextInt(end - start + 1) + start;
        swap(arr, start, randomPivotIndex);
        int pivot = arr[start];
        int m1Size = 0;
        // process rest of elements
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < pivot) {
                // move element from M2 to M1 by swapping it with first element of M2 and incrementing M1 size by 1
                m1Size++;
                swap(arr, start + m1Size, i);
            }
        }

        // move pivot between M1 and M2 [M1|pivot|M2] by swapping it with last element of M1 (if M1 is not empty)
        if (m1Size > 0) {
            swap(arr, start, start + m1Size);
        }
        // sort M1  (if M1 size > 1)
        if (m1Size > 1) {
            quickSort(arr, start, start + m1Size - 1);
        }
        // sort M2 (if M2 size > 1)
        if (start + m1Size < end - 1) {
            quickSort(arr, start + m1Size + 1, end);
        }
    }

}
