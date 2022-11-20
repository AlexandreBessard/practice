package leetcode.dynamicProgramming;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/807/
public class JumpGame {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(canJumpBacktrack(nums));
        System.out.println(canJumpTopDown(nums));
        /*
        System.out.println(canJumpBacktrack(nums2));
        System.out.println(canJumpBacktrackWithMemo(nums));
        System.out.println(canJumpBottomUp(nums));
        System.out.println(canJumpGreedy(nums));

         */
    }

    //Approach 1: Backtracking
    /*
    Time: O(2n)
    Space: O(n)
     */
    public static boolean canJumpBacktrack(int[] nums) {
        return helper(0, nums);
    }

    public static boolean helper(int position, int[] nums) {
        if (position == nums.length - 1) { //means we have reached the end of the array
            return true; //Stop the recursion
        }
        //To know how far we can jump from that current position (position + num of jumps)
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition < furthestJump; nextPosition++) {
            if (helper(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }

    //Top-down. Same logic with backtracking but we implement memoization
    //Approach 2: Dynamic Programming Top-down -> backtracking + memoization
    /*
    Time complexity: O(n²) we are looking at the next nums[i] element to its right aiming to find GOOD
    Space complexity: O(2n) == O(n)
    First n originates from recursion, second n comes from usage of the memo
     */
    //Approach 2: Dynamic Programming Top-Down

    private static Index[] memoization;

    static boolean canJumpTopDown(int[] nums) {
        memoization = new Index[nums.length];
        Arrays.fill(memoization, Index.UNKNOWN);
        memoization[memoization.length - 1] = Index.GOOD; //The latest index is set to GOOD for our base case
        return canJumpDP(0, nums);
    }

    private static boolean canJumpDP(int position, int[] nums) {
        //Base case
        if (memoization[position] != Index.UNKNOWN) {
            return memoization[position] == Index.GOOD; //Return true if GOOD only else false
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1); // How far we can go from our position
        //Iteration
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) { //Try all position
            if (canJumpDP(nextPosition, nums)) {
                memoization[position] = Index.GOOD;
                return true;
            }
        }
        memoization[position] = Index.BAD; //we exit the iteration, it means from this position, it is no possible to reach the end.
        return false;
    }


    enum Index {GOOD, BAD, UNKNOWN}

    //Approach 4: Greedy
    /*
    Time: O(n)
    Space: O(1)
     */
    static boolean canJumpGreedy(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    //Approach 3: Dynamic Programming Bottom Up
    /*
    Time complexity: O(n²)
    Space complexity: O(n) used by memo table.
     */
    static boolean canJumpBottomUp(int[] nums) {
        Index[] memo = new Index[nums.length];
        Arrays.fill(memo, Index.UNKNOWN);
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }
}
