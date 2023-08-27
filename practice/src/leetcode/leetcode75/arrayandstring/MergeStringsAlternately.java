package leetcode.leetcode75.arrayandstring;

//https://leetcode.com/problems/merge-strings-alternately/?envType=study-plan-v2&envId=leetcode-75
public class MergeStringsAlternately {

    /*
    Input: word1 = "abcd", word2 = "pq"
    Output: "apbqcd"
    Explanation: Notice that as word1 is longer, "cd" is appended to the end.
    word1:  a   b   c   d
    word2:    p   q
    merged: a p b q c   d
     */
    public static void main(String[] args) {
        String word1 = "abcd";
        String word2 = "pq";
        System.out.println(mergeAlternately(word1, word2));
    }

    /*
    Time: We traverse both strings once. O(n) where n is the length of the longer string.
    Space: O(n) where n is the length of the string returns as result.
     */
    public static String mergeAlternately(String word1, String word2) {
        var result = new StringBuilder();
        int i = 0;
        while (i < word1.length() || i < word2.length()) {
            if (i < word1.length()) {
                result.append(word1.charAt(i));
            }
            //true means we have a letter to append
            if (i < word2.length()) {
                result.append(word2.charAt(i));
            }
            i++;
        }
        return result.toString();
    }

}
