package algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/combination-sum-ii/
public class CombinationsII {
    //Each number in candidates may only be used once in the combination.
    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        for (List<Integer> comb : combinationsSum2(nums, target)) {
            System.out.println(comb);
        }
    }

    static List<List<Integer>> combinationsSum2(int[] nums, int target) {
        Arrays.sort(nums); //Array must be sorted, else this algo does not work
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtracking(0, nums, target, new LinkedList<>(), res, used);
        return res;
    }

    private static void backtracking(int start, int[] nums, int target,
                                     LinkedList<Integer> currList, List<List<Integer>> res, boolean[] used) {
        if (target == 0) {
            res.add(new ArrayList<>(currList));
        } else if (target < 0) {
            return;
        } else {
            for (int i = start; i < nums.length; i++) {
                if(i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                currList.add(nums[i]);
                backtracking(start + 1, nums, (target - nums[i]), currList, res, used);
                currList.removeLast();
            }
        }
    }
}
