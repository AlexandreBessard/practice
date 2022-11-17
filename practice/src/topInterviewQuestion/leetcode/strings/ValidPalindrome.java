package topInterviewQuestion.leetcode.strings;

public class ValidPalindrome {
/*
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.
 */
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        //Output true
        //amanaplanacanalpanama is palindrome
        System.out.println(isPalindrome(s));
        System.out.println(isPalindromeOtherApproach(s));
    }

    /*
    Two Pointers
    Time complexity: O(n) in length of string n
    Space complexity: 0(1)
     */
    static boolean isPalindrome(String s) {
        for(int left = 0, right = s.length() - 1; left < right; left++, right--) {
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
        }
        return true;
    }

    //Alternative approach
    /*
    Compare with reverse
    Time complexity: O(n): n is length of the string
    Space complexity: O(n) to stored filterString and reverseString
     */
    static boolean isPalindromeOtherApproach(String s) {
        var sb = new StringBuilder();
        for(char c: s.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        String filteredString = sb.toString();
        String reversedString = sb.reverse().toString();
        return filteredString.equals(reversedString);
    }

}
