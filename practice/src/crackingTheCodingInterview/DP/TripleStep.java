package crackingTheCodingInterview.DP;

import java.util.Arrays;

public class TripleStep {

    public static void main(String[] args) {
        System.out.println(countWaysMemoization(37));
    }

    static int countWaysMemoization(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return countWaysMemoization(n, memo);
    }
    static int countWaysMemoization(int n, int[] memo) {
        if(n < 0)
            return 0;
        else if(n == 0)
            return 1;
        else if(memo[n] > -1)
            return memo[n];
        else {
            memo[n] =
                    countWaysMemoization(n - 1, memo)
                            + countWaysMemoization(n - 2, memo)
                            + countWaysMemoization(n - 3, memo);
            return memo[n];
        }
    }

    //Time complexity: O(3n)
    //Each call branches out to three more calls
    static int countWays(int n) {
        if(n < 0)
            return 0;
        else if(n == 0)
            return 1;
        else
            return countWays(n - 1) +
                    countWays(n - 2) +
                    countWays(n - 3);
    }


}
