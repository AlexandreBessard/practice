package patternsForCodingInterviews.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744050595_68Unit
public class SubsetsWithDuplicates {

    public static void main(String[] args) {
        List<List<Integer>> result;
        result = findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        /*
        result = findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

         */
    }

    /*
    Time: O(N * 2N)
    Space: O(2N)
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        //Sort the numbers to handle duplicates
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        int startIndex = 0;
        int endIndex = 0;
        for(int i = 0; i < nums.length; i++) {
            startIndex = 0;
            //If the current and the previous elements are the same, create a new subsets only
            //from the subsets added in the previous step
            if(i > 0 && nums[i] == nums[i - 1]) {
                startIndex = endIndex + 1;
            }
            endIndex = subsets.size() - 1;
            //Pointer 'j' based on the subsets array
            for(int j = startIndex; j <= endIndex; j++) {
                //Create a new subset from the existing subsets and add the current element to it
                List<Integer> set = new ArrayList<>(subsets.get(j));
                set.add(nums[i]);
                subsets.add(set);
            }
        }
        return subsets;
    }

}
