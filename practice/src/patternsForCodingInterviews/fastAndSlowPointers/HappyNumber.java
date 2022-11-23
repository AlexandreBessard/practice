package patternsForCodingInterviews.fastAndSlowPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743560700_15Unit
public class HappyNumber {
/*
Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
    public static void main(String[] args) {
        System.out.println(find(23));
        //System.out.println(find(12));
    }

    /*
    Time: O(log N)
    Space: O(1)
     */
    public static boolean find(int num) {
        int slow = num;
        int fast = num;
        //see if cycle is stuck on number 1 to find out if the number is happy or not
        do {
            slow = findSquareSum(slow); //Move one step
            fast = findSquareSum(findSquareSum(fast)); // Move 2 steps
        } while(slow != fast); //Found the cycle, both are stuck at 1
        return slow == 1; // see if the cycle is stuck on the number '1'
    }
    private static int findSquareSum(int num) {
        int sum = 0, digit;
        while(num > 0) {
            digit = num % 10; //Get the last digit ex: '23' get 3 and 2...
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

}
