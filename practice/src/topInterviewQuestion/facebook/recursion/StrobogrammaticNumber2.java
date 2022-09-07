package topInterviewQuestion.facebook.recursion;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/interview/card/facebook/53/recursion-3/3029/
public class StrobogrammaticNumber2 {

    public static void main(String[] args) {
        var obj = new StrobogrammaticNumber2();
        List<String> res = obj.findStrobogrammatic(4);
        System.out.println(res);
    }

    public char[][] reversiblePairs = {
            {'0', '0'},
            {'1', '1'},
            {'6', '9'},
            {'8', '8'},
            {'9', '6'}
    };
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
}
