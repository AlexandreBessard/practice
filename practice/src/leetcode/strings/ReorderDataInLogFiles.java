package leetcode.strings;

import java.util.Arrays;

//https://leetcode.com/problems/reorder-data-in-log-files/
public class ReorderDataInLogFiles {
    /*
    You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.

    There are two types of logs:

    Letter-logs: All words (except the identifier) consist of lowercase English letters.
    Digit-logs: All words (except the identifier) consist of digits.
    Reorder these logs so that:

    The letter-logs come before all digit-logs.
    The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.
    The digit-logs maintain their relative ordering.
    Return the final order of the logs.

    Example 1:
    Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
    Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
    Explanation:
    The letter-log contents are all different, so their ordering is "art can", "art zero", "own kit dig".
    The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
     */
    public static void main(String[] args) {
        String[] logs1 =
                {"let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"};
        for (String s : reorderLogFiles(logs1)) {
            System.out.println(s);
        }
    }

    /*
    Time: O(M . N . log N), Arrays.sort() -> O(N . log N) + compareTo() -> O(M) compare the contents of the logs
     Space: O(M . log N), O(m) compareTo() need more space and Arrays.sort()  based on quicksort algorithm and take O(M) space
     */
    public static String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            String[] split1 = s1.split(" ", 2); // [let1, art can]
            String[] split2 = s2.split(" ", 2);
            //Check if first element from second index is a num
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) { //Both have letter
                int comp = split1[1].compareTo(split2[1]);
                //If both same letter, compare with the first index
                return (comp == 0) ? split1[0].compareTo(split2[0]) : comp;
            } else if (isDigit1 && isDigit2) { //Both are digits
                //return to the original order
                return 0;
            } else if (isDigit1 && !isDigit2) { //first is digit, second letter
                //bring letter forward (priority based on digit)
                return 1; //digit comes AFTER letter
            } else { //First is letter, second digit
                //Letter comes FIRST
                return -1;
            }
        });
        return logs;
    }
}
