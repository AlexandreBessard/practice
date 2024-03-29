package leetcode.strings;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/887/
public class LongestCommonPrefix {
    /*
    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".
     */
    public static void main(String[] args) {
        String[] strs = {"flow", "flower", "flight"};
        String[] strs1 = {"leetcode", "leet", "lee", "le"};
        String[] strs2 = {"leets", "leetcode", "leetc", "leeds"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefixDividAndConquer(strs1));
        //System.out.println("Binary Search : " + longestCommonPrefixBinarySearch(strs));
        System.out.println("Binary Search : " + longestCommonPrefixBinarySearch(new String[]{"test"}));

    }

    /*
    Approach 4: BinarySearch
     */
    static String longestCommonPrefixBinarySearch(String[] strs) { //  "flower","flow","flight"
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) { //Get the min length of a word
            minLen = Math.min(minLen, str.length()); //4
        }
        int low = 1; //Start with index 1-indexed
        int high = minLen; // 4 // End with 1-indexed because we exclude the latest element with substring()
        //Binary Search
        while (low <= high) {  // 1, 2, 3, 4
            int middle = low + (high - low) / 2; //2
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, int length) {
        String str1 = strs[0].substring(0, length);
        for (int i = 1; i < strs.length; i++) { //If array of length 1, it is not true, skip the for loop
            if (!strs[i].startsWith(str1)) //Test if string starts with the specified prefix
                return false;
        }
        return true;
    }

    /*
    Approach 2: Divide & Conquer
    Time complexity: O(S) where S in the number of all characters.
    Space complexity: O(m . log n)
    there are 'log n' recursive calls, each store 'm' space to store the result.
     */
    static String longestCommonPrefixDividAndConquer(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private static String longestCommonPrefix(String[] strs,
                                              int l,
                                              int r) {
        if (l == r)
            return strs[l];
        int mid = (l + r) / 2;
        String lcpLeft = longestCommonPrefix(strs, l, mid);
        String lcpRight = longestCommonPrefix(strs, mid + 1, r);
        return commonPrefix(lcpLeft, lcpRight);
    }

    private static String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
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
    static String longestCommonPrefix(String[] strs) { // "flower", "flow", "flight"
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            //IndexOf() returns -1 each time the string is not the same
            // else 0 if string into the prefix is entirely contains in the String
            //Returns the index within this string of the first occurrence of the specified substring.
            while (strs[i].indexOf(prefix) != 0) { // ex: 'flow'er is entirely contained  in 'flow'  return 0
                prefix = prefix.substring(0, prefix.length() - 1); //Remove each letter at the end 1 by 1
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}
