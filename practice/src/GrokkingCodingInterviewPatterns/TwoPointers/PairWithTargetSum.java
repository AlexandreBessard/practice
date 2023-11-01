package GrokkingCodingInterviewPatterns.TwoPointers;

import java.util.Arrays;

public class PairWithTargetSum {

    //https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638ca0aa5b41522e8a2e3395

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 6};
        int target = 6;
        System.out.println(Arrays.toString(search(nums, target)));
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    static int[] search(int[] arr, int targetSum) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int current = arr[left] + arr[right];

            if (current == targetSum) {
                return new int[]{left, right};
            } else if (current > targetSum) {
                // need a smaller pair, we have a bigger current value than expected
                right--;
            } else {
                // need a bigger pair, current value is too small
                left++;
            }
        }
        return new int[]{-1, -1};
    }

}
