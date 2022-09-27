package topInterviewQuestion.facebook.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/facebook/53/recursion-3/3029/
public class StrobogrammaticNumber2 {

    public static void main(String[] args) {
        var obj = new StrobogrammaticNumber2();
        List<String> res = obj.findStrobogrammatic(3);
        System.out.println(res);
        List<String> res1 = obj.findStrobogrammaticIterative(3);
        System.out.println(res1);
    }

    public static char[][] reversiblePairs = {
            {'0', '0'},
            {'1', '1'},
            {'6', '9'},
            {'8', '8'},
            {'9', '6'}
    };
    //Recursive
    public List<String> findStrobogrammatic(int n) {
        return generateStroboNumbers(n, n);
    }
    private List<String> generateStroboNumbers(int n, int finalLength) {
        if(n == 0) { //O digit is an empty String
            return new ArrayList<>(List.of(""));
        }
        if(n == 1) {
            //1 digit strobogrammatic numbers.
            return new ArrayList<>(List.of("0", "1", "8"));
        }
        //Recursive call
        List<String> prevStroboNums = generateStroboNumbers(n - 2,
                finalLength);
        List<String> currStroboNums = new ArrayList<>();
        for(String prevStroboNum : prevStroboNums) {
            for(char[] pair : reversiblePairs) {
                // We can only append 0's if it is not first digit.
                if(pair[0] != '0' || n != finalLength) { //n != finalLength -> when we cant 00 as a separator
                    currStroboNums.add(pair[0] + prevStroboNum + pair[1]);
                }
            }
        }
        return currStroboNums;
    }

    //Approach 2: Iterative
    /*

     */
    public List<String> findStrobogrammaticIterative(int n) {
        Queue<String> q = new LinkedList<>();
        int currStringLength;
        // When n is even, it means when decreasing by 2 we will go till 0.
        if(n % 2 == 0) {
            // We will start with 0-digit strobogrammatic numbers.
            currStringLength = 0;
            q.add("");
        } else { // it is not an even number
            currStringLength = 1;
            q.add("0");
            q.add("1");
            q.add("8");
        }
        while(currStringLength < n) {
            currStringLength += 2;
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String number = q.poll();
                StringBuilder strBuilder;
                for(char[] pair : reversiblePairs) {
                    if(currStringLength != n || pair[0] != '0') {
                        strBuilder = new StringBuilder();
                        strBuilder.append(pair[0]).append(number).append(pair[1]);
                        q.add(strBuilder.toString());
                    }
                }
            }
        }
        List<String> stroboNums = new ArrayList<>(q.size());
        while (!q.isEmpty()) {        //Equivalent to : stroboNums.addAll(q);
            stroboNums.add(q.poll());
        }
        return stroboNums;
    }
}
