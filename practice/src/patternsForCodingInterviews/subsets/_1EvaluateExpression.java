package patternsForCodingInterviews.subsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744093989_73Unit
public class _1EvaluateExpression {

    public static void main(String[] args) {
        List<Integer> result;
        result = diffWaysToEvaluateExpression("1+2*3");
        System.out.println("Expression evaluations: " + result);
        /*
        result = diffWaysToEvaluateExpression("2*3-4-5");
        System.out.println("Expression evaluations: " + result);

         */
    }

    //memoization
    static Map<String, List<Integer>> map = new HashMap<>();

    /*
    Time: O(N * 2N)
    Space: O(2N)
     */
    public static List<Integer> diffWaysToEvaluateExpression(String input) {
        //Part of memoization
        if (map.containsKey(input)) {
            return map.get(input);
        }
         List<Integer> result = new ArrayList<>();
        //base case: if the input string is a number, parse and add it to the output.
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            result.add(Integer.parseInt(input));
            return result;
        } else {
            for (int i = 0; i < input.length(); i++) {
                char chr = input.charAt(i);
                if (!Character.isDigit(chr)) {
                    //Break  the equation here into two parts and make recursively calls
                    List<Integer> leftParts =
                            diffWaysToEvaluateExpression(input.substring(0, i));
                    List<Integer> rightParts =
                            diffWaysToEvaluateExpression(input.substring(i + 1));
                    for (int part1 : leftParts) {
                        for (int part2 : rightParts) {
                            if (chr == '+') {
                                result.add(part1 + part2);
                            } else if (chr == '-') {
                                result.add(part1 - part2);
                            } else if (chr == '*') {
                                result.add(part1 * part2);
                            }
                        }
                    }
                }
            }
        }
        //Part of memoization
        map.put(input, result);
        return result;
    }

}
