package topInterviewQuestion.easy.dynamicProgramming;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] nums = {7,1,5,3,6,4};
        //Output: 5
        System.out.println(maxProfit(nums));
        System.out.println(maxProfitOnePass(nums));
        int[] nums1 = {0, 6, -3, 7};
        System.out.println(maxProfitKadane(nums1));
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


    //Approach 2: One pass
    /*
    Time complexity: O(n)
    Space complexity:  O(1)
     */
    static int maxProfitOnePass(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for(int i = 0; i < prices.length; i++) {
            if(prices[i] < minprice) //Take the min value
                minprice = prices[i];
            else if(prices[i] - minprice > maxprofit) //Higher price - min value = max profit
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    //Approach 1: Brut force
    /*
    Time complexity: O(n²)
    Space complexity: O(1)
     */
    static int maxProfit(int[] prices) {
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
