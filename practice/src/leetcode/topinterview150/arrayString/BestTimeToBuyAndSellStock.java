package leetcode.topinterview150.arrayString;

public class BestTimeToBuyAndSellStock {

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        // You want to maximize your profit by choosing
        // a single day to buy one stock
        // and choosing a different day in the future to sell that stock.
        // buy on day 2 and sell on day 5 -> max profit possible
        System.out.println(maxProfit(nums));
        System.out.println(maxProfitOptimized(nums));
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    static int maxProfitOptimized(int[] prices) {
        // It represents the minimum price encountered so far in the loop.
        // Initialized to the largest possible integer.
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) { // true means we found a smaller price
                // We buy at this price
                minPrice = prices[i];
                // sell the action bought in the past with the current price
            } else if (prices[i] - minPrice > maxProfit) { // true means we have a bigger profit
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    /*
    Solution: Brut force
    Time: O(n2)
    Space: O(1)
     */
    static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int buy = 0; buy < prices.length; buy++) {
            for (int sell = 1; sell < prices.length; sell++) {
                int profit = prices[sell] - prices[buy];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

}
