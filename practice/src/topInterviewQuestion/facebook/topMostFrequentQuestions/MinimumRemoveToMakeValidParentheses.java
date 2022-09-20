package topInterviewQuestion.facebook.topMostFrequentQuestions;

//https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
public class MinimumRemoveToMakeValidParentheses {

    public static void main(String[] args) {
        String s = "lee(t(c)o)de)";
        //Output: "lee(t(c)o)de"
        System.out.println(minRemoveToMakeValid(s));
        String s1 = "a)b(c)d";
        //Output: "ab(c)d"
        //System.out.println(minRemoveToMakeValid(s1));
        String s2 = "a)((b(c)d";
        System.out.println(minRemoveToMakeValidShortenedTwoPass(s2));
    }

    //Approach 3: Shortened Two Pass StringBuilder
    static String minRemoveToMakeValidShortenedTwoPass(String s) {
        // Pass 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            }
            if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }
        // Pass 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance; //Number of open '(' to keep for our result
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                if (openToKeep < 0) continue; //We have enough  open bracket '(' we continue
            }
            result.append(c);
        }

        return result.toString();
    }

    //Approach 2: Two Pass String Builder
    static String minRemoveToMakeValid(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        System.out.println("result.reverse() -> " + result.reverse());
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }

    /*
    String is a ChaSequence
    A CharSequence is a readable sequence of char values.
    This interface provides uniform, read-only access to many different kinds of char sequences
     */
    private static StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == open) {
                balance++;
            }
            if (c == close) {
                if (balance == 0)
                    continue;

                balance--;
            }
            sb.append(c);
        }
        return sb;
    }

}
