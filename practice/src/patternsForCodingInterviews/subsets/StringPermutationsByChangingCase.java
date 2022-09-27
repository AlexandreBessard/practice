package patternsForCodingInterviews.subsets;

import java.util.ArrayList;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744066603_70Unit
public class StringPermutationsByChangingCase {

    public static void main(String[] args) {
        List<String> result =
                findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);
/*
        result = findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);

 */
    }

    /*
    Time: O(N * 2N)
    Space: O(N * 2N)
     */
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if(str == null) {
            return permutations;
        }
        permutations.add(str);
        //process evert characters if the string one by one
        for(int i = 0; i < str.length(); i++) {
            if(Character.isLetter(str.charAt(i))) { //Process only characters, skip digit
                //we will take all existing permutations and change the letter case appropriately
                int n = permutations.size();
                for(int j = 0; j < n; j++) {
                    char[] chs = permutations.get(j).toCharArray(); //new instance for chs
                    //If the current char is in upper case, change it to lower case and vice versa
                    if(Character.isUpperCase(chs[i])) { //Be careful, we work on index 'i'
                        chs[i] = Character.toLowerCase(chs[i]);
                    } else {
                        chs[i] = Character.toUpperCase(chs[i]);
                    }
                    permutations.add(String.valueOf(chs));
                }
            }
        }
        return permutations;
    }

}
