package algorithms.dynamicProgramming;

public class BottomUp {

    public static void main(String[] args) {
        System.out.println(fibonacci(3));
    }
    //Bottom Up
    static int fibonacci(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[num];
    }

}
