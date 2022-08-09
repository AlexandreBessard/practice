package topInterviewQuestion.amazon.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/483/
public class WordLadder2 {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        var obj = new WordLadder2();
        //List<List<String>> res = obj.findLadders(beginWord, endWord, wordList);
        List<List<String>> res = obj.findLaddersBidirectional(beginWord, endWord, wordList);
        for(List<String> i : res) {
            System.out.print("[ ");
            for(String el : i ) {
                System.out.print(el + ", ");
            }
            System.out.print(" ]");
        }
    }

    //Approach 2: Bidirectional (BFS) + Backtracking
    /*
    Time complexity: O(NK² + b)
    K is the max length of a word. N is the number of words in 'wordList'.
    b is the number if possible paths from beginWord to endWord in the directed graph we have.
    Space complexity: O(NK)
    N: number of words in wordList, K in the max length of a word
     */
    Map<String, List<String>> adjList = new HashMap<String, List<String>>();
    List<String> currPath = new ArrayList<String>();
    List<List<String>> shortestPaths = new ArrayList<List<String>>();

    private List<String> findNeighbors(String word, Set<String> wordList) {
        List<String> neighbors = new ArrayList<String>();
        char charList[] = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char oldChar = charList[i];

            // replace the i-th character with all letters from a to z except the original character
            for (char c = 'a'; c <= 'z'; c++) {
                charList[i] = c;

                // skip if the character is same as original or if the word is not present in the wordList
                if (c == oldChar || !wordList.contains(String.valueOf(charList))) {
                    continue;
                }
                neighbors.add(String.valueOf(charList));
            }
            charList[i] = oldChar;
        }
        return neighbors;
    }

    private void backtrack(String source, String destination) {
        //System.out.printlen(source);
        // store the path if we reached the endWord
        if (source.equals(destination)) {
            List<String> tempPath = new ArrayList<String>(currPath);
            shortestPaths.add(tempPath);
        }

        if (!adjList.containsKey(source)) {
            return;
        }

        for (int i = 0; i < adjList.get(source).size(); i++) {
            currPath.add(adjList.get(source).get(i));
            backtrack(adjList.get(source).get(i), destination);
            currPath.remove(currPath.size() - 1);
        }
    }

    private void swap(Set<String> forward, Set<String> backward) {
        Set<String> temp = forward;
        forward = backward;
        backward = temp;
    }
    private void addEdge(String word1, String word2, int direction) {
        if(direction == 1) {
            if (!adjList.containsKey(word1)) {
                adjList.put(word1, new ArrayList<String>());
            }
            adjList.get(word1).add(word2);
        } else {
            if (!adjList.containsKey(word2)) {
                adjList.put(word2, new ArrayList<String>());
            }
            adjList.get(word2).add(word1);
        }
    }

    private boolean bfs(String beginWord, String endWord, Set<String> wordList) {
        if ( ! wordList.contains(endWord)) {
            return false;
        }

        // remove the root word which is the first layer
        if (wordList.contains(beginWord)) {
            wordList.remove(beginWord);
        }

        Set<String> forwardQueue =  new HashSet<String>();
        Set<String> backwardQueue = new HashSet<String>();

        forwardQueue.add(beginWord);
        backwardQueue.add(endWord);

        boolean found = false;
        int direction = 1;

        while (forwardQueue.isEmpty() != true)  {
            // visited will store the words of current layer
            Set<String> visited = new HashSet<String>();

            // swap the queues because we are always extending the forwardQueue
            if (forwardQueue.size() > backwardQueue.size()) {
                Set<String> temp = forwardQueue;
                forwardQueue = backwardQueue;
                backwardQueue = temp;
                direction ^= 1; //it is like a switch (on/off)
            }

            for (String currWord : forwardQueue) {
                List<String> neighbors = findNeighbors(currWord, wordList);
                for (String word : neighbors) {

                    // if the backwardQueue already contains it we can stop after completing this level
                    if (backwardQueue.contains(word)) {
                        found = true;
                        addEdge(currWord, word, direction);
                    }

                     /* the word shouldn't be presnt in forwardQueue because if it is then the edge will
                     be between two words at the same level which we don't want */
                    else if (!found && wordList.contains(word)  &&  ! forwardQueue.contains(word)) {
                        visited.add(word);
                        addEdge(currWord, word, direction);
                    }
                }
            }

            // removing the words of the previous layer
            for (String currWord : forwardQueue) {
                if (wordList.contains(currWord)) {
                    wordList.remove(currWord);
                }
            }

            if (found) {
                break;
            }

            forwardQueue = visited;
        }
        return found;
    }

    public List<List<String>> findLaddersBidirectional(String beginWord, String endWord, List<String> wordList) {
        // copying the words into the set for efficient deletion in BFS
        Set<String> copiedWordList = new HashSet<>(wordList);
        // build the DAG using BFS
        boolean sequence_found = bfs(beginWord, endWord, copiedWordList);

        // There is no valid sequence that connects `beginWord` to `endWord`
        if ( ! sequence_found) {
            return shortestPaths;
        }
        // every path will start from the beginWord
        currPath.add(beginWord);
        // traverse the DAG to find all the paths between beginWord and endWord
        backtrack(beginWord, endWord);

        return shortestPaths;
    }



    //----------------------------------------------------------------
    //Approach 1: BFS + Backtracking
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<>();// Neighbors for every node
        HashMap<String, Integer> distance = new HashMap<>();// Distance of every node from the start node
        ArrayList<String> solution = new ArrayList<>();
        dict.add(start);
        //BFS by level
        bfs(start, end, dict, nodeNeighbors, distance);
        //DFS
        dfs(start, end, dict, nodeNeighbors, distance, solution, res);
        return res;
    }
    //Output all paths with the shortest distance
    private void dfs(String cur, String end, Set<String> dict,
                     HashMap<String, ArrayList<String>> nodeNeighbors,
                     HashMap<String, Integer> distance, ArrayList<String> solution,
                     List<List<String>> res)
    {
        solution.add(cur);
        if(end.equals(cur)) { // # Find a solution
            res.add(new ArrayList<>(solution));
        } else {
            for(String next : nodeNeighbors.get(cur)) {
                if(distance.get(next) == distance.get(cur) + 1) {
                    // # Next candidate
                    dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }
        //# backtracking
        solution.remove(solution.size() - 1);
    }
    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<String>());
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        distance.put(start, 0);
        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                ArrayList<String> neighbors = getNeighbors(cur, dict);
                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {// Check if visited
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor))// Found the shortest path
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }
            if (foundEnd)
                break;
        }
    }
// Find all next level nodes.
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();
        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }

}
