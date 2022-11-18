package leetcode.array;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/782/
public class MissingRanges {
/*
You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
Return the smallest sorted list of ranges that cover every missing number exactly.
That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 */
    public static void main(String[] args) {
        int[] nums = {0,1,3,50,75};
        int lower = 0, upper = 99;
        for(String s : findMissingRanges1(nums, lower, upper)) {
            System.out.print(s + ", ");
        }
    }

    //Approach correction: https://leetcode.com/problems/missing-ranges/discuss/50468/Accepted-Java-solution-8-lines-and-0ms
    static List<String> findMissingRanges1(int[] nums, int lower, int upper) { // 0,1,3,50,75  ||  0  , 99
        List<String> res = new ArrayList<>(); //contains only the missing range or number
        for(int curr : nums) {
            if(curr > lower) { //true if the range is missing
                res.add(toString(lower, curr - 1));
            }
            if(curr == upper) {
                return res; //Avoid overflow
            }
            //update lower
            lower = curr + 1;
        }
        if(lower <= upper) { //latest range remaining since we looped through the entire array
            res.add(toString(lower, upper));
        }
        return res;
    }
    private static String toString(int start, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        if(start < end) { //We have a missing range
            sb.append("->").append(end);
        }
        return sb.toString();
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
            int curr = (i < nums.length) ? nums[i] : upper + 1; //Check if 'i' is potentially in the current range
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
