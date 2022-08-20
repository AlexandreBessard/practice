package patternsForCodingInterviews.fastAndSlowPointers;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743560700_15Unit
public class HappyNumber {

    public static void main(String[] args) {
        System.out.println(find(23));
        System.out.println(find(12));
    }

    /*
    Time: O(log N)
    Space: O(1)
     */
    public static boolean find(int num) {
        int slow = num;
        int fast = num;
        do {
            slow = findSquareSum(slow); //Move one step
            fast = findSquareSum(findSquareSum(fast)); // Move 2 steps
        } while(slow != fast); //Found the cycle
        return slow == 1; // see if the cycle is stuck on the number '1'
    }
    private static int findSquareSum(int num) {
        int sum = 0, digit;
        while(num > 0) {
            digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

}
