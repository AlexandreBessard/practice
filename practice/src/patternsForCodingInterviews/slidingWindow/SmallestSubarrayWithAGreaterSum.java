package patternsForCodingInterviews.slidingWindow;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628540999042_0Unit
public class SmallestSubarrayWithAGreaterSum {
/*
Given an array of positive numbers and a positive number ‘S,’ find the length of the smallest contiguous subarray
whose sum is greater than or equal to ‘S’. Return 0 if no such subarray exists.
 */
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 2, 3, 2};
        int S = 7;
        //Output 2
        //Explanation: The smallest subarray with a sum greater than or equal to '7' is [5, 2].
        //FIND THE SMALLEST SUBARRAY (not sum of smallest subarray)
        System.out.println(findMinSubArray(S, nums));
    }

    //SLIDING WINDOW
    /*
    Time complexity: O(N), for loop runs for all elements.
    inner while loop, process each element only once.
    Therefore, O(N + N) which is equivalent to O(N)
    Space complexity: O(1)
     */
    public static int findMinSubArray(int S, int[] arr) {
        int windowSum = 0, windowStart = 0;
        int minLength = Integer.MAX_VALUE;
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            //shrink the window as small as possible until the 'windowSum' is smaller than 'S'
            while (windowSum >= S) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= arr[windowStart]; //Substract element going out
                windowStart++; // slide the window ahead
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

}
