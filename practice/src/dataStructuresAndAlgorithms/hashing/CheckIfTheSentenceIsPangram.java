package dataStructuresAndAlgorithms.hashing;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/check-if-the-sentence-is-pangram/
public class CheckIfTheSentenceIsPangram {

    public static void main(String[] args) {
        String sentence = "thequickbrownfoxjumpsoverthelazydog";
        System.out.println(checkIfPangram(sentence));
    }

    static boolean checkIfPangram(String sentence) {
        Set<Character> seen = new HashSet<>();
        for (char currChar : sentence.toCharArray()) {
            seen.add(currChar);
        }
        //If size of 'seen' is 26, then sentence is a pangram
        return seen.size() == 26;
    }

}
