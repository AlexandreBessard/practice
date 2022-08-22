package patternsForCodingInterviews.topologicalSort;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744530502_121Unit
public class TopologicalSort {

    public static void main(String[] args) {
        List<Integer> result = TopologicalSort.sort(4, new int[][] { new int[] { 3, 2 },
                new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(5, new int[][] { new int[] { 4, 2 },
                new int[] { 4, 3 }, new int[] { 2, 0 }, new int[] { 2, 1 }, new int[] { 3, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(7, new int[][] { new int[] { 6, 4 },
                new int[] { 6, 2 }, new int[] { 5, 3 }, new int[] { 5, 4 },
                new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
        System.out.println(result);
    }

    /*
    Time: O(V + E) v total of vertices, and E is the total number of edges in the graph
    Space: O(V + E), we store all the edges for each vertex in adjacency list
     */
    public static List<Integer> sort(int vertices, int[][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();
        if(vertices <= 0) {
            return sortedOrder;
        }
        //A . Initialize the graph
        Map<Integer, Integer> inDegree = new HashMap<>(); //Count of incoming edges for every vertex
        //Adjency list graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < vertices; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }
        //B. Build the graph
        for(int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            graph.get(parent).add(child); //Put the child into it's parent's list
            inDegree.put(child, inDegree.get(child) + 1); //Increment child's inDegree
        }
        //C. Find all sources, all vertices with 0 in-degree
        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()) {
            if(entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }
        //D. For each source, add  it to the sortedOrder and subtract one from all of its children
        // in-degree if a child's in-degree becomes zero, add it to source queue
        while( ! sources.isEmpty()) {
            int vertex = sources.poll();
            sortedOrder.add(vertex);
            //Get the node's children to decrement their in-degrees
            List<Integer> children = graph.get(vertex);
            for(int child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0) {
                    sources.add(child);
                }
            }
        }
        //Problem 1: Find if a given Directed Graph has a cycle in it or not.
        /*
        Solution: If we can’t determine the topological ordering of all the vertices of a directed graph,
        the graph has a cycle in it. This was also referred to in the above code:
         */
        if(sortedOrder.size() != vertices) {
            return new ArrayList<>();
        }
        return sortedOrder;
    }

}
