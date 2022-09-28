package patternsForCodingInterviews.O1Knapsack;

public class MinimumSubsetSumDifference {

    public static void main(String[] args) {
        PartitionSet ps = new PartitionSet();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.canPartition(num));
    }

    static class PartitionSet {
        Integer[][] dp;

        public int canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            dp = new Integer[nums.length][sum + 1];
            return this.canPartitionRecursive(nums, 0, 0, 0);
        }

        private int canPartitionRecursive(int[] nums, int currentIndex, int sum1, int sum2) {
            //Base case
            if (currentIndex == nums.length) {
                return Math.abs(sum1 - sum2);
            }
            //Get from the cache
            if (dp[currentIndex][sum1] != null) {
                return dp[currentIndex][sum1];
            }
            //Recursive
            int newSumDiff = sum1 + nums[currentIndex];
            int diff1 = canPartitionRecursive(nums, currentIndex + 1, newSumDiff, sum2);
            newSumDiff = sum2 + nums[currentIndex];
            int diff2 = canPartitionRecursive(nums, currentIndex + 1, sum1, newSumDiff);
            dp[currentIndex][sum1] = Math.min(diff1, diff2);
            //Return from the cache
            return dp[currentIndex][sum1];
        }

    }
}
