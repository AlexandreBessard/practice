package topInterviewQuestion.facebook.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/277/
public class CloneGraph {

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        List<Node> l1 = new ArrayList<>(List.of(two, four));
        one.neighbors = l1;
        List<Node> l2 = new ArrayList<>(List.of(one, three));
        two.neighbors = l2;
        List<Node> l3 = new ArrayList<>(List.of(two, four));
        three.neighbors = l3;
        List<Node> l4 = new ArrayList<>(List.of(one, three));
        four.neighbors = l4;

        var obj = new CloneGraph();
        Node res = obj.cloneGraphDFS(one);
    }

    /*
     The key for the hash map would be the node of the original graph and corresponding
     value would be the corresponding cloned node of the cloned graph.
     If the node already exists in the visited we return corresponding stored reference of the cloned node.
     */
    //Key: original Node, Value: cloned Node
    private Map<Node, Node> visited = new HashMap<>();

    //Approach 1: DFS
    /*
    Time: O(N + M) -> N number of nodes (vertices) and M number of edges
    Space: O(N) -> visited hashMap
     */
    public Node cloneGraphDFS(Node node) {
        if (node == null) {
            return node;
        }
        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);
        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraphDFS(neighbor));
        }
        return cloneNode;
    }

    //Approach 2: BFS
    /*
    Time: O(N + M)
    Space: O(N)
     */
    public Node cloneGraphBFS(Node node) {
        if(node == null)
            return node;
        // Hash map to save the visited node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        Map<Node, Node> visited = new HashMap<>();
        //Put first node in the Q
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        //Clone the node and put it to the visited map
        visited.put(node, new Node(node.val, new ArrayList<>()));
        //Start BFS traversal
        while(!queue.isEmpty()) {
            //Pop the node from the front of head of this queue
            Node n = queue.remove();
            //Iterate through all neighbors
            for(Node neighbor : n.neighbors) {
                if(!visited.containsKey(neighbor)) {
                    //Clone the neighbor and put it has visited
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    //Add to the Q
                    queue.add(neighbor);
                }
                // Add the clone of the neighbor to the neighbors of the clone node "n".
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }
        //Return the clone of the node from visited
        return visited.get(node);
    }


    static class Node {
        int val;
        List<Node> neighbors;

        Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

}
