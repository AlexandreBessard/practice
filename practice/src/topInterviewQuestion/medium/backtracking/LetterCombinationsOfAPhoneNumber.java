package topInterviewQuestion.medium.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/793/
public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        List<String> res = letterCombinations("23");
        for(String r : res) {
            System.out.print(r + ", ");
        }
    }

    //Approach 1: Backtracking
    /*
    Time complexity: O(4n . N) N is the length of digits.
    4 is referring to the max value length in the hashmap (7 and 9 having 4 letters)

    Space complexity: O(N) where N is the length of digits.
     */
    static List<String> combinations = new ArrayList<>();
    static Map<Character, String> letters = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
    );
    static String phoneDigits;
    static List<String> letterCombinations(String digits) {
        if(digits.length() == 0)
            return combinations;
        //Initiate backtracking with an empty path and starting index of 0
        phoneDigits = digits;
        backtracking(0, new StringBuilder());
        return combinations;
    }
    private static void backtracking(int index, StringBuilder path) {
        if(path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return; //Backtrack
        }
        //Get the letters that the current digit maps to, and loop through them
        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for(char letter: possibleLetters.toCharArray()) {
            //Add the letter to our current path
            path.append(letter);
            //Move on to the next digit
            backtracking(index + 1, path);
            //Backtrack by removing the letter before moving onto the next
            path.deleteCharAt(path.length() - 1);
        }
    }

}
