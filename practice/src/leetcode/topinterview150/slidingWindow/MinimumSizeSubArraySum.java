package leetcode.topinterview150.slidingWindow;

public class MinimumSizeSubArraySum {


    // https://leetcode.com/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        // Return the minimal length of subarray whose sum is greater than
        // or equal to the target.
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, nums));
        nums = new int[]{2, 1, 5, 2, 3, 2};
        System.out.println(minSubArrayLen(7, nums));
        nums = new int[]{1, 4, 4};
        System.out.println(minSubArrayLen(4, nums));
    }

    /*
    Sliding window
    Time: O(n)
    Space: O(1)
     */
    static int minSubArrayLen(int target, int[] nums) {
        int windowStart = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            // Add the next element to the current sum
            currentSum += nums[windowEnd];

            // Shrink the window while the sum is greater than or equal to the target
            while (currentSum >= target) {
                // Update the minimum length
                minLength = Math.min(minLength, windowEnd - windowStart + 1);

                // Remove the leftmost element from the sum and slide the window ahead
                currentSum -= nums[windowStart];
                windowStart++;
            }
        }
        // If minLength is not updated, return 0; otherwise, return the calculated minLength
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}