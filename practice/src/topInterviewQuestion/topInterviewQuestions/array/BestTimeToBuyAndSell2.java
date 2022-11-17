package topInterviewQuestion.topInterviewQuestions.array;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/
public class BestTimeToBuyAndSell2 {

    public static void main(String[] args) {
        //int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices = {7, 1, 5, 3, 6};
        System.out.println(maxProfit(prices));
        //System.out.println(maxProfitValleyApproach(prices));
    }

    //Approach 3: Single pass
    /*
    Time complexity: O(n)
    Space: 0(1)
     */
    public static int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) //If current price greater than previous, sell it.
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    //Brut force
    /*
    Time complexity : O(n^n) Recursive call n^n times.
    Space complexity : O(n). Depth of recursion is n.
     */
    static int maxProft(int[] prices) {
        return calculate(prices, 0);
    }
    private static int calculate(int[] prices, int s) {
        if(s >= prices.length)
            return 0;
        int max = 0;
        for(int start = s; start < prices.length; start++) {
            int maxProfit = 0;
            for(int i = start; i < prices.length; i++) {
                if(prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
            max = Math.max(maxProfit, max);
        }
        return max;
    }

    //Approach 2
    /*
    Time complexity: O(n) -> Single pass.
    Space complexity: O(1) -> Constant space required.
     */
    static int maxProfitValleyApproach(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxProfit = 0;
        while(i < prices.length - 1) {
            while(i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while(i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxProfit += peak - valley;
        }
        return maxProfit;
    }
}
