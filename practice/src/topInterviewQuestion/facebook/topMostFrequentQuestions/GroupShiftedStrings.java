package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/group-shifted-strings/
public class GroupShiftedStrings {

    public static void main(String[] args) {
        /*
        shifting 'each of' its letters to its successive letter: NOTICE the word EACH OF
        so it should be:
        az->ba ('a'->'b' and 'z'->'a')
         */
        String[] strings = {"abc","bcd","acef","xyz","az","ba","a","z"};
        String[] strings1 = {"abc","bcd","cde","def","efg"};
        String[] strings2 = {"ba","cb","dc","zy"};
        for(List<String> el : groupStrings(strings2)) {
            System.out.println(el);
        }
    }

    /*
    Hashing
    Time: O(N * K) -> N length of the strings and K max length of a string in strings
    Space: O(N * K)
     */
    static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> mapHashToList = new HashMap<>();
        // Create a hash_value (hashKey) for each string and append the string
        // to the list of hash values i.e. mapHashToList["cd"] = ["acf", "gil", "xzc"]
        for(String str : strings) {
            String hashKey = getHash(str);
            if(!mapHashToList.containsKey(hashKey)) {
                mapHashToList.put(hashKey, new ArrayList<>());
            }
            mapHashToList.get(hashKey).add(str);
        }
        // Iterate over the map, and add the values to groups
        return new ArrayList<>(mapHashToList.values());
    }
    //"abc","bcd","acef","xyz","az","ba","a","z"
    private static String getHash(String s) {
        char[] chars = s.toCharArray();
        var hashKey = new StringBuilder();
        /*
        Find its Hash value, that is, the string starts with an
        a after some shifts. The value of shift is equal to the first character of the string.
        Then shift all the characters by the same value shift.
        Notice that we also have to do a mod of 26 on the resulting character for the circular shift.
         */
        for(int i = 1; i < chars.length; i++) { //If one letter, we do not create a hash value, key will be 'empty'
            //example : az->ba ('a'->'b' and 'z'->'a')
            hashKey.append((char) (chars[i] - chars[i - 1] + 26) % 26 + 'a');
        }
        System.out.println("Hash value : " + hashKey);
        return hashKey.toString();
    }

}
