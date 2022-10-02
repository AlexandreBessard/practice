package dataStructuresAndAlgorithms.backtracking;

//https://leetcode.com/problems/fair-distribution-of-cookies/
public class FairDistributionOfCookies {

    public static void main(String[] args) {
        int[] cookies = {8, 15, 10, 20, 8};
        int k = 2;
        var obj = new FairDistributionOfCookies();
        System.out.println(obj.distributeCookies(cookies, k));
    }

    //Backtracking
    //Correction: https://leetcode.com/problems/fair-distribution-of-cookies/discuss/2140935/Java-Backtracking-or-With-Explanation
    /*
    Time: O(k^n) where n is cookies.length
    Space: O(k+n) where k is children array, n is number of call stacks
     */
    int res = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        backtrack(cookies, 0, k, new int[k]); //Usr array children to store the total cookies for each child
        return res;
    }

    private void backtrack(int[] cookies, int curr, int k, int[] children) {
        if (curr == cookies.length) { //All cookies are distributed
            int max = 0;
            for (int c : children) {
                max = Math.max(max, c); // find the max cookie among children
            }
            res = Math.min(res, max);
            return;
        }
        for (int i = 0; i < k; i++) {
            children[i] += cookies[curr];
            //for each cookie distribute to any children
            backtrack(cookies, curr + 1, k, children);
            children[i] -= cookies[curr];
        }
    }
}
