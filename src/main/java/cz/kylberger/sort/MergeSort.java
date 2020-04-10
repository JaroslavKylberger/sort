package cz.kylberger.sort;

public class MergeSort implements Sort {

    private boolean inPlace;

    public MergeSort(boolean inPlace) {
        this.inPlace = inPlace;
    }

    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int start, int end) {
        int size = end - start + 1;
        if (size < 2) {
            // one or no element array - no need to sort
            return;
        } else if (size == 2) {
            // two elements array - only swap elements if needed
            if (arr[start] > arr[end]) {
                swap(arr, start, end);
            }
        } else { // size > 2
            // - split unsorted array [start...end] into two [start...mid][mid+1...end]
            // - sort each of them separately
            // - merge them back into one sorted array
            int mid = (end - start) / 2 + start;
            // sort first array
            mergeSort(arr, start, mid);
            // sort second array
            mergeSort(arr, mid + 1, end);
            // merge sorted arrays
            if (inPlace) {
                mergeInPlace(arr, start, mid, end);
            } else {
                merge(arr, start, mid, end);
            }
        }
    }

    private void merge(int[] arr, int start, int mid, int end) {
        // this is faster (in average case) but consumes more memory ((end-start+1)*sizeOf(int) additional bytes)
        // output array for merged elements
        int[] outArr = new int[end - start + 1];
        // output array position
        int index = 0;
        // first array position
        int index1 = start;
        // second array position
        int index2 = mid + 1;
        while (index1 <= mid && index2 <= end) {
            if (arr[index1] <= arr[index2]) { // first array element is smaller or equal, consume it
                outArr[index] = arr[index1];
                index1++;
                index++;
            } else { // second array element is smaller, consume it
                outArr[index] = arr[index2];
                index2++;
                index++;
            }
        }
        if (index1 > mid) { // first array is consumed, consume second array
            while (index2 <= end) {
                outArr[index] = arr[index2];
                index2++;
                index++;
            }
        } else if (index2 > end) { // second array is consumed, consume first array
            while (index1 <= mid) {
                outArr[index] = arr[index1];
                index1++;
                index++;
            }
        }
        // copy merged elements back to input array
        for (int i = 0; i < outArr.length; i++) {
            arr[start + i] = outArr[i];
        }
    }

    private void mergeInPlace(int[] arr, int start, int mid, int end) {
        // this is slower (in average case) but consumes no additional memory
        int index1 = start;
        int index2 = mid + 1;
        while (index1 <= mid && index2 <= end) {
            // compare first array element and second array element
            // if first array element is smaller or equal, it is in the right place
            // if second array element is smaller
            // - move it to current position in first array
            // - shift all elements between first array current position and second array current position right by 1
            // - increment first array end by 1
            // - increment second array current position
            if (arr[index2] < arr[index1]) {
                int value = arr[index2];
                for (int i = index2; i > index1; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[index1] = value;
                // increment first array end
                mid++;
                // increment second array position
                index2++;
            }
            // increment first array position
            index1++;
        }
    }

}
