package topInterviewQuestion.easy.strings;

public class ValidPalindrome {

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
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while(i < j && !Character.isLetterOrDigit(s.charAt(i))){
                i++;
            }
            while(i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if(Character.toLowerCase(s.charAt(i))
                    != Character.toLowerCase(s.charAt(j)))
                return false;
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
