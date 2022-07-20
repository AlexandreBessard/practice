package topInterviewQuestion.easy.others;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/722/
public class MissingNumber {

    public static void main(String[] args) {
        // 9,6,4,2,3,5,7,0,1
        //Output8 which is the missing number
    }


    //Approach 2: HashSet
    /*
    Time complexity: O(n)
    Space complexity: 0(n)
     */
    static int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums)
            numSet.add(num);
        int expectedNumCount = nums.length + 1;
        for(int number = 0; number < expectedNumCount; number++) {
            if(!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }

}
