package topInterviewQuestion.amazon.arraysAndStrings;

import java.util.Arrays;
import java.util.Comparator;

//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2974/
public class ReorderLogFiles {

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        var obj = new ReorderLogFiles();
        for(String s : obj.reorderLogFiles(logs)){
            System.out.print(s + ", ");
        }
    }

    /*
    Time: O(M . N . log N)
    Arrays.sort -> O(N . Log N)
    Each invocation, of the compare() function, it could take up to O(M) time
    Space: O(M . log N)
    For each invocation of the compare() function, takes O(M) space to hold the parsed logs.
    Arrays.sort() is based on quicksort algorithm, whose space complexity is O(log N)
     */
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String log1, String log2) {
                //Split each log into two parts <identifier and contents>
                String[] split1 = log1.split(" ", 2);
                String[] split2 = log2.split(" ", 2);
                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
                //Case 1: both logs are letter-logs
                if(!isDigit1 && !isDigit2) {
                    //First compare the content
                    int cmp = split1[1].compareTo(split2[1]);
                    if(cmp != 0) { //Content are not the same
                        return cmp;
                    }
                    //Logs of same content, compare the identifiers
                    return split1[0].compareTo(split2[0]);
                }
                //Case 2: one of logs is digit
                if(!isDigit1 && isDigit2) {
                    //letter-log comes before digit-logs
                    return -1;
                } else if(isDigit1 && !isDigit2) {
                    return 1;
                }
                //Case 3: both logs are digits
                else
                {
                    //There are both digits
                    return 0; //Maintain their relative ordering
                }
            }
        };
        Arrays.sort(logs, myComp);
        return logs;
    }



}
