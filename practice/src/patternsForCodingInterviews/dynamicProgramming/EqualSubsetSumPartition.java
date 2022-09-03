package patternsForCodingInterviews.dynamicProgramming;

//https://designgurus.org/path-player?courseid=grokking-dynamic-programming&unit=grokking-dynamic-programming_6126ffd8cc61fUnit
public class EqualSubsetSumPartition {

    public static void main(String[] args) {
        EqualSubsetSumPartition ps = new EqualSubsetSumPartition();
        int[] num;
        num = new int[]{1, 2, 3, 4};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));
        System.out.println("\nTop Down : ");
        num = new int[]{1, 2, 3, 4};
        System.out.println(ps.canPartitionTopDown(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartitionTopDown(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartitionTopDown(num));
        System.out.println("\nBottom Down : ");
        num = new int[]{1, 2, 3, 4};
        System.out.println(ps.canPartitionBottomUp(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartitionBottomUp(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartitionBottomUp(num));
    }

    //Brut force
    /*
    Time: O(2n) where N represents total number
    Space: O(n)
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //If sum odd number, we can not have 2 subsets with equal sum
        if (sum % 2 != 0) {
            return false;
        }

        return canPartitionRecursive(nums, sum / 2, 0);
    }

    private boolean canPartitionRecursive(int[] nums, int sum, int currentIndex) {
        //base check
        if (sum == 0)
            return true;
        if (nums.length == 0 || currentIndex >= nums.length) {
            return false;
        }
        //Recursive call after choosing the number at the currentIndex
        //If the number at currentIndex exceeds the sum, we do not process this
        if (nums[currentIndex] <= sum) {
            if (canPartitionRecursive(
                    nums,
                    sum - nums[currentIndex],
                    currentIndex + 1)) {
                return true;
            }
        }
        //recursive call after excluding the number at the currentIndex
        return canPartitionRecursive(nums, sum, currentIndex + 1);
    }

    /***********************************************/

    //Top-Down with Memoization
    /*
    need to store the results for every subset for every possible sum, we use
    2D array. First dimension: represent different subsets
    Second dimension: different sums that we calculate from each subset

    Time: O(N * S)
    N represents total numbers & S total sum of all numbers
    Space: O(N)
     */
    public boolean canPartitionTopDown(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        Boolean[][] dp = new Boolean[nums.length][(sum / 2) + 1];
        return this.canPartitionRecursive(dp, nums, sum / 2, 0);
    }

    private boolean canPartitionRecursive(Boolean[][] dp,
                                          int[] num,
                                          int sum,
                                          int currentIndex) {
        //base check
        if (sum == 0)
            return true;
        if (num.length == 0 || currentIndex >= num.length)
            return false;
        // if we have not already processed a similar problem
        if (dp[currentIndex][sum] == null) {
            // recursive call after choosing the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't process this
            if (num[currentIndex] <= sum) {
                if (canPartitionRecursive(dp,
                        num,
                        sum - num[currentIndex],
                        currentIndex + 1)) {
                    dp[currentIndex][sum] = true;
                    return true;
                }
            }
            // recursive call after excluding the number at the currentIndex
            dp[currentIndex][sum] = canPartitionRecursive(dp,
                    num, sum, currentIndex + 1);
        }
        return dp[currentIndex][sum];
    }

    /***********************************************/

    /*
    Bottom Up

    Time: O(N * S)N represents total numbers and S is the total sum of all numbers
    Space: 0(N)
     */
    public boolean canPartitionBottomUp(int[] num) {
        int n = num.length;
        // find the total sum
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with same total
        if (sum % 2 != 0)
            return false;

        // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
        sum /= 2;

        boolean[][] dp = new boolean[n][sum + 1];

        // populate the sum=0 columns, as we can always for '0' sum with an empty set
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is equal to
        // its value
        for (int s = 1; s <= sum; s++) {
            dp[0][s] = (num[0] == s ? true : false);
        }

        // process all subsets for all sums
        for (int i = 1; i < n; i++) {
            for (int s = 1; s <= sum; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if (dp[i - 1][s]) {
                    dp[i][s] = dp[i - 1][s];
                } else if (s >= num[i]) {
                    // else we can find a subset to get the remaining sum
                    dp[i][s] = dp[i - 1][s - num[i]];
                }
            }
        }

        // the bottom-right corner will have our answer.
        return dp[n - 1][sum];
    }

}
