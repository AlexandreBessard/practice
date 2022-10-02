package dataStructuresAndAlgorithms.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/increasing-subsequences/
public class IncreasingSubsequences {

    public static void main(String[] args) {
        //int[] nums = {4, 6, 7, 7};
        int[] nums = {4, 6, 7};
        var obj = new IncreasingSubsequences();
        for(List<Integer> res : obj.findSubsequencesBacktracking(nums)) {
            System.out.println(res);
        }
    }

    //Backtracking
    //Correction: https://leetcode.com/problems/increasing-subsequences/discuss/1319745/java-easy-backtracking-solution
    public List<List<Integer>> findSubsequencesBacktracking(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> holder = new ArrayList<>();
        findsequence(res, holder, 0, nums);
        return new ArrayList<>(res);
    }
    private void findsequence(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
        if (holder.size() >= 2) {
            res.add(new ArrayList<>(holder));
        }
        for (int i = index; i < nums.length; i++) {
            if(holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) { //Check if prev value is less than the current
                holder.add(nums[i]);
                findsequence(res, holder, i + 1, nums);
                holder.remove(holder.size() - 1);
            }
        }
    }
}
