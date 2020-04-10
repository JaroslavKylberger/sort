package cz.kylberger.sort;

public class HeapSort implements Sort {

    @Override
    public void sort(int[] arr) {
        heapSort(arr, arr.length);
    }

    // main function to do heap sort
    private void heapSort(int arr[], int n) {
        // build heap
        buildMaxHeap(arr, n);

        // Transform heap to sorted array in-place (using only input array):
        // ------------------------------------------------------
        // use input array for heap (on the left) and sorted array (on the right): arr = [heap | sorted]
        // first all elements are in the heap and sorted array is empty [heap |]
        // in each step:
        // swap root element with last element of the heap and lower the index which divides heap and sorted array by 1
        // (this moves root element from the heap to the start of sorted array and last element of the heap to the root)
        // propagate new root to the right place in the heap
        // repeat this step until the heap is empty and all elements are in the sorted array [| sorted]
        // for example:
        // heap | sorted
        // 7 4 2 3 1 |
        // 4 3 2 1 | 7
        // 3 1 2 | 4 7
        // 2 1 | 3 4 7
        // 1 | 2 3 4 7
        // Output: 1 2 3 4 7
        for (int i = n - 1; i > 0; i--) {
            // swap current root with last node
            swap(arr, 0, i);

            // propagate new root to the right place in the heap
            heapify(arr, i, 0);
        }


    }

    // build max heap
    private void buildMaxHeap(int arr[], int n) {
        // index of last non-leaf node
        int startIdx = (n / 2) - 1;

        // perform reverse level order traversal from last non-leaf node and heapify each node
        for (int i = startIdx; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    // propagate arr[i] to the right place in its subtree
    // by swapping parent and child nodes where parent node is not larger than child nodes
    // n is size of the whole heap
    private void heapify(int arr[], int n, int i) {
        int indexOfLargestValue = i; // initialize index of largest value as parent
        int l = 2 * i + 1; // left child = 2*i + 1
        int r = 2 * i + 2; // right child = 2*i + 2

        // if left child is larger
        if (l < n && arr[l] > arr[indexOfLargestValue])
            indexOfLargestValue = l;

        // if right child is larger
        if (r < n && arr[r] > arr[indexOfLargestValue])
            indexOfLargestValue = r;

        // if root is not largest
        if (indexOfLargestValue != i) {
            swap(arr, i, indexOfLargestValue);

            // recursively process affected sub-tree
            heapify(arr, n, indexOfLargestValue);
        }
    }

}
