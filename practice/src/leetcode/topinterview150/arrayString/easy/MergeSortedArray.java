package leetcode.topinterview150.arrayString.easy;

import java.util.Arrays;

public class MergeSortedArray {

    //https://leetcode.com/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        nums1 = new int[]{1, 2, 3, 0, 0, 0};
        nums2 = new int[]{2, 5, 6};
        mergeTwoPointers(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    /*
    Two pointers
    Time: O(m + n), we are iterating through both arrays once.
    Space: O(1)
     */
    static void mergeTwoPointers(int[] nums1, int m, int[] nums2, int n) {
        int pNums1 = m - 1;  // latest element sorted from the first array
        int pNums2 = n - 1;
        int end = m + n - 1; // latest element from the biggest array
        while (pNums2 >= 0) { // True if with sill have element to process from the smaller array
            // if element from the first array is greater, placed it to the end
            if (pNums1 >= 0 && nums1[pNums1] > nums2[pNums2]) {
                nums1[end--] = nums1[pNums1--];
            } else {
                nums1[end--] = nums2[pNums2--];
            }
        }
    }

    /*
    Time: O(n log n) -> Quick sort algorithm used by sort()
    Space: O(1)
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);
    }

}
