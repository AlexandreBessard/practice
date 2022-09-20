package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/group-shifted-strings/
public class GroupShiftedStrings {

    public static void main(String[] args) {
        String[] strings = {"abc","bcd","acef","xyz","az","ba","a","z"};
        for(List<String> el : groupStrings(strings)) {
            System.out.println(el);
        }
    }

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
        for(int i = 1; i < chars.length; i++) { //If one letter, we do not create a hash value, key will be 'empty'
            hashKey.append((char) (chars[i] - chars[i - 1] + 26) % 26 + 'a');
        }
        System.out.println("Hash value : " + hashKey);
        return hashKey.toString();
    }

}
