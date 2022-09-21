package topInterviewQuestion.facebook.topMostFrequentQuestions;

//https://leetcode.com/problems/maximum-swap/
public class MaximumSwap {
    /*
    You are given an integer num. You can swap two digits at most once to get the maximum valued number.
    Return the maximum valued number you can get.
     */

    public static void main(String[] args) {
        int num = 2736;
        //Output: 7236
        int num1 = 76321; // -> Decreasing order
        int num2 = 1239;
        System.out.println(maximumSwap(num2));
    }

    /*
    Concept to remember, if a number is continously decreasing, we dont have anything to swap , as the number is already the largest possible.
    For eg, 76321

     */
    static int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }
        for (int i = 0; i < digits.length; i++) { //check fot each number who can be swapped
            for (int k = 9; k > digits[i] - '0'; k--) { //from 9 because it is the highest number
                if (buckets[k] > i) { //Means we swap because we have a bigger value from the bucket
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.parseInt(new String(digits));
                }
            }
        }
        return num;
    }


}
