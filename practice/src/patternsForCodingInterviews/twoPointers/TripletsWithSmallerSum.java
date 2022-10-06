package patternsForCodingInterviews.twoPointers;

import java.util.Arrays;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743469029_6Unit
public class TripletsWithSmallerSum {
/*
Given an array arr of unsorted numbers and a target sum, count all triplets in it such
that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices.
Write a function to return the count of such triplets.
 */
    public static void main(String[] args) {
        System.out.println(
                searchTriplets(new int[] { -1, 0, 2, 3 }, 6));
        /*
        System.out.println(
                searchTriplets(new int[] { -1, 4, 2, 1, 3 }, 5));

         */
    }

    public static int searchTriplets(int[] arr, int target) {
        Arrays.sort(arr);
        int count = 0;
        for(int i = 0; i < arr.length - 2; i++) {
            int targetSum = target - arr[i];
            count += searchPair(arr, targetSum, i);
        }
        return count;
    }
    private static int searchPair(int[] arr, int targetSum, int first) {
        int count = 0;
        int left = first + 1;
        int right = arr.length - 1;
        while(left < right) {
            if(arr[left] + arr[right] < targetSum) { //Found triplet
                // since arr[right] >= arr[left], therefore, we can replace arr[right] by any
                // number between left and right to get a sum less than the target sum
                count += right - left; //Count elements between left and right because : [left] <= arr[left] (sorted array)
                left++;
            } else {
                right--; //We need to pair with smaller number
            }
        }
        return count;
    }


}
