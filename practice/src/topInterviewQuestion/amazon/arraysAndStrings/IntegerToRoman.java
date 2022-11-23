package topInterviewQuestion.amazon.arraysAndStrings;
//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2964/
public class IntegerToRoman {

    public static void main(String[] args) {
        /*
        Input: num = 1994
        Output: "MCMXCIV"
         */
        System.out.println(intToRoman(1994));
    }

    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    //Approach 1: Greedy
    /*
     A Greedy algorithm is an algorithm that makes the best possible decision at
     the current time; in this case taking out the largest possible symbol it can
     */
    /*
    Time complexity: O(1) because there is a finite set of roman numerals.
    Space complexity: O(1)
     */
    static String intToRoman(int num) {
        var sb = new StringBuilder();//Our result
        for(int i = 0; i < values.length && num > 0; i++) {
            while(values[i] <= num) { //True, we decrement num with the highest value possible
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
