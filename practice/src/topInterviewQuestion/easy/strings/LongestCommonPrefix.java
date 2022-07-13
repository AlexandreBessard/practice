package topInterviewQuestion.easy.strings;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/887/
public class LongestCommonPrefix {

    /*
    Return longest common prefix string amongst an array of strings.
     */
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }


    //Approach 1: Horizontal scanning
    /*
    Time complexity: O(S) where S is the sum of all characters in all Strings
    Space complexity: O(1). Only constant space.
     */
    static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";
            }
        }
        return prefix;
    }
}
