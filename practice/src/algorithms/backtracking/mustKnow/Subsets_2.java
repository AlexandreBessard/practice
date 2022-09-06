package algorithms.backtracking.mustKnow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/subsets-ii/
public class Subsets_2 {

    public static void main(String[] args) {
        var obj = new Subsets_2();

        List<List<Integer>> resultBacktracking = obj.subsetsWithDup(new int[]{1, 2, 3});
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
        backtrack(output, new ArrayList<>(), nums, 0);
        return output;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> currList, int[] nums, int start) {
        list.add(new ArrayList<>(currList));
        //Iterate all possible candidate
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            //Candidate solution
            currList.add(nums[i]);
            //Move on to the next elements
            backtrack(list, currList, nums, i + 1);
            //backtrack removing the number before moving onto the next
            currList.remove(currList.size() - 1);
        }

    }

}
