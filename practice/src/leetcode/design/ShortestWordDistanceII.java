package leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/shortest-word-distance-ii/
public class ShortestWordDistanceII {
    /*
    Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.

    Implement the WordDistance class:

    WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
    int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.

    Example 1:
    Input
    ["WordDistance", "shortest", "shortest"]
    [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
    Output
    [null, 3, 1]
     */
    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance wordDistance = new WordDistance(words);
        System.out.println(wordDistance.shortest("coding", "practice"));
        System.out.println(wordDistance.shortest("makes", "coding"));
    }

    //Correction: https://leetcode.com/problems/shortest-word-distance-ii/discuss/67028/Java-Solution-using-HashMap
    static class WordDistance {

        //Key: word, Value: List of indexes where this word is located from the array
        private Map<String, List<Integer>> map;
        //Time: O(N)
        //Space: O(N)
        WordDistance(String[] words) {
            map = new HashMap<>();
            for (int currIndex = 0; currIndex < words.length; currIndex++) {
                String currWord = words[currIndex];
                if (map.containsKey(currWord)) {
                    map.get(currWord).add(currIndex);
                } else {
                    map.put(currWord, new ArrayList<>(List.of(currIndex)));
                }
            }
        }

        public int shortest(String s1, String s2) {
            List<Integer> list1 = map.get(s1); //List where this word appears (indexes)
            List<Integer> list2 = map.get(s2);
            int result = Integer.MAX_VALUE;
            for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
                int index1 = list1.get(i);
                int index2 = list2.get(j);
                if (index1 < index2) {
                    int range = index2 - index1;
                    result = Math.min(result, range);
                    i++;
                } else {
                    int range = index1 - index2;
                    result = Math.min(result, range);
                    j++;
                }
            }
            return result;
        }
    }


}
