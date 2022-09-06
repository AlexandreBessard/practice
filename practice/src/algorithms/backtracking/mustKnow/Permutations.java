package algorithms.backtracking.mustKnow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/permutations/  (without duplicate)
//https://leetcode.com/problems/permutations-ii/  (duplicate)
public class Permutations {

    public static void main(String[] args) {
        var obj = new Permutations();

        List<List<Integer>> resultBacktracking  = obj.permute(new int[]{1, 2, 3});
        for(List<Integer> l : resultBacktracking) {
            System.out.println(l);
        }
    }

    //Without duplicate
    //Permutations : https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(output, new ArrayList<>(), nums);
        return output;
    }

    private void backtrack(List<List<Integer>> output, List<Integer> currList, int [] nums){
        if(currList.size() == nums.length){
            output.add(new ArrayList<>(currList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(currList.contains(nums[i])) continue; // element already exists, skip
                currList.add(nums[i]);
                backtrack(output, currList, nums);
                currList.remove(currList.size() - 1);
            }
        }
    }


    //Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }


}
