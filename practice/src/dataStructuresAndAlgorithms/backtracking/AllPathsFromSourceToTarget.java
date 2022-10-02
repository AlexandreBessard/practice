package dataStructuresAndAlgorithms.backtracking;

import java.util.*;

public class AllPathsFromSourceToTarget {
    /*
    Acyclic graph means it is a graph having no graph cycles.
    Example: A -> B -> C

    Cyclic graph contains at least one cycle
    Example: A -> B -> C -> A
     */

    public static void main(String[] args) {
        int[][] graph = {
                {4, 3, 1}, //Node 0 -> list of nodes you can visit from i (0)
                {3, 2, 4}, //Node 1
                {3}, //Node 2
                {4}, //Node 3
                {} //Node 4
        };
        var obj = new AllPathsFromSourceToTarget();
        obj.allPathsSourceTargetBackTracking(graph);
        for (List<Integer> k : obj.results) {
            System.out.println(k);
        }
        System.out.println("\n");
        var obj1 = new AllPathsFromSourceToTarget();
        for (List<Integer> e : obj1.allPathsSourceTargetTopDown(graph)) {
            System.out.println(e);
        }
        System.out.println("\n");
        int[][] graph2 = { {1, 2}, {3}, {3}, {} };
        var obj2 = new AllPathsFromSourceToTarget();
        for (List<Integer> e : obj2.allPathsSourceTargetDFS(graph2)) {
            System.out.println(e);
        }
    }

    //Approach 1: Backtracking
    /*
    Time: O(2n * N)
     */
    private int target;
    private int[][] graph;
    private List<List<Integer>> results;

    public List<List<Integer>> allPathsSourceTargetBackTracking(int[][] graph) {
        this.target = graph.length - 1;
        this.graph = graph;
        this.results = new ArrayList<>();
        //adopt linkedList for fast access to the tail element
        LinkedList<Integer> path = new LinkedList<>();
        path.add(0);
        //Kick of the backtracking starting from source (node 0)
        this.backtracking(0, path);
        return null;
    }

    private void backtracking(int currNode, LinkedList<Integer> path) {
        //# Found the solution
        if (currNode == this.target) {
            //Note: one should make a deep copy of the path
            results.add(new ArrayList<>(path));
            return;
        }
        //# Iterate all possible candidates
        for (int nexNode : this.graph[currNode]) {
            //# try with this candidate solution
            path.addLast(nexNode);
            //# given the candidate, explore further
            this.backtracking(nexNode, path);
            //# backtrack : remove the previous choice and try the next choice
            path.removeLast();
        }
    }

    //Approach 2: Top-down dynamic programming
    private Map<Integer, List<List<Integer>>> memo;

    public List<List<Integer>> allPathsSourceTargetTopDown(int[][] graph) {
        this.target = graph.length - 1;
        this.graph = graph;
        this.memo = new HashMap<>();
        return this.allPathToTarget(0);
    }

    private List<List<Integer>> allPathToTarget(int currNode) {
        //Get from the cache
        if (memo.containsKey(currNode)) {
            return memo.get(currNode);
        }
        List<List<Integer>> results = new ArrayList<>();
        //Base case
        if (currNode == this.target) {
            List<Integer> path = new ArrayList<>();
            path.add(target);
            results.add(path);
            return results;
        }
        //Iterate through the paths
        for (int nextNode : this.graph[currNode]) {
            for (List<Integer> path : allPathToTarget(nextNode)) {
                List<Integer> newPath = new ArrayList<>();
                newPath.add(currNode);
                newPath.addAll(path);
                results.add(newPath);
            }
        }
        memo.put(currNode, results);
        return results;
    }


    //Approach 3: DFS -> traverse the graph from start to end and keep track of each node along the path.
    //Because it is a cyclic graph, we do not need to have a visited array mechanism
    /*
    Another dfs solution is to use memorization. Each node will be only visited once since the sub result from this node
    has already been recorded. Memorization increses space cost as well as time cost to record existing paths.
     */
    //We visit from bottom (last node)
    public List<List<Integer>> allPathsSourceTargetDFS(int[][] graph) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        return dfsSearch(graph, 0, map);
    }

    private List<List<Integer>> dfsSearch(int[][] graph, int node, Map<Integer, List<List<Integer>>> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        List<List<Integer>> res = new ArrayList<>();
        if (node == graph.length - 1) {
            List<Integer> path = new LinkedList<>();
            path.add(node);
            res.add(path);
        } else {
            for (int nextNode : graph[node]) {
                List<List<Integer>> subPaths = dfsSearch(graph, nextNode, map);
                for (List<Integer> path : subPaths) {
                    LinkedList<Integer> newPath = new LinkedList<>(path);
                    newPath.addFirst(node);
                    res.add(newPath);
                }
            }
        }
        map.put(node, res);
        return res;
    }
}
