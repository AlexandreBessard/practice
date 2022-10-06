package algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/combination-sum-iii/
public class CombinationsIII {
    /*
    Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

    Only numbers 1 through 9 are used.
    Each number is used at most once.
    Return a list of all possible valid combinations. The list must not contain the same combination twice,
    and the combinations may be returned in any order.
     */
    public static void main(String[] args) {
        int k = 3;
        int n = 7;
        for (List<Integer> el : combinationSum3(n, k)) {
            System.out.println(el);
        }
    }

    /*
    Time: O(C(9, k)
    Space: O(N)
     */
    static List<List<Integer>> combinationSum3(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combination(res, new LinkedList<>(), k, 1, n);
        return res;
    }

    private static void combination(List<List<Integer>> ans, LinkedList<Integer> comb, int k, int start, int n) {
        if (comb.size() > k) {
            return;
        } else if (comb.size() == k && n == 0) {
            ans.add(new ArrayList<>(comb));
            return;
        } else {
            for (int i = start; i <= n && i <= 9; i++) {
                comb.add(i);
                combination(ans, comb, k, i + 1, n - i);
                comb.removeLast();
            }
        }
    }
}
