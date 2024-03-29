package patternsForCodingInterviews.topKElements;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744369697_104Unit
public class _1RearrangeStringKDistanceApart {

    public static void main(String[] args) {
        System.out.println("Reorganized string: " +
                reorganizeString("mmpp", 2));
        System.out.println("Reorganized string: " +
                reorganizeString("Programming", 3));
        System.out.println("Reorganized string: " +
                reorganizeString("aab", 2));
        System.out.println("Reorganized string: " +
                reorganizeString("aappa", 3));
    }

    /*
    Time: O(N * logN) where N is the number of characters in the input
    Space: O(N) in worst case, we need to store all the N characters in the HashMap
     */
    public static String reorganizeString(String str, int k) {
        if (k <= 1) {
            return str;
        }
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> e2.getValue() - e1.getValue()
        );
        //Add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entrySet());
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        StringBuilder resultString = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            //can keep track of previous  character in a queue to insert them back in the heap after K iterations
            queue.offer(currentEntry);
            //After k iteration, we re-insert the character after k iterations
            if (queue.size() == k) {
                Map.Entry<Character, Integer> entry = queue.poll();
                if (entry.getValue() > 0) {
                    maxHeap.add(entry);
                }
            }
        }
        // if we were successful in appending all the characters to the result string,
        // return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }
}
