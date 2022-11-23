package leetcode.strings;

//https://leetcode.com/problems/zigzag-conversion/
public class ZigzagConversion {
/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 Write the code that will take a string and make this conversion given a number of rows:
Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:
Input: s = "A", numRows = 1
Output: "A"
 */
    public static void main(String[] args) {
        String s1 = "PAYPALISHIRING";
        String s2 = "PAYPALISHIRING";
        System.out.println(convert(s1,3));
        System.out.println(convert(s2, 4));
    }

    //Correction: https://leetcode.com/problems/zigzag-conversion/discuss/3403/Easy-to-understand-Java-solution
    /*
    Time: O(N + R) where N is the length of the string and R is num of rows
    Space: O(n)
     */
    public static String convert(String s, int nRows) {
        int strLength = s.length();
        StringBuffer[] strBuilderArray = new StringBuffer[nRows];
        for (int i = 0; i < strBuilderArray.length; i++) {
            strBuilderArray[i] = new StringBuffer();
        }
        int currIndex = 0;
        while (currIndex < strLength) { //Process each letters from the string
            // vertically down
            for (int idx = 0; idx < nRows && currIndex < strLength; idx++) {
                strBuilderArray[idx].append(s.charAt(currIndex++));
            }
            // obliquely up
            for (int idx = nRows-2; idx >= 1 && currIndex < strLength; idx--) {
                strBuilderArray[idx].append(s.charAt(currIndex++));
            }
        }
        //Concatenated each rows to the first row in order to have a single result (first row)
        for (int idx = 1; idx < strBuilderArray.length; idx++) {
            strBuilderArray[0].append(strBuilderArray[idx]);
        }
        return strBuilderArray[0].toString(); //Return the first row which has been concatenated (see code just above)
    }
}
