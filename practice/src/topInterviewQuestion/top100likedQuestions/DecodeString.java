package topInterviewQuestion.top100likedQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/decode-string/
public class DecodeString {

    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]"; // -> a cc | a cc | a cc   (1 a and 2 c letters repeatedly 3 times)
        System.out.println(decodeString(s2));
    }

    //Approach 1: Using Stack
    static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) { //Loop through each letters from the string
            if(s.charAt(i) == ']') {
                //Decode
                decode(stack);
            } else { // push the current character to stack
                stack.push(s.charAt(i));
            }
        }
            //get the result from stack
            char[] result = new char[stack.size()];
            for(int i = result.length - 1; i >= 0; i--) {
                result[i] = stack.pop();
            }
        return new String(result);
    }
    private static void decode(Stack<Character> stack) {
        List<Character> decodedString = new ArrayList<>();
        // get the encoded string
        while(stack.peek() != '[') {
            decodedString.add(stack.pop());
        }
        //Pop [ from the stack
        stack.pop(); // [ removed
        int base = 1;
        int k = 0;
        //Get the number k
        while(!stack.isEmpty() && Character.isDigit(stack.peek())) { //Make sure stack is not empty and it is a number
            k = k + (stack.pop() - '0') * base; //Case if we have 23 for example
            base *= 10;
        }
        //decode k[decodedString]
        while(k != 0) {
            for(int j = decodedString.size() - 1; j >= 0; j--) {
                stack.push(decodedString.get(j));
            }
            k--;
        }
    }

}
