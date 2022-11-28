package leetcode.design;

import java.util.*;

//https://leetcode.com/problems/search-suggestions-system/
public class SearchSuggestionSystem {
    /*
    You are given an array of strings products and a string searchWord.

    Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

    Return a list of lists of the suggested products after each character of searchWord is typed.
     */
    public static void main(String[] args) {
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String[] products2 = {"him", "her", "his", "tim", "hi"};
        String searchWord = "mouse";
        for (List<String> list : suggestedProducts(products2, "h")) {
            System.out.print(list);
        }
    }

    //Approach 2: Trie + DFS
    public static List<List<String>> suggestedProducts(String[] products,
                                                       String searchWord) {

        Trie trie = new Trie();
        List<List<String>> result = new ArrayList<>();
        //Add all words to the trie
        for (String product : products) {
            trie.insert(product);
        }
        //Initialize prefix
        var prefix = new StringBuilder();
        //Find word by prefix
        for (char chr : searchWord.toCharArray()) {
            prefix.append(chr);
            result.add(trie.getWordsStartingWith(prefix.toString()));
        }
        return result;
    }
}

class Trie {
    Node root;
    List<String> resultBuffer;

    Trie() {
        this.root = new Node();
    }

    public List<String> getWordsStartingWith(String prefix) {
        Node currNode = this.root;
        resultBuffer = new ArrayList<>();
        for(char chr : prefix.toCharArray()) {
            if(currNode.children.get(chr) == null) {
                return resultBuffer;
            }
            currNode = currNode.children.get(chr);
        }
        dfsWithPrefix(currNode, prefix);
        return resultBuffer;
    }

    private void dfsWithPrefix(Node currNode, String word) {
        //Limit result size to 3
        if(resultBuffer.size() == 3) { //Base case 1
            return;
        }
        if(currNode.endOfWord) { //Add 1 word to the buffer
            resultBuffer.add(word);
        }
        //Run DFS on all possible path
        //lexicographically minimums products (from 'a' to 'z')
        for(char chr = 'a'; chr <= 'z'; chr++) {
            if(currNode.children.get(chr) != null) {
                Node nextNode = currNode.children.get(chr);
                dfsWithPrefix(nextNode, word + chr);
            }
        }
    }

    public void insert(String word) {
        Node currNode = this.root;
        for (char chr : word.toCharArray()) {
            if(currNode.children.get(chr) == null) {
                currNode.children.put(chr, new Node());
            }
            currNode = currNode.children.get(chr);
        }
        currNode.endOfWord = true;
    }

}

class Node {
    public Map<Character, Node> children = new HashMap<>();
    public boolean endOfWord;
}
