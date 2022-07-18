package topInterviewQuestion.easy.array;
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/567/
//Solution: https://leetcode.com/problems/move-zeroes/discuss/172432/THE-EASIEST-but-UNUSUAL-snowball-JAVA-solution-BEATS-100-(O(n))-%2B-clear-explanation
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
    }

    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static void moveZeroes(int[] nums) {
        int snowBallSize = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                snowBallSize++;
            } else if(snowBallSize > 0) {
                int t = nums[i];
                nums[i] = 0;
                nums[i - snowBallSize] = t;
            }
        }
    }

}
