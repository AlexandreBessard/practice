package topInterviewQuestion.medium.dynamicProgramming;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/807/
public class JumpGame {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(canJumpBacktrack(nums2));
        System.out.println(canJumpBacktrackWithMemo(nums));
        System.out.println(canJumpBottomUp(nums));
        System.out.println(canJumpGreedy(nums));
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

    //Approach 2: Dynamic Programming Top-Down
    static boolean canJumpDP(int[] nums) {
        memo = new Index[nums.length];
        Arrays.fill(memo, Index.UNKNOWN);
        memo[memo.length - 1] = Index.GOOD; //The latest index is set to GOOD for our base case
        return canJumpDP(0, nums);
    }

    private static boolean canJumpDP(int position, int[] nums) {
        //Base case
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD; //Return true if GOOD only else false
        }
        //nums.length - 1  -> max we can go
        int furthestJump = Math.min(position + nums[position], nums.length - 1); // How far we can go from our position
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) { //Try all position
            if (canJumpDP(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }
        memo[position] = Index.BAD;
        return false;
    }

    //Approach 2: Dynamic Programming Top-down -> backtracking + memoization
    /*
    Time complexity: O(n²)
    Space complexity: O(2n) == O(n)
    First n originates from recursion, second n comes from usage of the memo
     */
    private static Index[] memo;

    static boolean canJumpBacktrackWithMemo(int[] nums) {
        memo = new Index[nums.length];
        Arrays.fill(memo, Index.UNKNOWN);
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPositionTopDown(0, nums);
    }

    private static boolean canJumpFromPositionTopDown(int position, int[] nums) {
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPositionBacktracking(nextPosition, nums)) {
                memo[nextPosition] = Index.GOOD;
                return true;
            }
        }
        memo[position] = Index.BAD;
        return false;
    }


    private static boolean canJumpFromPositionBacktracking(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPositionBacktracking(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }

    //Approach 1: BackTracking
    /*
    Time complexity: O(2n)
    Space complexity: O(n)
     */
    static boolean canJumpBacktrack(int[] nums) {
        return canJumpFromPositionBacktracking(0, nums);
    }

}
