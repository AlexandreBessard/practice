package topInterviewQuestion.facebook.topMostFrequentQuestions;
//https://leetcode.com/problems/custom-sort-string/
public class CustomSortString {

    /*
    Permute the characters of s so that they match the order that order was sorted.
    More specifically, if a character x occurs before a character y in order,
    then x should occur before y in the permuted string.
     */
    public static void main(String[] args) {
        String order = "cba";
        String s = "abcd";
        String order1 = "cbafg";
        String s1 = "abcd";
        //We rearrange s because c appears first (come first, then b and a) in order, repect that order
        //in String s. d does not appear, it can be at any position.
        //Output: cbad
        System.out.println(customSortString(order1, s1));
    }

    //Approach 1: Count and write
    /*
    Time: O(S.length + T.length) to iterate through S and T
    Space: O(T.length)
     */
    static String customSortString(String order, String s) {
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        // ans will be our final answer.  We use StringBuilder to join
        // the answer so that we more efficiently calculate a
        // concatenation of strings.
        var ans = new StringBuilder();
        // Write all characters that occur in S, in the order of S.
        for(char c : order.toCharArray()) {
            for(int i = 0; i < count[c - 'a']; i++) {
                ans.append(c);
            }
            // Setting count[char] to zero to denote that we do
            // not need to write 'char' into our answer anymore.
            count[c - 'a'] = 0;
        }
        // Write all remaining characters that don't occur in S.
        // That information is specified by 'count'.
        for(char c = 'a'; c <= 'z'; c++) { //26 letters
            for(int i = 0; i < count[c - 'a']; i++) { //True if count[c - 'a'] is 1 or more
                ans.append(c);
            }
        }
        return ans.toString();
    }





}
