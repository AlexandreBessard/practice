package leetcode.strings;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/4153/
public class CountAndSay {
/*
The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

countAndSay(1) = "1"
countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit. Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.

For example, the saying and conversion for digit string "3322251":
 */
    /*
    Must understand the logic:
 1.     1
 2.     11                  (one of one from previous ith element)
 3.     21                  (two of one)
 4.     1211                (one of Two, one of one) .....
 5.     111221
 6.     312211
 7.     13112221
 8.     1113213211
 9.     31131211131221
 10.   13211311123113112211
     */
    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }

    //Solution:
    //https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/4153/discuss/15995/Examples-of-nth-sequence
    //Time complexity: 0(n²)
    //Space complexity: 0(n) Caused by length of StringBuilder
    static String countAndSay(int n) {
        if(n <= 0)
            return "-1";
        String result = "1";
        for(int i = 1; i < n; i++) {
             result = build(result);
        }
        return result;
    }
    private static String build(String result) {
        var builder = new StringBuilder();
        int p = 0;
        while(p < result.length()) {
            char val = result.charAt(p);
            int count = 0;
            while(p < result.length() && result.charAt(p) == val) {
                p++;
                count++;
            }
            builder.append(String.valueOf(count));
            builder.append(val);
        }
        return builder.toString();
    }

}
