package topInterviewQuestion.top100likedQuestions;

//https://leetcode.com/problems/partition-equal-subset-sum/
public class PartitionEqualSubsetSum {
/*
Given a non-empty array nums containing only positive integers,
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equ
 */
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        int[] nums2 = {1, 2, 3, 5};
        System.out.println(canPartitionTopDown(nums1));
    }

    //TODO: Bottom Up approach

    //Approach 2: Top Down DP
    /*
    Time: O(m . N) proportional to the number of entries in memo
    Space: O(m . n) we are using 2 dimensional array 'memo'
     */
    static boolean canPartitionTopDown(int[] nums) {
        int totalSum = 0;
        //Find sum of all array elements
        for(int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd,it cannot be partitioned into equal sum subset
        if(totalSum % 2 != 0) {
            return false;
        }
        int subSetSum = totalSum / 2;
        int n = nums.length;
        Boolean[][] memo = new Boolean[n + 1][subSetSum + 1];
        return dfs(nums, n - 1, subSetSum, memo);
    }
    private static boolean dfs(int[] nums, int n, int subsetSum, Boolean[][] memo) {
        //Base case
        if(subsetSum == 0) {
            return true;
        }
        if(n == 0 || subsetSum < 0) {
            return false;
        }
        // check if subSetSum for given n is already computed and stored in memo
        if (memo[n][subsetSum] != null) {
            return memo[n][subsetSum];
        }
        memo[n][subsetSum] =  dfs(nums, n - 1, subsetSum - nums[n - 1]) //Case 1: x (element) is included in subset sum
                || dfs(nums, n-1, subsetSum); // x (element) it not included in subset sum, so we must take previous sum without x
        return memo[n][subsetSum];
    }

    //Approach 1: Brut force
    /*
    Time: O(2n) where n is equal to number of array elements.
    Space: O(N) used to store the recursion stack
     */
    static boolean canPartition(int[] nums) {
        int totalSum = 0;
        //Find sum of all array elements
        for(int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd,it cannot be partitioned into equal sum subset
        if(totalSum % 2 != 0) {
            return false;
        }
        int subSetSum = totalSum / 2;
        int n = nums.length;
        return dfs(nums, n - 1, subSetSum);
    }
    private static boolean dfs(int[] nums, int n, int subsetSum) {
        //Base case
        if(subsetSum == 0) {
            return true;
        }
        if(n == 0 || subsetSum < 0) {
            return false;
        }
        return dfs(nums, n - 1, subsetSum - nums[n - 1]) //Case 1: x (element) is included in subset sum
                || dfs(nums, n-1, subsetSum); // x (element) it not included in subset sum, so we must take previous sum without x
    }

}
