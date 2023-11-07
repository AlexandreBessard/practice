package leetcode.topinterview150.arrayString.easy;

public class FindTheIndexOfTheFirstOccurrenceInAString {

    // https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/?envType=study-plan-v2&envId=top-interview-150
    /*
    Given two strings needle and haystack,
    return the index of the first occurrence of needle in haystack,
    or -1 if needle is not part of haystack.
     */
    public static void main(String[] args) {
        String haystack = "sadbuttsad";
        String needle = "sad";
        System.out.println(strStr(haystack, needle));
        haystack = "leetcode";
        needle = "leeco";
        System.out.println(strStr(haystack, needle));
    }

    // Use this template: https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63dd9b83488110f74a92ffbc
    /*
    Sliding window
     */
    static int strStr(String haystack, String needle) {
        // Try to extend the range
        for (int idxHaystack = 0; idxHaystack < haystack.length(); idxHaystack++) {
            // exit because we are sure that needle does not match, we have not
            // enough letters left to match the entire needle
            if (idxHaystack > haystack.length() - needle.length()) {
                break;
            }
            int idxNeedle = 0;
            // true means we have a match
            while (idxNeedle < needle.length()
                    && needle.charAt(idxNeedle) == haystack.charAt(idxHaystack)) {
                // we move forward
                idxNeedle++;
                idxHaystack++;
                // True means it is a complete match
                if (idxNeedle == needle.length()) {
                    // return the index where the needle start matching with the haystack
                    return idxHaystack - idxNeedle;
                }
            }
        }
        return -1;
    }

}
