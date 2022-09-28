package patternsForCodingInterviews.O1Knapsack;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744468342_115Unit
public class EqualSubsetSumPartition {

    public static void main(String[] args) {
        PartitionSet ps = new PartitionSet();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num)); //True
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num)); //True
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num)); //False
    }

    static class PartitionSet {

        Boolean[][] dp;

        public boolean canPartition(int[] num) {
            int sum = 0;
            for (int n : num) {
                sum += n;
            }
            //First dimension: represent different subsets and second represent different 'sums' we can calculate for each subset
            dp = new Boolean[num.length][sum / 2 + 1];
            //Two equal subsets must have a sum equal to S/2
            if (sum % 2 == 1) {
                return false;
            }
            return this.canPartitionRecursive(num, sum / 2, 0);
        }

        private boolean canPartitionRecursive(int[] num, int sum, int currentIndex) {
            //Base case
            if (sum == 0) {
                return true;
            }
            if (sum <= 0 || currentIndex >= num.length) {
                return false;
            }
            //Get from cache
            if (dp[currentIndex][sum] != null) {
                return dp[currentIndex][sum];
            }
            //Recursive call
            if (num[currentIndex] <= sum) {
                int newSum = sum - num[currentIndex];
                if (canPartitionRecursive(num, newSum, currentIndex + 1)) {
                    return true;
                }
            }
            //recursive call after excluding the number at the current index
            dp[currentIndex][sum] = canPartitionRecursive(num, sum, currentIndex + 1);
            //Return from the cache
            return dp[currentIndex][sum];
        }

    }

}
