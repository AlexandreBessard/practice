package patternsForCodingInterviews.topKElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744359516_103Unit
public class RearrangeString {

    public static void main(String[] args) {
        System.out.println("Rearranged string: " +
                rearrangeString("aappp"));
        System.out.println("Rearranged string: " +
                rearrangeString("Programming"));
        System.out.println("Rearranged string: " +
                rearrangeString("aapa"));
    }

    /*
    Time: O(N * logN)
    Space: O(N)
     */
    public static String rearrangeString(String str) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> e2.getValue() - e1.getValue()
        );
        // add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entrySet());
        Map.Entry<Character, Integer> previousEntry = null;
        StringBuilder resultString = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // add the previous entry back in the heap if its frequency is greater than zero
            if (previousEntry != null && previousEntry.getValue() > 0) {
                maxHeap.offer(previousEntry);
            }
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            previousEntry = currentEntry;
        }
        // if we were successful in appending all the characters to the result string,
        // return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }

}
