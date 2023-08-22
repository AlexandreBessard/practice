package leetcode.leetcode75.twoPointers.designGurus;

import java.util.Arrays;

public class TripletSumCloseToTarget {

    public static void main(String[] args) {
        int[] input1 = new int[]{-1, 0, 2, 3};
        int target = 3;
        // Result -> target - smallest difference
        System.out.println(searchTriplets(input1, target));
    }

    /*
    Given an array of unsorted numbers and a target number,
    find a triplet in the array whose sum is as close to the target number as possible,
    return the sum of the triplet.
    If there are more than one such triplet, return the sum of the triplet with the smallest sum.

    Time: O(N * logN + N2) -> equivalent to O(N2)
    Space: O(N) used by the sorting algorithm.
     */
    public static int searchTriplets(int[] arr, int targetSum) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(arr); //Time complexity: O(N * logN)
        int smallestDifference = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int targetDiff = targetSum - arr[i] - arr[left] - arr[right];
                //Found triplet
                if (targetDiff == 0) {
                    return targetSum;
                }
                //Smallest sum when we have multiple solution
                if (Math.abs(targetDiff) < Math.abs(smallestDifference) //Found the closest difference
                        || (Math.abs(targetDiff) == Math.abs(smallestDifference)
                        //targetDiff is greater (meaning it's a larger sum that is equally close)
                        && targetDiff > smallestDifference)) {
                    smallestDifference = targetDiff;
                }
                if (targetDiff > 0) {
                    left++; // Need a triplet with bigger sum
                } else {
                    right--;
                }
            }
        }
        return targetSum - smallestDifference;
    }
}
