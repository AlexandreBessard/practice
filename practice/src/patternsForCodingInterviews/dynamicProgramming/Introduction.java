package patternsForCodingInterviews.dynamicProgramming;

//https://designgurus.org/path-player?courseid=grokking-dynamic-programming&unit=grokking-dynamic-programming_6126ffd8d78e2Unit
public class Introduction {

    public static void main(String[] args) {
        var obj = new Introduction();
        System.out.println(obj.calculateFibanocci(4));
        System.out.println(obj.calculateFibanocciTopDownMemoized(4));
        System.out.println(obj.calculateFibanocciBottomUp(4));

    }

    //Top-down without memoization
    public int calculateFibanocci(int n) {
        if (n < 2)
            return n;
        return calculateFibanocci(n - 1) +
                calculateFibanocci(n - 2);
    }

    //Top-down with memoization
    public int calculateFibanocciTopDownMemoized(int n) {
        int[] memoize = new int[n + 1];
        return calculateFibanocciTopDownMemoized(memoize, n);
    }

    private int calculateFibanocciTopDownMemoized(int[] memoize, int n) {
        if (n < 2)
            return n;
        //If we have already solved this subproblem, return from cache
        if (memoize[n] != 0) {
            return memoize[n];
        }
        memoize[n] = calculateFibanocciTopDownMemoized(memoize, n - 1)
                + calculateFibanocciTopDownMemoized(memoize, n - 2);

        return memoize[n];
    }

    //Bottom-Up with Tabulation
    public int calculateFibanocciBottomUp(int n) {
        if (n == 0)
            return 0;
        int[] dp = new int[n + 1];
        //base cases
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
