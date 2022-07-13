package topInterviewQuestion.easy.strings;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        String s = "loveleetcode";
        //First Unique character is index 2 : 'v'
        System.out.println(firstUniqueChar(s));
    }

    /*
    Time complexity: O(N), since we go through the string of length N two times.
    Space complexity: O(1), Because English alphabet contains 26 letters (static num)
     */
    static int firstUniqueChar(String s) {
        Map<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        //find the index
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(count.get(c) == 1)
                return i;
        }
        return -1;
    }

}
