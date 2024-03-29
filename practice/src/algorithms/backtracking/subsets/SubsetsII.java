package algorithms.backtracking.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/subsets-ii/
public class SubsetsII {
    /*
    Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
    The solution set must not contain duplicate subsets. Return the solution in any order.
     */
    public static void main(String[] args) {
        var obj = new SubsetsII();

        List<List<Integer>> resultBacktracking = obj.subsetsWithDup(new int[]{1, 2, 2});
        for (List<Integer> l : resultBacktracking) {
            System.out.println(l);
        }
    }

    //Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/
    /*
    Time: O(N * 2n) to generate all subsets and then copy them into output list
    Space: O(N), to maintain the list and modifying list in place. We do not count space for output
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length + 1; i++) { //0 -> 1 -> 2 -> 3
            backtrack(output, new LinkedList<>(), nums, 0, i);
        }
        return output;
    }

    private void backtrack(List<List<Integer>> list, LinkedList<Integer> currList, int[] nums, int start, int maxSize) {
        if (currList.size() == maxSize) {
            list.add(new ArrayList<>(currList));
        } else {
            //Iterate all possible candidate
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) { //skip duplicates
                    continue; // skip duplicates
                }
                //Candidate solution
                currList.add(nums[i]);
                //Move on to the next elements
                backtrack(list, currList, nums, i + 1, maxSize);
                //backtrack removing the number before moving onto the next
                currList.removeLast();
            }
        }
    }

}
