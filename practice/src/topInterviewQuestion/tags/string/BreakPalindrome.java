package topInterviewQuestion.tags.string;
//https://leetcode.com/problems/break-a-palindrome/
public class BreakPalindrome {
    /*
    Input: palindrome = "abccba"
    Output: "aaccba"
    Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
    Of all the ways, "aaccba" is the lexicographically smallest.
     */
    public static void main(String[] args) {
        String palindrome = "abccba";
        var obj = new BreakPalindrome();
        System.out.println(obj.breakPalindrome(palindrome));
    }

    //Approach 1: Greedy
    public String breakPalindrome(String palindrome) {
        int length = palindrome.length();
        if(length == 1)
            return "";
        //Strings are immutable in Java, convert it into a char array
        char[] palindromeArray = palindrome.toCharArray();
        for(int i = 0; i < length / 2; i++) { //Palindrome
            if(palindromeArray[i] != 'a') {
                palindromeArray[i] = 'a';
                return String.valueOf(palindromeArray);
            }
        }
        palindromeArray[length - 1] = 'b'; //in case if 'aaaaaaa'
        return String.valueOf(palindromeArray);
    }

}
