package topInterviewQuestion.leetcode.dynamicProgramming;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/
public class BestTimeToBuyAndSell {

    public static void main(String[] args) {
        //int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices = {7, 1, 5, 3, 6};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfitValleyApproach(prices));
        //System.out.println(maxProfitBrutForce(prices));
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

    //Kadane's Algorithm
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39038/Kadane's-Algorithm-Since-no-one-has-mentioned-about-this-so-far-%3A)-(In-case-if-interviewer-twists-the-input)
    /*
    All the straight forward solution should work, but if the interviewer twists the question slightly by giving the difference array of prices,
    Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, you might end up being confused.
     */
    static  int maxProfitKadane(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur += prices[i] - prices[i - 1];
            if(maxSoFar < maxCur) {
                maxSoFar = maxCur;
            }
            if(maxCur < 0) {
                maxCur = 0;
            }
        }
        return maxSoFar;
    }

    //Approach 2
    /*
    Time complexity: O(n) -> Single pass.
    Space complexity: O(1) -> Constant space required.
     */
    static int maxProfitValleyApproach(int[] prices) {
        int i = 0;
        int valley = prices[0]; //minimum value
        int peak = prices[0]; //max value
        int maxProfit = 0;
        while(i < prices.length - 1) {
            while(i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i]; //We know that this price is the minimum
            while(i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i]; //We know that this price if higher than minimum
            maxProfit += peak - valley;
        }
        return maxProfit;
    }

    //Approach 1: Brut force
    //Can find only 1 result
    /*
    Time complexity: O(n²)
    Space complexity: O(1)
     */
    static int maxProfitBrutForce(int[] prices) {
        int maxProfit = 0;
        for(int i = 0; i < prices.length - 1; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if(profit > maxProfit)
                    maxProfit = profit;
            }
        }
        return maxProfit;
    }

}
