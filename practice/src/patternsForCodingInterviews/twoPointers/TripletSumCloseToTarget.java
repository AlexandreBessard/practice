package patternsForCodingInterviews.twoPointers;

import java.util.Arrays;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743457252_5Unit
public class TripletSumCloseToTarget {
    /*
    Given an array of unsorted numbers and a target number, find a triplet in the array whose sum is as close to the
    target number as possible, return the sum of the triplet. If there are more than one such triplet,
    return the sum of the triplet with the smallest sum.
     */
    public static void main(String[] args) {
        System.out.println(searchTriplet(new int[] { -2, 0, 1, 2 }, 2));
        System.out.println(searchTriplet(new int[] { -3, -1, 1, 2 }, 1));
        System.out.println(searchTriplet(new int[] { 1, 0, 1, 1 }, 100));
    }

    /*
    Time: O(N log N), the function will take O(N * log N + N²) -> O(N²)
    Space:  O(N) caused by sorted array
     */
    public static int searchTriplet(int[] arr, int targetSum) {
        if(arr == null || arr.length < 3)
            throw new IllegalArgumentException();
        Arrays.sort(arr);
        int smallestDifference = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while(left < right) {
                //Comparing the sum of three numbers to the targetSum
                int targetDiff = targetSum - arr[i] - arr[left]- arr[right];
                if(targetDiff == 0) { // found triplet with exact numbers
                    return targetSum; //Return sum of all numbers
                }
                //Second part, find smallest difference
                if (Math.abs(targetDiff) < Math.abs(smallestDifference)
                        || (Math.abs(targetDiff) == Math.abs(smallestDifference)
                        && targetDiff > smallestDifference))
                {
                    //Save the closest and the biggest difference.
                    smallestDifference = targetDiff;
                }
                if(targetDiff > 0) {
                    left++; //Need triplet with a bigger sum
                } else {
                    //Remove too much, we need smaller number (sorted array)
                    right--;  //Need triplet with smaller sum
                }
            }
        }
        return targetSum - smallestDifference; //smallestDiff -> sum of this triplet
    }
}
