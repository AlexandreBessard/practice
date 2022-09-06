package topInterviewQuestion.facebook.arraysAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//https://leetcode.com/explore/interview/card/facebook/53/recursion-3/293/
public class Permutations2 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        var obj = new Permutations2();
        List<List<Integer>> res = obj.permuteUnique(nums);
        for(List<Integer> l : res) {
            System.out.println(l);
        }
    }

    //Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    //TODO: need to be reviewed to understand used[] array
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
