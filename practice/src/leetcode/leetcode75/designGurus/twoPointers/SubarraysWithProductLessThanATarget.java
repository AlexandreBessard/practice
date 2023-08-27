package leetcode.leetcode75.designGurus.twoPointers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubarraysWithProductLessThanATarget {

    public static void main(String[] args) {
        int[] input1 = new int[]{2, 5, 3, 10};
        int target = 30;
        System.out.println(findSubarrays(input1, target));
    }

    /*
    Sliding Window

    Given an array with positive numbers and a positive target number,
    find all of its contiguous subarrays whose product is less than the target number.

    Time: O(N3) since we have 2 for-loops and 1 while-loop
    Space: O(N) which is used for the tempList.
     */
    public static List<List<Integer>> findSubarrays(int[] arr, int target) { // 2, 5, 3, 10
        List<List<Integer>> result = new ArrayList<>();
        double product = 1;
        int left = 0;
        //Right boundary of the current array
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];
            while (product > target
                    //means we still have element left.
                    && left < arr.length) {
                //Has to be continuous
                //Remove the current element from the product
                product /= arr[left++];
            }
            //Create temporary list to store the current subarray.
            LinkedList<Integer> tempList = new LinkedList<>();
            // Iterate from right to  and all of these subarrays to the result.
            for (int i = right; i >= left; i--) {
                //Add the current element at the beginning of the list
                tempList.addFirst(arr[i]);
            }
            //Add the current subaray to the list
            result.add(tempList);
        }
        return result;
    }

}
