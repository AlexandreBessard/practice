package topInterviewQuestion.medium.dynamicProgramming;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/809/
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int[] coins2 = {1, 2, 3};
        int amount2 = 6;
        int amount = 11; // 5 + 5 + 1
        System.out.println(coinChangeTopDown(coins2, amount2));
        System.out.println(coinChangeBottomUm(coins2, amount2));
    }

    //Approach 3: Dynamic Programming: Bottom-Up
    static int coinChangeBottomUm(int[] coins, int amount){
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        //i represent the amount
        for (int i = 1; i <= amount; i++) {
            //j represent the coins at the index
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    //Approach 2: Dynamic Programming: Top-Down
    static int coinChangeTopDown(int[] coins, int amount) {
        if(amount < 1)
            return 0;
        return coinChangeTopDown(coins, amount, new int[amount]);
    }
    private static int coinChangeTopDown(int[] coins, int rem, int[] count) {
        if (rem < 0)
            return -1;
        if (rem == 0)
            return 0;
        if (count[rem - 1] != 0)
            return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeTopDown(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
}


