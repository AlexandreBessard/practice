package leetcode.topinterview150.hashmap.easy;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    // https://leetcode.com/problems/word-pattern/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        // Input: pattern = "abba", s = "dog cat cat dog" => a -> dog, cat -> b
        String pattern = "abba";
        String s = "dog cat cat dog";
        System.out.println(wordPattern(pattern, s));
        pattern = "abba";
        s = "dog cat cat fish";
        System.out.println(wordPattern(pattern, s));
    }

    /*
    HashMap
    Time: O(n)
    Space: O(m)
     */
    static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Character, String> charToWord = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            char currentChar = pattern.charAt(i);
            String currentWord = words[i];

            if (charToWord.containsKey(currentChar)) {
                if (!charToWord.get(currentChar).equals(currentWord)) {
                    return false;
                }
            } else { // the letter is not associated to any word
                // true means the word has already been assigned to a letter
                if (charToWord.containsValue(currentWord)) {
                    return false;
                }
                charToWord.put(currentChar, currentWord); // a -> dog, b -> cat
            }
        }
        return true;
    }
}
