package patternsForCodingInterviews.topologicalSort;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744560693_125Unit
public class AlienDictionary {

    /*
    Video example:
    https://www.youtube.com/watch?v=L5n9AA7cHDY&ab_channel=codeTree
    until 3 min 03, easy to understand
    -> Topological sorting for problems where you have some objects that are based on some rules.
     */
    public static void main(String[] args) {
        String result = AlienDictionary.findOrder(new String[]{"ba", "bc", "ac", "cab"});
        // ba, bc -> a comes before c / bc, ac -> b comes before a / ac, cab -> a comes before c
        System.out.println("Character order: " + result);

        result = AlienDictionary.findOrder(new String[]{"cab", "aaa", "aab"});
        System.out.println("Character order: " + result);

        result = AlienDictionary.findOrder(new String[]{"ywx", "wz", "xww", "xz", "zyy", "zwz"});
        System.out.println("Character order: " + result);
    }

    /*
    Time: O(V + E)
    Space: O(V + E)
     */
    public static String findOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        //A. Initialize the graph
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();
        for (String word : words) { //Loop through each words
            for (char character : word.toCharArray()) { //Loop through each letters
                inDegree.put(character, 0);
                graph.put(character, new ArrayList<>());
            }
        }
        //B Build the graph
        for (int i = 0; i < (words.length - 1); i++) {
            //Find ordering of characters from adjacent words
            String w1 = words[i];
            String w2 = words[i + 1];
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char parent = w1.charAt(j);
                char child = w2.charAt(j);
                if (parent != child) { //If the 2 characters are different
                    graph.get(parent).add(child);
                    inDegree.put(child, inDegree.get(child) + 1);
                    //Only the first different character between the two words
                    //will help us find the order
                    break;
                }
            }
        }
        //C Find all sources with O in-Degree
        Queue<Character> sources = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }
        // d. For each source, add it to the
        // sortedOrder and subtract one from all of its
        // children's in-degrees if a child's
        // in-degree becomes zero, add it to sources queue
        StringBuilder sortedOrder = new StringBuilder();
        while (!sources.isEmpty()) {
            Character vertex = sources.poll();
            sortedOrder.append(vertex);
            //get the node's children to decrement their in-degree
            List<Character> children = graph.get(vertex);
            for (Character child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    sources.add(child);
                }
            }
        }
        // if sortedOrder doesn't contain all characters, there is a cyclic dependency
        // between characters, therefore, we  will not be able to find the correct ordering
        // of the characters
        if (sortedOrder.length() != inDegree.size()) {
            return "";
        }
        return sortedOrder.toString();
    }
}