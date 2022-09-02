package patternsForCodingInterviews.dynamicProgramming;

//https://designgurus.org/path-player?courseid=grokking-dynamic-programming&unit=grokking-dynamic-programming_6126ffd8cf242Unit
public class Knapsack {

    public static void main(String[] args) {
        int maxProfit = 0;
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Recursive ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Recursive ---> " + maxProfit);

        maxProfit = ks.solveKnapsackTopDown(profits, weights, 7);
        System.out.println("Top down ---> " + maxProfit);
        maxProfit = ks.solveKnapsackTopDown(profits, weights, 6);
        System.out.println("Top Down ---> " + maxProfit);

        maxProfit = ks.solveKnapsackBottomUp(profits, weights, 7);
        System.out.println("Bottom Up ---> " + maxProfit);
        maxProfit = ks.solveKnapsackBottomUp(profits, weights, 6);
        System.out.println("Bottom Up ---> " + maxProfit);
    }

    /************************************/


    //Brut force
    /*
    Time: O(2n), n represents the total number of items.
    Space: O(n)
     */
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return this.knapsackRecursive(profits, weights, capacity, 0);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity,
                                  int currentIndex) {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't
        // process this
        int profit1 = 0;
        //SELECT
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + knapsackRecursive(
                    profits,
                    weights,
                    capacity - weights[currentIndex],
                    currentIndex + 1);

        // recursive call after excluding the element at the currentIndex
        //SKIP
        int profit2 = knapsackRecursive(
                profits,
                weights,
                capacity,
                currentIndex + 1);

        return Math.max(profit1, profit2);
    }

    /************************************/


    //Top-Down With memoization
    /*
    O(N * C)
    Space: memoization 2D array: O(N * C), recursion: O(N)
    Which is O(N * C)
     */
    public int solveKnapsackTopDown(int[] profits,
                                    int[] weights,
                                    int capacity) {
        //Since we have two changing values: capacity and currentIndex,
        //we can use two-dimensional array
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return this.knapsackRecursiveTopDown(
               dp, profits, weights, capacity, 0);
    }

    private int knapsackRecursiveTopDown(Integer[][] dp,
                                         int[] profits,
                                         int[] weights,
                                         int capacity,
                                  int currentIndex) {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        //We have already solved a similar problem
        if(dp[currentIndex][capacity] != null) {
            return dp[currentIndex][capacity];
        }
        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't
        // process this
        int profit1 = 0;
        //SELECT
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + knapsackRecursive(
                    profits,
                    weights,
                    capacity - weights[currentIndex],
                    currentIndex + 1);

        // recursive call after excluding the element at the currentIndex
        //SKIP
        int profit2 = knapsackRecursive(
                profits,
                weights,
                capacity,
                currentIndex + 1);

        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
    }

    /************************************/


    //Bottom-Up
    /*
    Time: O(N * C)
    Space: O(N)
     */
    public int solveKnapsackBottomUp(int[] profits,
                                     int[] weights,
                                     int capacity)
    {
        // basic checks
        if (capacity <= 0
                || profits.length == 0
                || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];

        // populate the capacity=0 columns, with '0' capacity we have '0' profit
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        // if we have only one weight, we will take it if it is not more than the capacity
        for(int c=0; c <= capacity; c++) {
            if(weights[0] <= c)
                dp[0][c] = profits[0];
        }

        // process all sub-arrays for all the capacities
        for(int i=1; i < n; i++) {
            for(int c=1; c <= capacity; c++) {
                int profit1= 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i-1][c-weights[i]];
                // exclude the item
                profit2 = dp[i-1][c];
                // take maximum
                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        // maximum profit will be at the bottom-right corner.
        return dp[n-1][capacity];
    }

}
