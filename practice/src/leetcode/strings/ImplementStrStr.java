package leetcode.strings;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/885/
public class ImplementStrStr {
    /*
    Given two strings needle and haystack,
    return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     */
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        //Output : 2 because entire second string in include in hello word
        System.out.println(strStr(haystack, needle));
    }

    /*
    Time complexity: O(n²)
    Space complexity: O(1)
     */
    static int strStr(String haystack, String needle) { // sadbutsad   //  sad
        if(needle.length() == 0) {
            return 0;
        }
        if(haystack.length() == 0) {
            return -1;
        }
        for(int i = 0; i < haystack.length(); i++) { //Loop through the longest string element
            //no enough places for needle after i
            if((i + needle.length()) > haystack.length()) {
                break;
            }
            for(int j = 0; j < needle.length(); j++) {
                //i is like our starting index.
                if(haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if(j == needle.length() - 1) { //We have reached the end of the word, return i (starting index)
                    return i; //Return the first index with occurence
                }
            }
        }
        return -1;
    }







}
