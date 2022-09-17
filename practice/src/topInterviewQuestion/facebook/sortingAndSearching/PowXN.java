package topInterviewQuestion.facebook.sortingAndSearching;

//https://leetcode.com/explore/interview/card/facebook/54/sorting-and-searching-3/3031/
public class PowXN {
    /*
    Input: x = 2.00000, n = 10
    Output: 1024.00000   -> 10 * 10

    Input: x = 2.10000, n = 3
    Output: 9.26100     -> 3 * 3
     */
    public static void main(String[] args) {
        System.out.println(myPowIterativeOptimized(2.00000, 10));
    }

    //Approach 1: Brut force
        /*
        Time: O(n)
        Space: O(1)
         */
    public static double myPowBrutForce(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;
        return ans;
    }

    //Approach 3: Fast Power Algorithm Iterative
        /*
        Time: O(log n)
        Space: O(1)
         */
    public static double myPowIterativeOptimized(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) { //Not pair
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
}
