package algorithms.dynamicProgramming.commonPatternsContinued;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/learn/card/dynamic-programming/633/common-patterns-continued/4136/
public class CountingDP {
    //Number of distinct way
    //https://leetcode.com/explore/learn/card/dynamic-programming/633/common-patterns-continued/4137/
    static class PaintFence {

        public static void main(String[] args) {
            int n = 3, k = 2;
            var obj = new PaintFence();
            //Output 6
            System.out.println(obj.numWays(n, k));
        }

        //Given the two integers n and k, return the number of ways you can paint the fence.
        private Map<Integer, Integer> memo = new HashMap<>();
        /*
        Time complexity: O(n)
        Space complexity: O(N) (Recursion stack)
         */
        public int numWays(int n, int k) {
            return totalWays(n, k); //One argument i, need one dimentional array
        }
        //k: number of different colors
        //i:
        // will determine the number of ways you can paint i fence posts.
        private int totalWays(int i, int k) { //k ways to paint the fence posts
            if(i == 1)
                return k;
            if(i == 2) // Allowed to paint two posts in a row with the same color
                // If we have two posts, then there are k * k ways to paint it
                // (since we are allowed to paint have two posts in a row be the same color).
                // Therefore, totalWays(1) = k, totalWays(2) = k * k.
                return k * k;
            // Check if we have already calculated totalWays(i)
            if (memo.containsKey(i)) {
                return memo.get(i);
            }
            //Recurrence:
            memo.put(i,
                    (k - 1) //Use different color than previous post
                            * totalWays(i - 1, k)
//
                            + (k - 1) //same color
                            //It is allowed to have 2 consecutive post with the same color
                            * totalWays(i - 2, k));
            return memo.get(i);
        }
    }


    //----------------------- BOTTOM UP
    /*
    Approach 3: Bottom-Up, Constant Space

    Time complexity: O(n)
    Space complexity;: O(1)
     */
    public int numWays(int n, int k) {
        if (n == 1) return k;
        int twoPostsBack = k;
        int onePostBack = k * k;
        for (int i = 3; i <= n; i++) {
            int curr = (k - 1) * (onePostBack + twoPostsBack);
            twoPostsBack = onePostBack;
            onePostBack = curr;
        }
        return onePostBack;
    }

}
