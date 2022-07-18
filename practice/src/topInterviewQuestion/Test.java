package topInterviewQuestion;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String[] strs2 = {"leets", "leetcode", "leetc", "leeds"};
        System.out.println(longestCommonPrefixBinarySearch(strs2));
    }

    static String longestCommonPrefixBinarySearch(String[] strs) {
        int minLen = Integer.MAX_VALUE;
        for(String s: strs) {
            minLen = Math.min(minLen, s.length());
        }
        int low = 0;
        int high = minLen;
        while(low < high) {
            int mid = (low + high) / 2;
            if(isCommonPrefix(strs, mid)){
                //Increment mid
                low = mid + 1;
            } else {
                //Decrement mid
                high = mid - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }
    private static boolean isCommonPrefix(String[] strs, int idx) {
        String prefix = strs[0].substring(0, idx);
        for(int i = 0; i < strs.length; i++) {
            if(!strs[i].startsWith(prefix))
                return false;
        }
        return true;
    }
}
