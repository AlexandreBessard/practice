package leetcode.strings;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/reverse-vowels-of-a-string/
public class ReverseVowelsOfAString {

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(reverseVowels(s));
    }

    //Approach 1: Two pointers
    /*
    Time: O(n)
    Space: O(n)
     */
    static final Set<Character> vowels = new HashSet<>(List.of(
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    );
    public static String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chrs = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            //Find the left most vowel
            while (left < right && !vowels.contains(s.charAt(left))) { //True means it is not a vowel
                left++;
            }
            //Find the right most vowel
            while (left < right && !vowels.contains(s.charAt(right))) {
                right--;
            }
            //Swap between these two pointers where vowels are located
            swap(chrs, left, right);
            //Move the two pointers
            left++;
            right--;
        }
        return new String(chrs);
    }

    private static void swap(char[] chrs, int left, int right) {
        char temp = chrs[left];
        chrs[left] = chrs[right];
        chrs[right] = temp;
    }
}
