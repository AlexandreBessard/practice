package patternsForCodingInterviews.slidingWindow;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1627871358579_1Unit
public class MaximumSumSubarrayOfSizeK {
    /*
    Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any contiguous subarray of size ‘k’.
     */
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 1, 3, 2};
        int k = 3;
        //Output 9
        //Explanation: Subarray with maximum sum is [5, 1, 3].
        //System.out.println(findMaxSumSubArrayBrutForce(k, nums));
        System.out.println(findMaxSumSubArraySlidingWindow(k, nums));
    }

    //SLIDING WINDOW
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int findMaxSumSubArraySlidingWindow(int k, int[] arr) {
        int windowSum = 0, maxSum = 0;
        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if(windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart]; //Substract element going out
                windowStart++; // Slide the window ahead
            }
        }
        return maxSum;
    }


    //Brut force
    public static int findMaxSumSubArrayBrutForce(int k, int[] arr) {
        int maxSum = 0, windowSum;
        for(int i = 0; i <= arr.length - k; i++) {
            windowSum = 0;
            for(int j = i; j < i + k; j++) {
                windowSum += arr[j];
            }
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }
}
