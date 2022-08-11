package algorithms.dynamicProgramming.commonPatternsInDP;
//https://leetcode.com/explore/learn/card/dynamic-programming/632/common-patterns-in-dp-problems/4116/
public class BestTimeToBuyAndSellStockIV {

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
    public static void main(String[] args) {
        int[] nums = {3,2,6,5,0,3};
        int  k = 2;
        var obj = new BestTimeToBuyAndSellStockIV();
        //Output 7
        System.out.println(obj.maxProfit(k, nums));
        System.out.println(obj.maxProfitBottomUp(k, nums));
    }

    private int[] prices;
    private int[][][] memo;
    public int maxProfit(int k, int[] prices) {
        this.prices = prices;
        this.memo = new int[prices.length][k + 1][2];
        //start on day 0, with k transactions remaining and not holding a stock.
        //holding: 0 -> we are not holding a stock (option to buy a stock)
        // holding: 1 -> We are holding a stock (option ot sell a stock)
        return dp(0, k, 0);
    }

    //transactionsRemaining: represents how many transaction we have left.
    private int dp(int i, int transactionsRemaining, int holding) {
        // Base cases
        if (transactionsRemaining == 0 || i == prices.length) {
            return 0;
        }

        if (memo[i][transactionsRemaining][holding] == 0) {
            //move onto the next day with the same number of transactions,
            // while still holding the stock
            int doNothing = dp(i + 1, transactionsRemaining, holding);
            int doSomething;

            if (holding == 1) { //Holding a stock
                // Sell Stock
                doSomething = prices[i] + dp(
                        i + 1,
                        transactionsRemaining - 1,
                        0);
            } else { //Does not hold stock
                // Buy Stock
                doSomething = - prices[i] + dp(
                        i + 1,
                        transactionsRemaining,
                        1);
            }
            // Recurrence relation. Choose the most profitable option.
            memo[i][transactionsRemaining][holding] = Math.max(doNothing, doSomething);
        }
        return memo[i][transactionsRemaining][holding];
    }


    //--------------------------- BOTTOM UP
    public int maxProfitBottomUp(int k, int[] prices) {
        int n = prices.length;
        int dp[][][] = new int[n + 1][k + 1][2];
        for (int i = n - 1; i >= 0; i--) {
            for (int transactionsRemaining = 1; transactionsRemaining <= k; transactionsRemaining++) {
                for (int holding = 0; holding < 2; holding++) {
                    int doNothing = dp[i + 1][transactionsRemaining][holding];
                    int doSomething;
                    if (holding == 1) {
                        // Sell stock
                        doSomething = prices[i] + dp[i + 1][transactionsRemaining - 1][0];
                    } else {
                        // Buy stock
                        doSomething = -prices[i] + dp[i + 1][transactionsRemaining][1];
                    }

                    // Recurrence relation
                    dp[i][transactionsRemaining][holding] = Math.max(doNothing, doSomething);
                }
            }
        }
        return dp[0][k][0];
    }
}
