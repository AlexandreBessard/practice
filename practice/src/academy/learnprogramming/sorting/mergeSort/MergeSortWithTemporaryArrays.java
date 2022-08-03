package academy.learnprogramming.sorting.mergeSort;
//https://www.baeldung.com/java-merge-sort
public class MergeSortWithTemporaryArrays {

    public static void main(String[] args) {

        int[] nums = {5, 1, 6, 2, 3, 4};
        mergeSort(nums, nums.length);
        for(int i : nums) {
            System.out.print(i + ", ");
        }

    }

    public static void mergeSort(int[] nums, int n) {
        if(n < 2) //Only one element in the array
            return;
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        for(int i = 0; i < mid; i++) {
            l[i] = nums[i];
        }
        for(int i = mid; i < n; i++) {
            r[i - mid] = nums[i];
        }
        mergeSort(l, mid); //Left side
        mergeSort(r, n - mid); //Right side
        merge(nums, l, r, mid, n - mid);
    }
    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
