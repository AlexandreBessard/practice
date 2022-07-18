package topInterviewQuestion.easy.dynamicProgramming;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] nums = {7,1,5,3,6,4};
        //Output: 5
        System.out.println(maxProfit(nums));
        System.out.println(maxProfitOnePass(nums));
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
            if(prices[i] < minprice)
                minprice = prices[i];
            else if(prices[i] - minprice > maxprofit)
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
