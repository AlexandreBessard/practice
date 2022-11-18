package leetcode.math;

public class PowerOfThree {

    public static void main(String[] args) {

    }

    //Approach 1: Loop iteration
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

}
