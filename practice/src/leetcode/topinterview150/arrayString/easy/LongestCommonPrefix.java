package leetcode.topinterview150.arrayString.easy;

public class LongestCommonPrefix {

    // https://leetcode.com/problems/longest-common-prefix/?envType=study-plan-v2&envId=top-interview-150
    /*
    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".
     */
    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefixBrutForce(strs));
        strs = new String[]{"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefixBrutForce(strs));
        strs = new String[]{"flower", "flow", "floght"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefixBrutForce(strs));
    }

    /*
    Binary Search
    Time: O(S * log(minLength))
    Space: O(1)
     */
    static String longestCommonPrefix(String[] strs) {
        // Base case
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLengthWord = Integer.MAX_VALUE;
        // Get the smallest word
        for (String str : strs) {
            minLengthWord = Math.min(str.length(), minLengthWord);
        }
        int left = 1; // index-based 1
        // index-based 1 because we exclude the latest element using substring()
        int right = minLengthWord;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isCommonPrefix(strs, mid)) {
                //it is a match, move forward from the left side
                left = mid + 1;
            } else {
                // decrease backward from the right side because no match
                right = mid - 1;
            }
        }
        // index-based 1, true means no match
        if (left == 1) {
            return "empty";
        }
        return strs[0].substring(0, left - 1);
    }

    private static boolean isCommonPrefix(String[] strs, int length) {
        // Get the prefix from the first array element
        String prefix = strs[0].substring(0, length);
        // loop through each element from the array
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(prefix)) {
                // Prefix does not match
                return false;
            }
        }
        // The prefix matches with all elements from the array
        return true;
    }

    /*
    ------------------------------------------------------------------------
    Brut force
    Time: O(n * m) where n is the average length of strings and m is the number of strings
    Space: O(1)
     */
    static String longestCommonPrefixBrutForce(String[] strs) {
        // Base case
        if (strs == null || strs.length == 0) {
            return "";
        }
        // Init with the first string as the prefix to be compared
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String current = strs[i];
            int idx = 0;
            // Compare characters of the current string with the prefix
            while (idx < prefix.length()
                    && idx < current.length()
                    && prefix.charAt(idx) == current.charAt(idx)) {
                idx++;
            }
            //Update the prefix to be the common part
            prefix = prefix.substring(0, idx);
            // if prefix become an empty string, no need to continue
            if (prefix.isEmpty()) {
                return "empty";
            }
        }
        return prefix;
    }
}
