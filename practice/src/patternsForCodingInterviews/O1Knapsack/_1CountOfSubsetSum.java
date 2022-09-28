package patternsForCodingInterviews.O1Knapsack;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744496415_118Unit
public class _1CountOfSubsetSum {

    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        int[] num = {1, 1, 2, 3};
        System.out.println(ss.countSubsets(num, 4));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ss.countSubsets(num, 9));
    }

    static class SubsetSum {
        Integer[][] dp;

        public int countSubsets(int[] nums, int S) {
            dp = new Integer[nums.length][S + 1];
            return countSubsetsRecursive(nums, S, 0);
        }

        private int countSubsetsRecursive(int[] nums, int S, int currentIndex) {
            //Base case
            if (S == 0) {
                return 1; //This subset is equal to S
            }
            if (S <= 0 || currentIndex >= nums.length) {
                return 0;
            }
            //Check from cache
            if (dp[currentIndex][S] != null) {
                return dp[currentIndex][S];
            }
            //Recursive
            int sum1 = 0;
            if (nums[currentIndex] <= S) {
                int newSum = S - nums[currentIndex];
                sum1 = countSubsetsRecursive(nums, newSum, currentIndex + 1);
            }
            int sum2 = countSubsetsRecursive(nums, S, currentIndex + 1);
            dp[currentIndex][S] = sum1 + sum2; //Count the number of subsets equal to 'S'
            //Return from the cache
            return dp[currentIndex][S];
        }
    }
}
