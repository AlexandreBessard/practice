package algorithms.dividAndConquer;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 2, 8, 7, 6, 4};
        quickSort(nums);
        for(int i : nums) {
            System.out.print(i + ", ");
        }
    }

    /* Sorts an array in the ascending order in O(n log n) time */
    /*
    Based on Divide and Conquer strategy
    Unstable.
    Quicksort is preferred for arrays and works better for small array.
    On average, it is O(N log N), but O(N2) in the worst case.
    Time: O(NlogN)
    Space(O(1)
     */
    public static void quickSort(int[] lst) {
        int n = lst.length;
        qsort(lst, 0, n - 1);
    }
    private static void qsort(int[] lst, int lo, int hi) {
        if(lo < hi) {
            int p = partition(lst, lo, hi); //return p (index) where all elements from p are smaller than the pivot
            qsort(lst, lo, p - 1);
            qsort(lst, p + 1, hi);
        }
    }
    private static int partition(int[] lst, int lo, int hi) {
        /* Picks the last element hi as a pivot and returns the index of pivot value in the sorted array */
        int pivot = lst[hi];
        int i = lo;
        for(int j = lo; j < hi; j++) {
            if(lst[j] < pivot) { //True -> Swap element smaller than the pivot on the left
                //swap
                int tmp = lst[i];
                lst[i] = lst[j];
                lst[j] = tmp;
                i++;
            }
        }
        int tmp = lst[i];
        lst[i] = lst[hi];
        lst[hi] = tmp;
        return i;
    }
}
