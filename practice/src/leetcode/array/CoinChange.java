package leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/809/
public class CoinChange {
/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.
Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1
 */
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int[] coins2 = {1, 2, 3};
        int amount2 = 6;
        int amount = 11; // 5 + 5 + 1
        System.out.println(coinChangeTopDown(coins2, amount2));
        System.out.println(coinChangeBottomUm(coins, 3));
        System.out.println(coinChangeBFS(coins, 11));
    }

    //Approach 3: BFS
    //Correction: https://leetcode.com/problems/coin-change/discuss/77368/*Java*-Both-iterative-and-recursive-solutions-with-explanations
    /*
    Time: O(V + E)
    Space: O(N)
     */
    public static int coinChangeBFS(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        //Indexes represent the remaining amount
        boolean[] visited = new boolean[amount + 1];
        queue.offer(amount);
        visited[amount] = true;
        int minNumOfCoinsNeeded = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int currAmount = queue.remove();
                for(int coin : coins) { //Remove the current amount by testing each coins
                    int remainingAmount = currAmount - coin;
                    if(remainingAmount == 0) {
                        return minNumOfCoinsNeeded;
                    } else if(remainingAmount > 0 && !visited[remainingAmount]) {
                        queue.add(remainingAmount);
                        visited[remainingAmount] = true;
                    }
                }
            }
            minNumOfCoinsNeeded++;
        }
        return -1;
    }

    //Approach 2: Dynamic Programming: Top-Down
    static int coinChangeTopDown(int[] coins, int amount) {
        if(amount < 1) {
            return 0;
        }
        return coinChangeTopDown(coins, amount, new int[amount]);
    }
    private static int coinChangeTopDown(int[] coins, int amount, int[] count) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (count[amount - 1] != 0) {
            return count[amount - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeTopDown(coins, amount - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[amount - 1];
    }

    //Approach 3: Dynamic Programming: Bottom-Up
    static int coinChangeBottomUm(int[] coins, int amount){
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0; //With amount 0 dp[0] I need 0 coins.
        //i represent the amount
        for (int i = 1; i <= amount; i++) { //check for each amount from 1 to amount (bottom up) how minimum coins do I need
            //j represent the coins at the index
            for (int coin : coins) {
                if ((i - coin) >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // +1 represent the number of coins.
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}


