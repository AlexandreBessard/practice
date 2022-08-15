package topInterviewQuestion.amazon.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2982/
public class WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(
                Arrays.asList("hot","dot","dog","lot","log","cog")
        );
        var obj = new WordLadder();
        System.out.println(obj.ladderLength(beginWord, endWord, wordList));
    }
    private int L;
    private Map<String, List<String>> allComboDict;
    WordLadder() {
        this.L = 0;
        this.allComboDict = new HashMap<>();
    }

    //Approach 2: Bidirectional BFS
    /*
    Time: O(M² x N) where M is the length of words and N is the total number of words in the input
    Space: O(M²x N) to store all M transformations for each of the N words in the all_combo_dict
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if( ! wordList.contains(endWord))
            return 0;
        //Since all words are the same length
        this.L = beginWord.length();
        wordList.forEach(word -> { //hot","dot","dog","lot","log","cog
            for(int i = 0; i < L; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, L);
                List<String> transformations =
                        this.allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                //The second transformation D*g could then be mapped to Dog or Dig, since all of
                //them share the same generic state. Having a common generic transformation means two words are connected and differ by one letter.
                this.allComboDict.put(newWord, transformations);
            }
        });
        //Queues for bidirectional BFS
        //BFS starting from begin word
        Queue<Map.Entry<String, Integer>> Q_begin = new LinkedList<>();
        //BFS starting from endWord
        //String: word, Integer: level
        Queue<Map.Entry<String, Integer>> Q_end = new LinkedList<>();
        Q_begin.add(Pair.of(beginWord, 1));
        Q_end.add(Pair.of(endWord, 1));
        //Visited to make sure we do not repeat the processing same word
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);
        int ans = -1;
        while( ! Q_begin.isEmpty() && ! Q_end.isEmpty()) {
            //Progress forward one step from the shorter Queue
            if(Q_begin.size() <= Q_end.size()) {
                ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            } else {
                ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            }
            if(ans > -1)
                return ans;
        }
        return 0;
    }

    private int visitWordNode(Queue<Map.Entry<String, Integer>> Q,
                              Map<String, Integer> visited,
                              Map<String, Integer> otherVisited)
    {
        for(int j = Q.size(); j > 0; j--) {
            Map.Entry<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for(int i = 0; i < L; i++) {
                //Intermediate words for current word
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, L);
                //Next states are all the words which share the same intermediate state
                for(String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if(otherVisited.containsKey(adjacentWord)) { //Reach the middle, We have found the word
                        return level + otherVisited.get(adjacentWord);
                    }
                    if( ! visited.containsKey(adjacentWord)) {
                        //Save the level as the value of the dictionary
                        visited.put(adjacentWord, level + 1);
                        Q.add(Pair.of(adjacentWord, level + 1));
                    }
                }
            }
        }
        return -1;
    }

    static class Pair {
        public static <K, V> Map.Entry<K, V> of(K first, V second) {
            return new AbstractMap.SimpleEntry<>(first, second);
        }
    }
}
