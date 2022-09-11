package topInterviewQuestion.facebook.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/explore/interview/card/facebook/56/design-3/300/
public class AddAndSearchWordDataStructureDesign {

    public static void main(String[] args) {
        System.out.println("Use of Trie :");
        var wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.searchTrie("pad")); // return False
        System.out.println(wordDictionary.searchTrie("bad")); // return True
        System.out.println(wordDictionary.searchTrie(".ad")); // return True
        System.out.println(wordDictionary.searchTrie("b..")); // return True
        System.out.println("\nUse of HashMap :");
        //Non-optimized
        var wordDictionary2 = new  WordDictionary2();
        wordDictionary2.addWord("bad");
        wordDictionary2.addWord("dad");
        wordDictionary2.addWord("mad");
        System.out.println(wordDictionary2.search("pad")); // return False
        System.out.println(wordDictionary2.search("bad")); // return True
        System.out.println(wordDictionary2.search(".ad")); // return True
        System.out.println(wordDictionary2.search("b..")); // return True
    }

    //Approach 1 Non optimized
    static class WordDictionary2 {
        Map<Integer, Set<String>> d;
        /** Initialize your data structure here. */
        public WordDictionary2() {
            d = new HashMap<>();
        }
        /** Adds a word into the data structure. */
        public void addWord(String word) {
            int m = word.length();
            if (!d.containsKey(m)) {
                d.put(m, new HashSet<>());
            }
            d.get(m).add(word);
        }
        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            int m = word.length();
            if (d.containsKey(m)) {
                for (String w : d.get(m)) {
                    int i = 0;
                    while ((i < m) && (w.charAt(i) == word.charAt(i) || word.charAt(i) == '.')) {
                        i++;
                    }
                    if (i == m) return true;
                }
            }
            return false;
        }
    }

    //Approach 2
    //Optimized
    static class WordDictionary {
        TrieNode trie;
        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            trie = new TrieNode();
        }

        /*
        Adds a word into the data structure
         */
        public void addWord(String word) {
            TrieNode node = trie;
            for(char ch : word.toCharArray()) {
                if(!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.word = true; //End of the word
        }

        /** Returns if the word is in the data structure.
         * A word could contain the dot character '.' to represent any one letter. */
        public boolean searchTrie(String word) {
            return searchInNode(word, trie);
        }
        private boolean searchInNode(String word, TrieNode node) {
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(!node.children.containsKey(ch)) {
                    // if the current character is '.'
                    // check all possible nodes at this level
                    if(ch == '.') {
                        for(char x : node.children.keySet()) {
                            TrieNode child = node.children.get(x);
                            if(searchInNode(word.substring(i + 1), child)){
                                return true;
                            }
                        }
                    } else {
                        //In case if we do not find the letter and it is not a '.' return false.
                        return false;
                    }
                } else {
                    node = node.children.get(ch);
                }
            }
            return node.word;
        }
    }
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean word = false;
        TrieNode() {}
    }
}
