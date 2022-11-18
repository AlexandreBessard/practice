package leetcode.array;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/781/
public class IncreasingTripletSubsequence {
    /*
    Given an integer array nums, return true if there exists a
    triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k].
    If no such indices exists, return false.
     */
    public static void main(String[] args) {
        int[] nums = {2,1,5,0,4,6};
        //true -> 0 < 4 < 6
        System.out.println(increasingTriplet(nums));
    }

    //Approach 1: Linear scan
    /*
    Time complexity: O(n)
    Space complexity: O(1)
     */
    static boolean increasingTriplet(int[] nums) {
        //start with the largest values, both have been updated, return true
        int firstSmallerNum = Integer.MAX_VALUE; // first element
        int secondBiggerNum = Integer.MAX_VALUE; // second element
        for(int currValue : nums) { //Loop through each value
            if(currValue <= firstSmallerNum) { //Update first to get the smallest value
                firstSmallerNum = currValue;
            } else if(currValue <= secondBiggerNum) { //Update second to get a bigger value
                secondBiggerNum = currValue;
            } else {
                return true; //We know that the current element is bigger than the first and second element
            }
        }
        return false;
    }


}
