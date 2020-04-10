package cz.kylberger.sort;

public interface Sort {

    void sort(int[] arr);

    default void swap(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

}
