package algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/permutations-ii/
public class PermutationII {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        for (List<Integer> num : permuteUnique(nums)) {
            System.out.println(num);
        }
    }

    static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtracking(res, nums, new LinkedList<>(), new boolean[nums.length]);
        return res;
    }

    private static void backtracking(List<List<Integer>> res, int[] nums, LinkedList<Integer> currList, boolean[] used) {
        //Base case
        if (currList.size() == nums.length) {
            res.add(new ArrayList<>(currList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if(used[i]) {
                    continue;
                }
                if(i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) { //'!used[i - 1]' -> means we already used this element by backtracking, avoid duplicates for this edge case: 1, 2, 2  (The first "2" has been visited and backtracked)
                    continue;
                }
                used[i] = true;
                currList.add(nums[i]);
                backtracking(res, nums, currList, used);
                used[i] = false;
                currList.removeLast();
            }
        }
    }
}
