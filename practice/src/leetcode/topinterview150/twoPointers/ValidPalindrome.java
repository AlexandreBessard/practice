package leetcode.topinterview150.twoPointers;

public class ValidPalindrome {

    //https://leetcode.com/problems/valid-palindrome/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        String input = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(input));
        input = " ";
        System.out.println(isPalindrome(input));
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    static boolean isPalindrome(String input) {
        if (input.isEmpty()) {
            return true;
        }
        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(input.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(input.charAt(right))) {
                right--;
            }

            char leftChar = Character.toLowerCase(input.charAt(left));
            char rightChar = Character.toLowerCase(input.charAt(right));

            if (leftChar != rightChar) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

}
