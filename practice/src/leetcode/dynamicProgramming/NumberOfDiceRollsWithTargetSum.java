package leetcode.dynamicProgramming;
//https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
public class NumberOfDiceRollsWithTargetSum {
/*
You have n dice, and each die has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
Example 1:
Input: n = 1, k = 6, target = 3
Output: 1
Explanation: You throw one die with 6 faces.
There is only one way to get a sum of 3.
 */
    public static void main(String[] args) {
        int n = 1;
        int k = 6;
        int target = 3;
        System.out.println(numRollsToTarget(n, k, 3));
    }

    //TODO: must do
    public static int numRollsToTarget(int n, int k, int target) {

        return 0;
    }



}
