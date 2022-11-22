package leetcode.strings;
//https://leetcode.com/problems/reverse-words-in-a-string-iii/
public class ReverseWordsInAStringIII {
/*
Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
Example 1:
Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Example 2:
Input: s = "God Ding"
Output: "doG gniD"
 */
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        String s1 = "God Ding";
        System.out.println(reverseWords(s));
        System.out.println(reverseWords(s1));
    }

    //Approach 2: Two pointers
    /*
    Time: O(n) --> O(N + N) = O(n)
    Space: O(1)
     */
    public static String reverseWords(String s) {
        char[] chrArray = s.toCharArray();
        for(int start = 0; start < chrArray.length; start++) {
            //Move to the next element if it is a space
            if(chrArray[start] != ' ') { //True if non-space (letter)
                int end = start; //Use to know where the word is going to end
                //Check if it is the latest letter and if next letter is not a space (entire word) increment
                while(end + 1 < chrArray.length
                        && chrArray[end + 1] != ' ') {
                    //Increase the window
                    end++; //move end to the end of the word
                }
                //reverse the string between the window start and end
                reverse(chrArray, start, end);
                start = end; //Index where the next word start
            }
        }
        return new String(chrArray);
    }
    private static void reverse(char[] ca, int start, int end) {
        for(; start < end; end--) {
            char temp = ca[start];
            ca[start] = ca[end];
            ca[end] = temp;
        }
    }



}
