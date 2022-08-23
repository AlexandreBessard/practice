package patternsForCodingInterviews.topKElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744311147_98Unit
public class FrequencySort {

    public static void main(String[] args) {
        String result = FrequencySort.sortCharacterByFrequency("Programming");
        System.out.println("Here is the given string after sorting characters by frequency: "
                + result);

        result = FrequencySort.sortCharacterByFrequency("abcbab");
        System.out.println("Here is the given string after sorting characters by frequency: "
                + result);
    }

    /*
    Time: O(D * logD), D is the distinct characters
    worst case when all characters are unique -> O(N * logN) where N total number of characters
    Space: O(N)
     */
    public static String sortCharacterByFrequency(String str) {
        // find the frequency of each character
        Map<Character, Integer> characterFrequencyMap = new HashMap<>();
        for(char chr : str.toCharArray()) {
            characterFrequencyMap.put(chr, characterFrequencyMap.getOrDefault(chr, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> e2.getValue() - e1.getValue()
        );
        // add all characters to the max heap
        maxHeap.addAll(characterFrequencyMap.entrySet());
        // build a string, appending the most occurring characters first
        StringBuilder sortedString = new StringBuilder(str.length());
        while( ! maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            //Loop because we can have multiple frequency for this entry, we want to keep all the letters in the output
            for(int i = 0; i < entry.getValue(); i++) {
                sortedString.append(entry.getKey());
            }
        }
        return sortedString.toString();
    }


}
