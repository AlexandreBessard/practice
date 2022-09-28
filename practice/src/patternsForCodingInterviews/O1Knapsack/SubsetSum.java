package patternsForCodingInterviews.O1Knapsack;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744479845_116Unit
public class SubsetSum {

    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        int[] num = {1, 2, 3, 7};
        System.out.println(ss.canPartition(num, 6));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ss.canPartition(num, 10));
        num = new int[]{1, 3, 4, 8};
        System.out.println(ss.canPartition(num, 6));
    }

    Boolean[][] dp;

    public boolean canPartition(int[] nums, int S) {
        dp = new Boolean[nums.length][S + 1];
        return canPartitionRecursive(nums, S, 0);
    }

    private boolean canPartitionRecursive(int[] nums, int S, int currentIndex) {
        //Base case
        if (S == 0) {
            return true;
        }
        if (S <= 0 || currentIndex >= nums.length) {
            return false;
        }
        //Get from cache
        if (dp[currentIndex][S] != null) {
            return dp[currentIndex][S];
        }
        //Recursive call
        if (nums[currentIndex] <= S) {
            int newSum = S - nums[currentIndex];
            if (canPartitionRecursive(nums, newSum, currentIndex + 1)) {
                return true;
            }
        }
        dp[currentIndex][S] = canPartitionRecursive(nums, S, currentIndex + 1);
        //Get from the cache
        return dp[currentIndex][S];
    }

}
