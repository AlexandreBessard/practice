package leetcode.topinterview150.arrayString.easy;

public class LengthOfLastWord {

    // https://leetcode.com/problems/length-of-last-word/?envType=study-plan-v2&envId=top-interview-150

    /*
    Given a string s consisting of words and spaces,
    return the length of the last word in the string.

    A word is a maximal substring consisting of non-space characters only.
     */
    public static void main(String[] args) {
        String s = "Hello World";
        System.out.println(lengthOfLastWorld(s));
        s = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWorld(s));
    }

    static int lengthOfLastWorld(String s) {
        // leading and trailing space removed
        String str = s.trim();
        int count = 0;
        // start from the end
        for (int i = str.length() - 1; i >= 0; i--) {
            // Skip it if it is a number or space
            if (str.charAt(i) != ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

}
