package leetcode.leetcode75.twoPointers.designGurus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletSumToZero {

    public static void main(String[] args) {
        int[] input1 = new int[]{-5, 2, -1, -2, 3};
        System.out.println(searchTriplets(input1));

    }

    /*
    Given an array of unsorted numbers, find all unique triplets in it that add up to zero.

    Time: O(N * logN) because we are soring the array.
    The searchPair function is O(N * logN + N2) equivalent to O(N2)
    Space: if ignoring the required space for the result, it takes O(N) which is required for sorting.
     */
    public static List<List<Integer>> searchTriplets(int[] arr) {
        //Have to sort the array
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            //Skip same element to avoid duplicate.
            //Must find all UNIQUE elements
            if (i > 0 && arr[i - 1] == arr[i]) { //Edge case for ex: {-5, -5, -5, 2}
                continue;
            }
            // If the targetSum is positive, it stays positive else if negative, it becomes positive.
            searchPair(arr, -arr[i], i + 1, triplets);
        }

        return triplets;
    }

    /*
    Time: O(N)
     */
    private static void searchPair(int[] arr, int targetSum, int left, List<List<Integer>> triplets) {
        int right = arr.length - 1;
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (currentSum == targetSum) {
                //Find triplet.
                triplets.add(List.of(-targetSum, arr[left], arr[right]));
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1]) {
                    //If the next element is the same as previous, skip it
                    left++;
                }
                while (left < right && arr[right] == arr[right + 1]) {
                    right--;
                }
            } else if (targetSum > currentSum) {
                left++; //Need to pair with a bigger element.
            } else {
                right--;
            }
        }
    }
}
