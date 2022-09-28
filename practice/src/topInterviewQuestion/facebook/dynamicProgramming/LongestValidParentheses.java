package topInterviewQuestion.facebook.dynamicProgramming;

import java.util.Stack;

//https://leetcode.com/explore/interview/card/facebook/55/dynamic-programming-3/3035/
public class LongestValidParentheses {

    public static void main(String[] args) {
        String s = "(()";
        String s1 = ")()())";
        var obj = new LongestValidParentheses();
        System.out.println(obj.longestValidParentheses(s1));
        System.out.println(obj.longestValidParenthesesWithoutExtraSpace(s1));
        System.out.println(longestValidParenthesesUsingStack(s1));
    }

    //Approach 3: Using stack
    /*
    Time: O(n)
    Space: O(n)
     */
    static int longestValidParenthesesUsingStack(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    //Approach 4: Without extra space
    /*
    Time: O(n) : Two traversal of the string
    Space: O(1), only 2 pointers needed (left and right)
     */
    public int longestValidParenthesesWithoutExtraSpace(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++; //Open parentheses
            } else {
                right++; //Closed parentheses
            }
            if (left == right) { //equal opened and closed parentheses
                maxLength = Math.max(maxLength, 2 * right); //Multiply by 2 because we count left
            } else if (right >= left) {// Reset, it is invalid
                left = right = 0;
            }
        }
        left = right = 0; //Reset
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++; //Open parentheses
            } else {
                right++; //Closed parentheses
            }
            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxLength;
    }

    //Approach 1: Brut force
    /*
    Time: O(n3) Generating every possible substring O(n²) and checking validity O(n)
    Space: O(n) use by the stack
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s.substring(i, j))) {
                    maxLen = Math.max(maxLen, j - i);
                }
            }
        }
        return maxLen;
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }


}
