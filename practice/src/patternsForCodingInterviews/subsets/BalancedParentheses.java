package patternsForCodingInterviews.subsets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744074098_71Unit
public class BalancedParentheses {

    public static void main(String[] args) {
        List<String> result = generateValidParentheses(2);
        System.out.println("All combinations of balanced parentheses are: " + result);

        result = generateValidParentheses(3);
        System.out.println("All combinations of balanced parentheses are: " + result);
    }

    /*
    Recursive
     */
    public static List<String> generateValidParenthesesRecursive(int num) {
        List<String> result = new ArrayList<String>();
        char[] parenthesesString = new char[2 * num];
        generateValidParenthesesRecursive(num, 0, 0, parenthesesString, 0, result);
        return result;
    }

    private static void generateValidParenthesesRecursive(int num, int openCount,
                                                          int closeCount, char[] parenthesesString, int index, List<String> result) {
        // if we've reached the maximum number of open and close parentheses, add to result
        if (openCount == num && closeCount == num) {
            result.add(new String(parenthesesString));
        } else {
            if (openCount < num) { // if we can add an open parentheses, add it
                parenthesesString[index] = '(';
                generateValidParenthesesRecursive(num, openCount + 1, closeCount,
                        parenthesesString, index + 1, result);
            }
            if (openCount > closeCount) { // if we can add a close parentheses, add it
                parenthesesString[index] = ')';
                generateValidParenthesesRecursive(num, openCount, closeCount + 1,
                        parenthesesString, index + 1, result);
            }
        }
    }

    /*
    Iterative
    Time: O(N * 2N)
    Space: O(N * 2N)
     */
    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<>();
        Queue<ParenthesesString> queue = new LinkedList<>();
        queue.add(new ParenthesesString(new StringBuilder(), 0, 0));
        while (!queue.isEmpty()) {
            ParenthesesString ps = queue.poll();
            // if we've reached the maximum number of open and close parentheses, add to result
            if (ps.openCount == num && ps.closeCount == num) {
                result.add(ps.str.toString());
            } else {
                StringBuilder strBuilder;
                ParenthesesString parenthesesString;
                if (ps.openCount < num) { // if we can add an open parentheses, add it
                    strBuilder = new StringBuilder(ps.str).append("(");
                    parenthesesString = new ParenthesesString(strBuilder, ps.openCount + 1, ps.closeCount);
                    queue.add(parenthesesString);
                }
                if (ps.openCount > ps.closeCount) { // if we can add a close parentheses, add it
                    strBuilder = new StringBuilder(ps.str).append(")");
                    parenthesesString = new ParenthesesString(strBuilder, ps.openCount, ps.closeCount + 1);
                    queue.add(parenthesesString);
                }
            }
        }
        return result;
    }

    static class ParenthesesString {
        StringBuilder str;
        int openCount; //Open parentheses count
        int closeCount; //Close parentheses count

        ParenthesesString(StringBuilder s, int openCount, int closeCount) {
            this.str = s;
            this.openCount = openCount;
            this.closeCount = closeCount;
        }
    }

}
