package patternsForCodingInterviews.topologicalSort;

import java.util.*;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744554328_124Unit
public class AllTasksSchedulingOrders {

    public static void main(String[] args) {
        /*
        printOrders(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println();

         */
        printOrders(4, new int[][] { new int[] { 3, 2 },
                new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println();

        /*
        printOrders(6,
                new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                        new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println();

         */
    }

    /*
    Time: O(V! * E) where V is the total number of tasks and E is the
    total prerequisites.
    We need E part because in each recursive call at max we remove and
    add back all the edges
     */
    public static void printOrders(int tasks, int[][] prerequisites) {
        List<Integer> sortedOrder = new ArrayList<>();
        if(tasks <= 0)
            return;
        //A initialize the graph
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < tasks; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }
        //B build the graph
        for(int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][0];
            int child = prerequisites[i][1];
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }
        //C find all sources (O in-degree)
        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if(entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }
        printAllTopologicalSorts(graph, inDegree, sources, sortedOrder);
    }
    private static void printAllTopologicalSorts(Map<Integer, List<Integer>> graph,
                                                 Map<Integer, Integer> inDegree,
                                                 Queue<Integer> sources,
                                                 List<Integer> sortedOrder)
    {
        if( ! sources.isEmpty()) {
            for(Integer vertex : sources) {
                sortedOrder.add(vertex);
                Queue<Integer> sourcesForNextCall = cloneQueue(sources);
                System.out.println(sourcesForNextCall.toString());
                // only remove the current source, all other sources should
                // remain in the queue for the next call
                sourcesForNextCall.remove(vertex);
                //get the node's children to decrement their in-degrees
                List<Integer> children = graph.get(vertex);
                for(int child : children) {
                    inDegree.put(child, inDegree.get(child) - 1);
                    if(inDegree.get(child) == 0) {
                        // Save the new source for the next call
                        sourcesForNextCall.add(child);
                    }
                }
                //Recursive call to print other orderings from the remaining (and new)
                // sources
                printAllTopologicalSorts(
                        graph,
                        inDegree,
                        sourcesForNextCall,
                        sortedOrder);
                //Backtrack, remove the vertex from the sorted order and put all of its
                //children back to consider the next source instead of the current vertex
                sortedOrder.remove(vertex);
                for(int child : children) {
                    inDegree.put(child, inDegree.get(child) + 1);
                }
            }
        }
        // if sortedOrder doesn't contain all tasks, either we've a cyclic dependency
        // between tasks, or we have not processed all the tasks in this recursive call
        if(sortedOrder.size() == inDegree.size()){
            System.out.println(sortedOrder);
        }
    }
    //Make a deep copy of the Queue
    private static Queue<Integer> cloneQueue(Queue<Integer> queue) {
        Queue<Integer> clone = new LinkedList<>();
        for(Integer num : queue) {
            clone.add(num);
        }
        return clone;
    }

}
