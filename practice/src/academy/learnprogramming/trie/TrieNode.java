package academy.learnprogramming.trie;

import java.util.HashMap;
import java.util.Map;

//https://www.baeldung.com/trie-java
/*
Ordered Trie Structure which takes advantage of keys that it stores.
Node's position in the tree defines the key with which that node is associated.

Each node in binary search trees always has two children
whereas tries' nodes, on the other hand, can have more


 */
class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        //trie.insert("Programming");
        trie.insert("is");
        /*
        trie.insert("a");
        trie.insert("way");
        trie.insert("of");
        trie.insert("life");

         */
        trie.delete("is");
    }

}

class TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;
    Map<Character, TrieNode> getChildren() {
        return children;
    }
    boolean isEndOfWord() {
        return endOfWord;
    }
    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    //Delete element
    boolean delete(String word) {
        return delete(root, word, 0);
    }
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode =
                delete(node, word, index + 1)
                && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }

    //Time complexity: O(n) where N represents the key
    public void insert(String word) {
        TrieNode current = root;
        for(char l : word.toCharArray()) {
            System.out.println(current);
            current = current
                    .getChildren()
                    .computeIfAbsent(l, c -> new TrieNode());
        }
        current.setEndOfWord(true);
    }

    //Finding element
    public boolean find(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if(node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }


}
