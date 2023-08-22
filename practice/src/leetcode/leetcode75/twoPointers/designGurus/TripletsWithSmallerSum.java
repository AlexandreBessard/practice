package leetcode.leetcode75.twoPointers.designGurus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletsWithSmallerSum {

    public static void main(String[] args) {
        int[] input1 = new int[]{-1, 4, 2, 1, 3};
        int target = 5;
        System.out.println(searchTriplets(input1, target));
        System.out.println(searchTripletsReturningList(input1, target));
    }

    /*
    Problem:
    Write a function to return the list of all such triplets instead of the count.
    How will the time complexity change in this case?

    Time: O(n log + N3) -> O(N3)
    Space: O(N) which is required for sorting.
     */
    public static List<List<Integer>> searchTripletsReturningList(int[] arr, int target) {
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(arr); //Time: O(N * log N)
        for (int i = 0; i < arr.length - 2; i++) { //Time: O(N)
            searchTriplets(triplets, target - arr[i], i, arr);
        }
        return triplets;
    }

    //Time: O(N2) since we have 2 loops
    private static void searchTriplets(List<List<Integer>> triplets, int targetSum, int currentIndex,
                                       int[] arr) {
        int left = currentIndex + 1;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < targetSum) { //Found triplets
                for (int i = right; i > left; i--) {
                    triplets.add(List.of(arr[currentIndex], arr[left], arr[i]));
                    left++;
                }
            } else {
                //Get a smaller number which can be smaller than the targetSum
                right--;
            }
        }
    }

    /*
    Given an array arr of unsorted numbers and a target sum,
    count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j,
    and k are three different indices. Write a function to return the count of such triplets.

    Time: O(N2)
    Space: O(N) used by sorting algorithm.
     */
    public static int searchTriplets(int[] arr, int target) { // -1, 1, 2, 3, 4
        Arrays.sort(arr); //Time: O(N * logN)
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            //Invoke this method for each targetSum
            count += searchPair(arr, target - arr[i], i);
        }
        return count;
    }

    //Time O(N)
    private static int searchPair(int[] arr, int targetSum, int first) {
        int count = 0; //Total of number of triplets found
        int left = first + 1;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < targetSum) {
                //Array is sorted, all the elements between left and right are valid triplets.
                count += right - left;
                left++; // move towards higher numbers in the array
            } else {
                right--;
            }
        }
        return count;
    }

}
