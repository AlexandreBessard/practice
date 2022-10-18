package topInterviewQuestion.top100likedQuestions;

import java.util.LinkedList;
import java.util.Stack;

//https://leetcode.com/problems/decode-string/
public class DecodeString {

    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]"; // -> a cc | a cc | a cc   (1 a and 2 c letters repeatedly 3 times)
        System.out.println(decodeString(s2));
        System.out.println("2 stacks -> " + decodeString2Stacks(s2));
    }

    //Approach 2: Using 2 stacks
    /*
    Time: O(maxK . n) where maxK is the max value of k and n is the length of a given string s
     */
    static String decodeString2Stacks(String s) {
        //Use 2 stacks, 1 will contain numbers and other will contain letters only
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        var currentString = new StringBuilder();
        int k = 0;
        for(char ch : s.toCharArray()) {
            if(Character.isDigit(ch)) { //If currChar is a number
                k = (k * 10) + ch - '0';
            }
            else if(ch == '[') {
                //Push the number k  and currentString to the stack
                countStack.push(k);
                stringStack.push(currentString); //Can add a empty string -> ""
                //Reset
                currentString = new StringBuilder();
                k = 0;
            }
            else if(ch == ']') {
                var decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for(int currK = countStack.pop(); currK > 0; currK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else { //It is a letter
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }

    //Approach 1: Using Stack
    /*
    Time: O(maxKcountk . N) where maxK is the max value of k, countK is the count of nested k anf n is max length of encoded string.
    Space: O(sum(maxKcountk . n)
     */
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
        LinkedList<Character> decodedString = new LinkedList<>();
        // get the encoded string
        while(stack.peek() != '[') {
            decodedString.addFirst(stack.pop());
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
            for (Character character : decodedString) {
                stack.push(character);
            }
            k--;
        }
    }

}
