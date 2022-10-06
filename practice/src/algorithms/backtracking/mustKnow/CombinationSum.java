package algorithms.backtracking.mustKnow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/combination-sum/ (can reuse same element)
//https://leetcode.com/problems/combination-sum-ii/ (can not reuse same element
public class CombinationSum {
    /*
    Example 1:
    Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

    Example 2:
    Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
     */
    public static void main(String[] args) {
        var obj = new CombinationSum();
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        int[] candidates3 = {3, 4, 5};
        int target3 = 9;
        List<List<Integer>> res;
        res = obj.combinationSum(candidates3, target3);
        for (List<Integer> l : res) {
            System.out.println(l);
        }
        /*
        res = obj.combinationSum2(candidates2, target1);
        System.out.println("Without replicate: ");
        for (List<Integer> l : res) {
            System.out.println(l);
        }

         */
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(nums); //Array mus be sorted
        backtrack(output, new LinkedList<>(), nums, target, 0);
        return output;
    }

    private void backtrack(List<List<Integer>> output,
                           LinkedList<Integer> currList,
                           int[] nums,
                           int remain,
                           int start) {
        if (remain == 0) { //Base case 1
            output.add(new ArrayList<>(currList));
        } else if (remain < 0) { //Base case 2
            return;
        } else {
            //Iterate all possbile candidates
            for (int i = start; i < nums.length; i++) {
                //Try this partial candidate solution
                currList.add(nums[i]);
                //Move on
                backtrack(output,
                        currList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                //Backtrack
                currList.removeLast();
            }
        }
    }

    //Combination Sum II (can't reuse same element) : https://leetcode.com/problems/combination-sum-ii/
    /*
    Time: O(2n)
    Space: 0(N)
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackSum2(list, new LinkedList<>(), nums, target, 0);
        return list;

    }

    private void backtrackSum2(List<List<Integer>> output, LinkedList<Integer> currList,
                               int[] nums, int remain, int start) {
        if (remain == 0) { //Base case 1
            output.add(new ArrayList<>(currList));
        } else if (remain < 0) { //Base case 2
            return;
        } else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue; // skip duplicates
                }
                currList.add(nums[i]);
                //Move forward
                backtrackSum2(output, currList, nums, remain - nums[i], i + 1);
                //Backtrack
                currList.removeLast();
            }
        }
    }


}
