package topInterviewQuestion.facebook.topMostFrequentQuestions;

//https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
public class MinimumAddToMakeParenthesesValid {

    public static void main(String[] args) {
        String s = "())";
        String s1 = "(((";
        String s2 = ")))";
        System.out.println(minAddToMakeValid(s1));
    }

    //Approach 1: Balance
    /*
    String is balance (same amount of opened and closed parentheses) if 'balance' == 0
    Time: O(N)
    Space: O(1)
     */
    static int minAddToMakeValid(String s) {
        int res = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            balance += s.charAt(i) == '(' ? 1 : -1;
            // It is guaranteed bal >= -1
            if (balance == -1) { //If true means more closed brackets than open (unbalanced)
                res++;
                balance++;
            }
        }
        return res + balance;
    }


}
