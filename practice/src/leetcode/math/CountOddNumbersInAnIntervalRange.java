package leetcode.math;
//https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
public class CountOddNumbersInAnIntervalRange {
/*
Given two non-negative integers low and high.
Return the count of odd numbers between low and high (inclusive).
 */
    public static void main(String[] args) {
        int low = 3, high = 7;
        System.out.println(countOdds(low, high));
    }

    /*
    Time: O(1) math operation is constant time
    Space: O(1)
     */
    public static int countOdds(int low, int high) {
        if(low % 2 == 0) { //true means even number
            low++;
        }
        if(high % 2 == 0) { //true means even number
            high--;
        }
        //Divide the range by 2 and add 1
        return (high - low) / 2 + 1;
    }



}
