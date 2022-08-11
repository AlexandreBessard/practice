package algorithms.dynamicProgramming.commonPatternsContinued;
//https://leetcode.com/explore/learn/card/dynamic-programming/633/common-patterns-continued/4144/
public class StateReduction {

    public static void main(String[] args) {
        System.out.println(fib(4));
    }

    //Fibanocci bottom Up approach
    /*
    Space complexity: O(1) instead of O(n) using an array
    Time complexity: O(n)
     */
    public static int fib(int n) {
        if (n <= 1) return n;
        int one_back = 1;
        int two_back = 0;

        for (int i = 2; i <= n; i++) {
            int temp = one_back;
            one_back += two_back;
            two_back = temp;
        }
        return one_back;
    }

}
