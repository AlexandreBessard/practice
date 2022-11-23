package leetcode.others;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/722/
public class MissingNumber {
/*
Given an array nums containing n distinct numbers in the range [0, n],
return the only number in the range that is missing from the array.
 */
    public static void main(String[] args) {
        // 9,6,4,2,3,5,7,0,1
        //Output8 which is the missing number
        int[] num = {3,0,1};
        System.out.println(missingNumber(num));
    }

    //Approach 2: HashSet
    /*
    Time complexity: O(n)
    Space complexity: 0(n)
     */
    static int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums) {
            numSet.add(num);
        }
        int expectedNumCount = nums.length + 1;
        for(int number = 0; number < expectedNumCount; number++) {
            //True if not contained (missing)
            if(!numSet.contains(number)) { //True if we miss that number
                return number;
            }
        }
        return -1;
    }

}
