package topInterviewQuestion.facebook.others;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/interview/card/facebook/57/others-3/3039/
public class ExpressionAddOperators {

    public static void main(String[] args) {
        String num = "232";
        String num2 = "0.009";
        int target = 8;
        var obj = new ExpressionAddOperators();
        System.out.println(obj.addOperators(num2, target));
    }

    //https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
    //BackTracking
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, num.toCharArray(), 0, target, 0, 0);
        return res;
    }
    /*
     * When we use dfs to do this question, the most tricky part is that how to deal with multiplication. For every
     * addition and subtraction, we just directly adding or subtracting the new number. However, for multiplication,
     * we should multiply current number and previous number firstly, and then add previous previous number.
     * So we can use a variable 'prev' to record every previous number in each recursion step. If current recursive
     * call is trying multiplication, we should use previous calculation value subtract previous number, and then
     * adding multiplication result between previous number and current number.
     * */
    public void dfs(List<String> res, StringBuilder sb, char[] num, int pos, int target, long prev, long multi) {
        if (pos == num.length) {
            if (target == prev) res.add(sb.toString());
            return;
        }
        long curr = 0;
        for (int i = pos; i < num.length; i++) {
            // corner case: if current position is 0, we can only use it as a single digit number, should be 0
            // if it is not a single digit number with leading 0, it should be considered as an invalid number
            if (num[pos] == '0' && i != pos)
                break;
            curr = 10 * curr + num[i] - '0';
            int len = sb.length();
            if (pos == 0) { //position 0 should be considered individually, since it does not have any operand character
                dfs(res, sb.append(curr), num, i + 1, target, curr, curr);
                sb.setLength(len);
            } else {
                dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr);
                sb.setLength(len);
                dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr);
                sb.setLength(len);
                // trick part: to calculate multiplication, we should subtract previous number, and then add current
                // multiplication result to the subtraction result
                dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr);
                sb.setLength(len);
            }
        }
    }
}
