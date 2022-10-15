package topInterviewQuestion.top100likedQuestions;
//https://leetcode.com/problems/implement-trie-prefix-tree/
public class ImplementTrie /* (Prefix Tree)  */ {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.seach("apple")); //true
        System.out.println(trie.seach("app")); //False
        System.out.println(trie.startsWith("app"));  //True
        trie.insert("app");
        System.out.println(trie.seach("app")); // True
    }

    static class Trie {
        private TrieNode root;
        //Constructor
        Trie() {
            root = new TrieNode();
        }
        //Insert the string word into the trie
        /*
        Time: O(m) where m is the key length
        Space: O(m)
         */
        void insert(String word) {
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if(!node.containsKey(currChar)) {
                    node.put(currChar, new TrieNode());
                }
                node = node.get(currChar);
            }
            node.setEnd();
        }
        //true if the entire string word is in the trie else false
        /*
        Time: O(m)
        Space: O(1)
         */
        boolean seach(String word) {
            var node = searchPrefix(word);
            return node != null && node.isEnd;
        }
        // search a prefix or whole key in trie and returns the node where search ends
        private TrieNode searchPrefix(String word) {
            var node = root;
            for(int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if(node.containsKey(currChar)) {
                    node = node.get(currChar);
                } else {
                    return null;
                }
            }
            return node;
        }

        //true if there is a previously inserted string 'word' that has the prefix else false
        /*
        Time: O(n)
        Space: O(1)
         */
        boolean startsWith(String prefix) {
            var node = searchPrefix(prefix);
            return node != null;
        }
    }

    static class TrieNode {
        private TrieNode[] links;
        private final int R = 26; //26 -> the number of lowercase latin letters.
        private boolean isEnd;
        public TrieNode() {
            links = new TrieNode[R];
        }
        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }
        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }
        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }

}
