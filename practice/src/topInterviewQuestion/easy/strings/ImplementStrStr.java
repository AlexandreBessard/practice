package topInterviewQuestion.easy.strings;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/885/
public class ImplementStrStr {

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
    static int strStr(String haystack, String needle) {
        if(needle.length() == 0)
            return 0;
        if(haystack.length() == 0)
            return -1;
        for(int i = 0; i < haystack.length(); i++) {
            //no enough places for needle after i
            if((i + needle.length()) > haystack.length())
                break;
            for(int j = 0; j < needle.length(); j++) {
                if(haystack.charAt(i + j) != needle.charAt(j))
                    break;
                if(j == needle.length() - 1)
                    return i;
            }
        }
        return -1;
    }







}
