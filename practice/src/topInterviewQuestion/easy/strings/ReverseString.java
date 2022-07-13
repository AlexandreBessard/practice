package topInterviewQuestion.easy.strings;

public class ReverseString {

    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        //Output: "o","l","l","e","h"
        reverseRecursivve(s);
        for(char c: s){
            System.out.print(c + ", ");
        }
    }

    //Approach 2, Recursive (Space complexity: O(n)
    static void reverseRecursivve(char[] s) {
        helper(s, 0, s.length - 1);
    }
    static void helper(char[] s, int left, int right) {
        if(left >= right) //Base case
            return;
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        helper(s, ++left, --right);
    }
    //Approach 2 O(1) space
    //Time complexity: O(N)
    static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while(left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
