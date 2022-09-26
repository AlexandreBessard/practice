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
        if(currList.size() == nums.length){ //Base case
            output.add(new ArrayList<>(currList)); //add to the result
        } else{
            for(int i = 0; i < nums.length; i++){
                if(currList.contains(nums[i])) continue; // element already exists, skip
                currList.add(nums[i]);
                backtrack(output, currList, nums); //get the next element
                currList.remove(currList.size() - 1); //Backtrack
            }
        }
    }
}
