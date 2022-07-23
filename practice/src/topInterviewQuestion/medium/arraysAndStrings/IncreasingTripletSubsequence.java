package topInterviewQuestion.medium.arraysAndStrings;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/781/
public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        int[] nums = {2,1,5,0,4,6};
        //true -> 0 < 4 < 6
        System.out.println(inscreasingTriplet(nums));
    }

    //Approach 1: Linear scan
    /*
    Time complexity: O(n)
    Space complexity: O(1)
     */
    static boolean inscreasingTriplet(int[] nums) {
        int first_num = Integer.MAX_VALUE;
        int second_num = Integer.MAX_VALUE;
        for(int n : nums) {
            if(n <= first_num) {
                first_num = n;
            } else if(n <= second_num) {
                second_num = n;
            } else {
                return true;
            }
        }
        return false;
    }


}
