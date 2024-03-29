package topInterviewQuestion.facebook.recursion;

import java.util.*;

//https://leetcode.com/explore/interview/card/facebook/53/recursion-3/324/
public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        String failCase = ")())()";
        String s1 = "()())()";
        String s2 = "(a)())()";
        var obj = new RemoveInvalidParentheses();
        List<String> res;
        //res = obj.removeInvalidParentheses(s1);
        res = obj.removeInvalidParenthesesBFS(failCase);
        System.out.println(res);
        //res = obj.removeInvalidParentheses(s2);
        //System.out.println(res);
    }

    //BFS -> can guarantee the number of parentheses that need to be removed is minimal,we have no recursion
    //https://leetcode.com/explore/interview/card/facebook/53/recursion-3/324/discuss/75032/Share-my-Java-BFS-solution
    /*
    The idea is straightforward, with the input string s, we generate all possible states by removing one
    ( or ), check if they are valid, if found valid ones on the current level,
     put them to the final result list and we are done, otherwise, add them to a queue and carry on to the next level.

    Check if String is valid -> O(n)
    Remove ( or ) from the first level so there are C(n, n - 1)
    Each new  string -> check if valid (n-1)  -> total (n-1) * C(n, n - 1)
    Third level -> (n-2) x C(n, n-2) so on and so forth...
    We have this formula
    T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
    */
    public List<String> removeInvalidParenthesesBFS(String s) {
        List<String> res = new ArrayList<>();
        // sanity check
        if (s == null) return res;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        // initialize
        queue.add(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            s = queue.poll(); //each level from the queue, the input is smaller and smaller (remove 1 character when we loop)
            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }
            //This ensure once we have found a valid parentheses pattern,
            // we do not do any further bfs using items pending in the queue since any
            // further bfs would only yield strings of smaller length.
            //However the items already in queue need to be processed
            // since there could be other solutions of the same length.
            if (found) //We can have other valid results from the queue
                continue;
            StringBuilder stringBuilder;
            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')') //In case of a letter for example
                    continue;
                //Skip one parenthese (the middle) between left -> i and i + 1 -> right
                stringBuilder = new StringBuilder(s.substring(0, i)); //Get the left side of the input string
                stringBuilder.append(s.substring(i + 1)); //Get the right side of the input string
                final String t = stringBuilder.toString();
                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }
        return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }
        return count == 0;
    }

    //DFS
    //https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
    public List<String> removeInvalidParentheses(String s) {
        List<String> output = new ArrayList<>();
        removeHelper(s, output, 0, 0, '(', ')');
        return output;
    }

    public void removeHelper(String s, List<String> output, int iStart, int jStart, char openParen, char closedParen) {
        int numOpenParen = 0, numClosedParen = 0;
        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == openParen) numOpenParen++;
            if (s.charAt(i) == closedParen) numClosedParen++;
            if (numClosedParen > numOpenParen) { // We have an extra closed paren we need to remove
                for (int j = jStart; j <= i; j++) // Try removing one at each position, skipping duplicates
                    if (s.charAt(j) == closedParen && (j == jStart || s.charAt(j - 1) != closedParen))
                        // Recursion: iStart = i since we now have valid # closed parenthesis thru i. jStart = j prevents duplicates
                        removeHelper(s.substring(0, j) + s.substring(j + 1, s.length()), output, i, j, openParen, closedParen);
                return; // Stop here. The recursive calls handle the rest of the string.
            }
        }
        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        String reversed = new StringBuilder(s).reverse().toString();
        if (openParen == '(')
            removeHelper(reversed, output, 0, 0, ')', '(');
        else
            output.add(reversed);
    }
}
