package patternsForCodingInterviews.O1Knapsack;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744460476_114Unit
public class O1Knapsack {

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }

    /*
    We have 2 changing values-> capacity & currentIndex we can use 2D array
     */
    static class Knapsack {
        Integer[][] dp;
        public int solveKnapsack(int[] profits, int[] weights, int capacity) {
            dp = new Integer[profits.length][capacity + 1];
            return this.knapsackRecursive(profits, weights, capacity, 0);
        }
        private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
            //Base case
            if(capacity <= 0 || currentIndex >= profits.length) {
                return 0;
            }
            //Get from the cache
            if(dp[currentIndex][capacity] != null) {
                return dp[currentIndex][capacity];
            }
            //Recursive call
            int profit1 = 0;
            if(weights[currentIndex] <= capacity) { //True if we have enough capacity to take this element based on his weight
                int remainingCapacity = capacity - weights[currentIndex];
                profit1 = profits[currentIndex] + knapsackRecursive(profits, weights, remainingCapacity, currentIndex + 1);
            }
            //Recursive call after excluding the element at the current index
            int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);
            dp[currentIndex][capacity] =  Math.max(profit1, profit2);
            //Return from cache
            return dp[currentIndex][capacity];
        }
    }

}
