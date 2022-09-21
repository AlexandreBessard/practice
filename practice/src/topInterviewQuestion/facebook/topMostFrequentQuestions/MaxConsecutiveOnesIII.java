package topInterviewQuestion.facebook.topMostFrequentQuestions;

//https://leetcode.com/problems/max-consecutive-ones-iii/
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        System.out.println(longestOnes(nums, k));
    }

    //Approach 1: Sliding Window
    /*
    Time: O(N)
    Space: O(1)
     */
    static int longestOnes(int[] nums, int k) {
        int windowStart = 0;
        int kthOfZeros = k;
        int maxLength = Integer.MIN_VALUE;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if (nums[windowEnd] == 0) {
                kthOfZeros--;
            }
            //Shrink the window
            if (kthOfZeros == 0) {
                maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
                kthOfZeros++;
                windowStart = windowEnd;
            }
        }
        return maxLength;
    }

}
