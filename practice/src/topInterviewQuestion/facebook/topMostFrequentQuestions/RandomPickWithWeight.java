package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.Random;

//https://leetcode.com/problems/random-pick-with-weight/
public class RandomPickWithWeight {

    public static void main(String[] args) {
        int[] nums1 = {2, 5, 3, 4};
        Solution solution = new Solution(nums1);
        System.out.println(solution.pickIndex());
    }

    //Binary Search
    //Approach 2: Prefix Sums with Binary Search

    static class Solution {
        //Space: O(n)
        private int[] prefixSums;
        private int totalSum;
        private final Random random;

        //Time: O(n)
        public Solution(int[] w) {
            this.random = new Random();
            this.prefixSums = new int[w.length];
            int prefixSum = 0;
            for (int i = 0; i < w.length; i++) {
                prefixSum += w[i];
                this.prefixSums[i] = prefixSum;
            }
            this.totalSum = prefixSum;
        }

        /*
        In the solution we are actually using the width of each range as the weight of the indexes
        (accumulated sum increases more with a higher weight),
         for example, for index 1 the range is [3, 7] which is longer than other ranges
         (since it has higher weight, 5), and therefore the random pick falls into that range with higher probability.
         */
        /*
        [2, 7, 10, 14]
        idx in [1,2] return 0
        idx in [3,7] return 1
        idx in [8,10] return 2
        idx in [11,14] return 3
         */
        //Time: O(log N)
        public int pickIndex() {
            //then get random val random.nextInt(14)+1, idx is in range [1,14]
            //random can return 0 so need to add +1 for the range for 0 to 14 exclusive
            int target = random.nextInt(prefixSums[prefixSums.length - 1]) + 1;
            // run a binary search to find the target zone
            int low = 0, high = this.prefixSums.length;
            while (low < high) {
                // better to avoid the overflow
                int mid = low + (high - low) / 2;
                if (target > this.prefixSums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

}
