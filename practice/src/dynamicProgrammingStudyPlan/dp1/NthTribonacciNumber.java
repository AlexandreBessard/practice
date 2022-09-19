package dynamicProgrammingStudyPlan.dp1;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/n-th-tribonacci-number/?envType=study-plan&id=dynamic-programming-i
public class NthTribonacciNumber {

    public static void main(String[] args) {
        System.out.println(tribonacciBasicRecursive(25));
        System.out.println(tribonacciBottomUp(25));
        System.out.println(tribonacciTopDown(25));
    }

    //TOP-DOWN
    /*
    Time: O(N)
    Space: O(N)
     */
    static Map<Integer,Integer> memo = new HashMap<>();
    static int tribonacciTopDown(int n) {
        if(memo.containsKey(n)) return memo.get(n);
        if(n == 0)
            return 0;
        if(n <= 2)
            return 1;
        memo.put(n, tribonacciBasicRecursive(n - 1)
                + tribonacciBasicRecursive(n - 2)
                + tribonacciBasicRecursive(n - 3));
        return memo.get(n);
    }

    //BOTTOM-UP
    /*
    Time: O(N)
    Space: O(N)
     */
    static int tribonacciBottomUp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0; //Line of code not necessary
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    //Basic recursive method
    /*
    Time: O(n)
    Space: O(n)
     */
    static int tribonacciBasicRecursive(int n) {
        if (n < 3) return n == 0 ? 0 : 1;
        int tmp, x = 0, y = 1, z = 1;
        for (int i = 3; i <= n; i++) {
            tmp = x + y + z;
            x = y;
            y = z;
            z = tmp;
        }
        return z;
    }

}
