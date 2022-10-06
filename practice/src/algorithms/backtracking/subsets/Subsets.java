package algorithms.backtracking.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/subsets/
public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        for(List<Integer> res : subsets(nums)) {
            System.out.println(res);
        }
    }

    /*
    Time: O(N x 2n) -> generate all subsets and then copy them into output list
    Space: O(N)
     */
    static List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length + 1; ++i) { // 0 -> 1 -> 2 -> 3
            backtrack(0, new LinkedList<>(), nums, res, i);
        }
        return res;
    }

    private static void backtrack(int start, LinkedList<Integer> curr, int[] nums, List<List<Integer>> res, int maxSizeCurrArray) {
        // if the combination is done
        if (curr.size() == maxSizeCurrArray) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < nums.length; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums, res, maxSizeCurrArray);
            // backtrack
            curr.removeLast();
        }
    }


}
