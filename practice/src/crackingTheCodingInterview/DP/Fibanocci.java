package crackingTheCodingInterview.DP;

public class Fibanocci {

    public static void main(String[] args) {
        System.out.println(Fibanocci.fibanocci(3));
        System.out.println(fib(5));
    }

    //Bottom-up
    static int fibonacciBottomUp(int n) {
        if(n == 0)
            return 0;
        else if (n == 1)
            return 1;
        int[] memo = new int[n];
        memo[0] = 0;
        memo[1] = 1;
        for(int i = 2; i < n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n - 1] + memo[n - 2];
    }

    static int fib(int i) {
        return fibanocci(i, new int[i + 1]);
    }
    //Memoized, Top-bottom
    static int fibanocci(int i, int[] memo) {
        if(i == 0 || i == 1)
            return i;
        if(memo[i] == 0) {
            memo[i] =
                    fibanocci(i - 1, memo)
                    + fibanocci(i - 2, memo);
        }
        return memo[i];
    }

    public static int fibanocci(int i) {
        if(i == 0)
            return 0;
        if(i == 1)
            return 1;
        return fibanocci(i - 1) + fibanocci(i - 2);
    }

}
