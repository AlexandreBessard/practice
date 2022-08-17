package patternsForCodingInterviews.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628541078811_9Unit
public class _4WordsConcatenation {

    public static void main(String[] args) {
        List<Integer> result;
        //result = findWordConcatenation("catfoxcat", new String[] { "cat", "fox" });
        //System.out.println(result);
        result = findWordConcatenation(
                "catcatfoxfox", new String[] { "cat", "fox" });
        System.out.println(result);
    }

    /*
    Time: O(N * M * Len)
    N number of characters, M total of words and 'Len' is the length of the word
    Space: O(M), Overall O(M + N) -> 2 HashMaps
     */
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for(String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        List<Integer> resultIndices = new ArrayList<>();
        int wordCounts = words.length;
        int wordLength = words[0].length();
        //str.length() - wordCounts * wordLength because we have a second loop to look
        //the words from the list. Avoid indexOutOfBoundException.
        for(int i = 0; i <= str.length() - wordCounts * wordLength; i++) {
            Map<String, Integer> wordsSeen = new HashMap<>();
            //Look further in the string based on words we have
            for(int j = 0; j < wordCounts; j++) {
                //get first index of the current string we looked for
                int nextWordIndex = i + j * wordLength;
                //get the next word from the string
                //Get the entire word we looked for
                String word = str.substring(nextWordIndex, nextWordIndex + wordLength);
                if( ! wordFrequency.containsKey(word)) { //break if we do not need this word
                    break;
                }
                //add the word to the 'wordsSeen' map
                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1);
                //No need to process further if the word has higher frequency than required
                if(wordsSeen.get(word) > wordFrequency.getOrDefault(word, 0)) {
                    break;
                }
                if(j + 1 == wordCounts) { //Store index if we have found all the words
                    resultIndices.add(i);
                }
            }
        }
        return resultIndices;
    }
}
