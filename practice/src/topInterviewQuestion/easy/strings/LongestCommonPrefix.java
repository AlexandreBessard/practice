package topInterviewQuestion.easy.strings;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/887/
public class LongestCommonPrefix {

    /*
    Return longest common prefix string amongst an array of strings.
     */
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String[] strs1 = {"leetcode","leet","lee", "le"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefixDividAndConquer(strs1));
    }
    /*
    Approach 2: Divide & Conquer
    Time complexity: O(S) where S in the number of all characters.
    Space complexity: O(m . log n)
    there are 'log n' recursive calls, each store 'm' space to store the result.
     */
    static String longestCommonPrefixDividAndConquer(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }
    private static String longestCommonPrefix(String[] strs,
                                              int l,
                                              int r)
    {
        if(l == r)
            return strs[l];
        int mid = (l + r) / 2;
        String lcpLeft = longestCommonPrefix(strs, l, mid);
        String lcpRight = longestCommonPrefix(strs, mid + 1, r);
        return commonPrefix(lcpLeft, lcpRight);
    }
    private static String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for(int i = 0; i < min; i++) {
            if(left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
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
