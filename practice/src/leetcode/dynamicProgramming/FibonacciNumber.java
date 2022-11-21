package leetcode.dynamicProgramming;

//https://leetcode.com/problems/fibonacci-number/
public class FibonacciNumber {

    public static void main(String[] args) {
        System.out.println(fibTopDown(4));
        System.out.println(fibBottomUp(25));
    }

    //Correction:
    //https://leetcode.com/problems/fibonacci-number/discuss/215992/Java-Solutions
    //TOP-DOWN
    /*
    Time: O(n)
    Space: O(n)
     */
    static int[] fib_cache = new int[31];

    static int fibTopDown(int n) {
        if (n <= 1) return n; //Base case
        //Get from the cache
        if (fib_cache[n] != 0) {
            return fib_cache[n];
        }
        //Recursion, store the result into the cache
        fib_cache[n] = fibTopDown(n - 1) + fibTopDown(n - 2);
        //Return from the cache
        return fib_cache[n];
    }

    //BOTTOM UP
    /*
    Time: O(n)
    Space: O(n)
     */
    static int fibBottomUp(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; //Line of code not necessary, array initialize by 0 by default
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

