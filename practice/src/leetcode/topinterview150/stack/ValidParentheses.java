package leetcode.topinterview150.stack;

import java.util.Stack;

public class ValidParentheses {

    // https://leetcode.com/problems/valid-parentheses/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        String s = "(({{}}))";
        System.out.println(isValid(s));
        s = "()(";
        System.out.println(isValid(s));
    }

    /*
    Stack
    Time: O(n) where n is the length of the input string
    Space: O(n), this space is used to store the characters in the stack
     */
    static boolean isValid(String s) {
        var stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        // if at the end we have an open element, the stack will not be empty
        // ex: ()(
        return stack.isEmpty();
    }

}
