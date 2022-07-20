package topInterviewQuestion.easy.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/721/
public class ValidParentheses {

    public static void main(String[] args) {
    /*
    Input: s = "()[]{}"
    Output: true

    Input: s = "(]"
    Output: false
     */
    }


    private static Map<Character, Character> mappings = new HashMap<>() {
        {
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }
    };

    /*
    Time complexity: O(N), we traverse String one character at a time
    push and pop operations take O(1) time
    Space complexity: 0(n)  if worst case : '(((((((((((((((((((('
     */
    static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(mappings.containsKey(c)) {
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                if(topElement != mappings.get(c))
                    return false;
                } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
