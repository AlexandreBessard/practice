package topInterviewQuestion.medium.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/114/others/823/
public class EvaluateReversePolishNotation {


    public static void main(String[] args) {
        //"2","1","+","3","*"
        //((2 + 1) * 3) = 9  -> explanation: get element before operator:
        // before '+' we have 1 and 2 -> it is 2 + 1 and so on...
        String[] tokens = {"2","1","+","3","*"};
        //Output 9
        System.out.println(evalRPN(tokens));
        tokens = new String[] {"2","1","+","3","*"};
        System.out.println(evalRPNWithStack(tokens));

    }

    private static final Map<String, BiFunction<Integer, Integer, Integer>>
            OPERATIONS = new HashMap<>() {
        {
            put("+", (a, b) -> a + b);
            put("-", (a, b) -> a - b);
            put("*", (a, b) -> a * b);
            put("/", (a, b) -> a / b);
        }
    };

    //Approach 2: Evaluate with Stack
    /*
    Time complexity: O(n) caused by length of the array
    Space complexity: O(n) caused by the stack
     */
    static int evalRPNWithStack(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String token : tokens) {
            if(!OPERATIONS.containsKey(token)){
                stack.push(Integer.valueOf(token));
                continue;
            }
            int number2 = stack.pop();
            int number1 = stack.pop();
            BiFunction<Integer, Integer, Integer> operation;
            operation = OPERATIONS.get(token);
            int result = operation.apply(number1, number2);
            stack.push(result);
        }
        return stack.pop();
    }

    //Approach 1: Reducing the List In-place
    /*
    Time complexity: O(n²)
    n length of tokens, and loop through delete2AtIndex
    Space complexity: 0(1)
     */
    static int evalRPN(String[] tokens) {
        int currentPosition = 0;
        int length = tokens.length;
        while(length > 1) {
            //Move position pointer to the next operator token
            while (!OPERATIONS.containsKey(tokens[currentPosition])) {
                currentPosition++;
            }
            //Extract the operation and number to apply operation too
            String operation = tokens[currentPosition];
            int number1 = Integer.parseInt(tokens[currentPosition - 2]);
            int number2 = Integer.parseInt(tokens[currentPosition - 1]);
            //Calculate the result
            BiFunction<Integer, Integer, Integer> operator = OPERATIONS.get(operation);
            int value = operator.apply(number1, number2);
            tokens[currentPosition] = Integer.toString(value);
            //Delete numbers and pointer correctly
            delete2AtIndex(tokens, currentPosition - 2, length);
            currentPosition--;
            length -= 2;
        }
        return Integer.parseInt(tokens[0]);
    }
    private static void delete2AtIndex(String[] tokens, int d, int length) {
        for(int i = d; i < length - 2; i++) {
            tokens[i] = tokens[i + 2];
        }
    }


}
