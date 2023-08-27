package leetcode.leetcode75.designGurus.twoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PairWithTargetSum {

    /*
    Given an array of numbers sorted in ascending order and a target sum,
    find a pair in the array whose sum is equal to the given target.
     */
    public static void main(String[] args) {
        int[] input1 = new int[]{1, 2, 3, 4, 6};
        int target = 6;
        System.out.println(Arrays.toString(search(input1, target)));

        System.out.println(Arrays.toString(searchUsingHashMap(input1, target)));
    }

    /*
    Using a Hashmap
    Time: O(N) where N is the total element of the array.
    Space: O(N) worst case if we push all elements to the hashmap.
     */
    public static int[] searchUsingHashMap(int[] arr, int targetSum) {
        //Key: value, Value: index where the value is located from the array.
        Map<Integer, Integer> nums = new HashMap<>(arr.length);
        for (int actualIndex = 0; actualIndex < arr.length; actualIndex++) {
            int remaining = targetSum - arr[actualIndex];
            if (nums.containsKey(remaining)) {
                return new int[]{nums.get(remaining), actualIndex};
            } else {
                nums.put(arr[actualIndex], actualIndex);
            }
        }
        return new int[]{-1, -1};
    }

    /*
    Array must be sorted in ascending order
    Time: O(N) where N is the total number of elements in the given array.
    Space: O(1)
     */
    public static int[] search(int[] arr, int targetSum) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (currentSum == targetSum) {
                return new int[]{left, right};
            }
            if (targetSum > currentSum) {
                //Pair with a bigger element
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

}
