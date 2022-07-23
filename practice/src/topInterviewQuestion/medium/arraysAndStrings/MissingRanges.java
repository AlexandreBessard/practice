package topInterviewQuestion.medium.arraysAndStrings;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/782/
public class MissingRanges {

    public static void main(String[] args) {
        int[] nums = {0,1,3,50,75};
        int lower = 0, upper = 99;
        for(String s : findMissingRanges(nums, lower, upper)) {
            System.out.print(s + ", ");
        }
    }


    //Approach 1: Linear Scan
    /*
    Time complexity: O(n)
    Space complexity: 0(1) (do not count the output because no processing on it)
     */
    static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int prev = lower - 1;
        for(int i = 0; i <= nums.length; i++) {
            int curr = (i < nums.length) ? nums[i] : upper + 1;
            if(prev + 1 <= curr - 1) {
                result.add(formatRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return result;
    }
    private  static String formatRange(int lower, int upper) {
        if(lower == upper){
            return String.valueOf(lower);
        }
        return lower + "->" + upper;
    }




}
