package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/794/
public class GenerateParentheses {

    public static void main(String[] args) {
        var obj = new GenerateParentheses();
        //List<String> res = obj.generateParenthesis(3);
        /*
        for(String r : res) {
            System.out.print(r + ", ");
        }

         */
        List<String> res1 = obj.generateParenthesisBackTracking(3);
        System.out.println();
        for (String r : res1) {
            System.out.print(r + ", ");
        }
        System.out.println("\n"+generateParenthesis(3));
    }

    //Approach 2: Backtracking
    public List<String> generateParenthesisBackTracking(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, StringBuilder cur,
                           int open,
                           int close,
                           int max) {
        // # If the solution is found
        if (cur.length() == max * 2) { //Base case
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            //# Try this partial candidate solution
            cur.append("(");
            //# Move on to the next candidate
            backtrack(ans, cur, open + 1, close, max);
            // Backtrack
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            // # Try this partial solution
            cur.append(")");
            //# Move on to the next candidate
            backtrack(ans, cur, open, close + 1, max);
            //# Backtrack
            cur.deleteCharAt(cur.length() - 1);
        }
    }


    //Approach 1: Brut force
    /*
    Time complexity: O(22nn)
    Space complexity: O(22nn)

    You can visualize the brut force as a binary tree ex:
    n = 3
        C
       / \
      c   ↄ
     /\  /\
    c  ↄ c ↄ
     */
    static List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    private static void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) { //Base case
            if (valid(current)) //Add to the result if it is valid
                result.add(new String(current));
        } else {
            current[pos] = '('; //See binary example above (left side)
            generateAll(current, pos + 1, result);
            current[pos] = ')'; //Right side
            generateAll(current, pos + 1, result);
        }
    }

    private static boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(')
                balance++;
            else
                balance--;
            if (balance < 0)
                return false;
        }
        return (balance == 0);
    }

}
