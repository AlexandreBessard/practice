package patternsForCodingInterviews.topologicalSort;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744536717_122Unit
public class TasksScheduling {

    public static void main(String[] args) {
        boolean result = isSchedulingPossible(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println("Tasks execution possible: " + result);

        result = isSchedulingPossible(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println("Tasks execution possible: " + result);

        result = isSchedulingPossible(6, new int[][] { new int[] { 2, 5 },
                new int[] { 0, 5 }, new int[] { 0, 4 }, new int[] { 1, 4 },
                new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println("Tasks execution possible: " + result);
    }

    public static boolean isSchedulingPossible(int tasks, int[][] prerequisites) {
        List<Integer> sortedOrder = new ArrayList<>();
        if(tasks <= 0)
            return false;
        // a. Initialize the graph
        HashMap<Integer, Integer> inDegree =
                new HashMap<>(); // count of incoming edges for every vertex
        HashMap<Integer, List<Integer>> graph = new HashMap<>(); // adjacency list graph
        for (int i = 0; i < tasks; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<Integer>());
        }
        //B. Build the graph
        for(int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][0], child = prerequisites[i][1];
            graph.get(parent).add(child); // put the child into it's parent's list
            inDegree.put(child, inDegree.get(child) + 1); // increment child's inDegree
        }
        //C Find all sources (vertices with 0 in-degree
        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if(entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }
        // d. For each source, add it to the sortedOrder and subtract one from all of its
        // children's in-degrees if a child's in-degree becomes zero, add it to sources queue
        while(! sources.isEmpty()) {
            int vertex = sources.poll();
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex);
            for(int child : graph.get(vertex)) {
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0) {
                    sources.add(child);
                }
            }
        }
        // if sortedOrder doesn't contain all tasks, there is a cyclic dependency between
        // tasks, therefore, we will not be able to schedule all tasks
        return sortedOrder.size() == tasks;
    }

}
