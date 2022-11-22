package leetcode.strings;

import java.util.HashSet;
import java.util.Set;

public class RemoveAllAdjacentDuplicatesInString {
/*
Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
Input: s = "abbaca"
Output: "ca"
Explanation:
For example, in "abbaca" we could remove "bb" since the letters are adjacent
and equal, and this is the only possible move.
The result of this move is that the string is "aaca",
of which only "aa" is possible, so the final string is "ca".
 */
    public static void main(String[] args) {
        String s = "abbaca";
        String s1 = "azxxzy";
        System.out.println(removeDuplicate(s));
        System.out.println(removeDuplicate(s1));
    }

    //Approach 2: Stack
    /*
    It is like a stack, the end to the string builder is the priority
    Time: O(N) where N is a string length
    Space: O(N - D) Where D is a total length for all duplicates
     */
    public static String removeDuplicate(String s) {
        var strBuilder = new StringBuilder(); //Used as a stack
        int strLength = strBuilder.length();
        for(char character : s.toCharArray()) {
            //Check if we have letter and same letter between string and end of the string builder
            if(strLength != 0 && character == strBuilder.charAt(strLength - 1)) {
                strBuilder.deleteCharAt(strLength - 1); //Remove  the latest letter
                strLength--; //decrement size
            } else {
                strBuilder.append(character); //add letter to the stack (string builder)
                strLength++; //increment the length by 1 (one character added to the stringBuilder object)
            }
        }
        return strBuilder.toString();
    }
}
