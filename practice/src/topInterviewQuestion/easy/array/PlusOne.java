package topInterviewQuestion.easy.array;
//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/559/
public class PlusOne {

    public static void main(String[] args) {
        int[] digits  = {4,3,2,1};
        //Output: [4, 3, 2, 2]
        int[] digits2 = {9};
        //Output : [1, 0]
        int[] digits3 = {9, 9, 9};
        for(int i : plusOne(digits3)) {
            System.out.print(i + ", ");
        }
    }



    //Approach 1: Schoolbook Addition with Carry
    /*
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int idx = n - 1; idx >= 0; idx--) {
            if(digits[idx] == 9)
                digits[idx] = 0;
            else {
                digits[idx]++;
                return digits;
            }
        }
        //We are here because all the digits are nines
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

}
