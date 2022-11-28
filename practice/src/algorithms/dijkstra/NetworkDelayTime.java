package algorithms.dijkstra;

import java.util.*;

//https://leetcode.com/explore/featured/card/graph/622/single-source-shortest-path-algorithm/3863/
public class NetworkDelayTime {
/*
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 */
    public static void main(String[] args) {
        int[][] times = { //times[i] = (source node, target node, time it takes for all the n nodes to receive the signal
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        int n = 4; //4 node in total 1-indexed
        int k = 2; //Start from the node k
        System.out.println(networkDelayTime(times, n, k));
    }

    //Correction : https://leetcode.com/problems/network-delay-time/discuss/210698/Java-Djikstrabfs-Concise-and-very-easy-to-understand
    //Approach 3
    // “Dijkstra’s algorithm” solves the “single-source shortest path” problem in a weighted directed graph with non-negative weights.
    /*
    Time: O(N + E log N) E represents vertices and N are the nodes. PriorityQueue -> O(log E) time.
    Space: O(N + E)
     */
    static int networkDelayTime(int[][] times, int n, int signalFromGivenNode) {
        //<Key: Source Node, Value <: Key node neighbor and time associated as value>>
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] time : times) {
            int sourceNode = time[0];
            int targetNode = time[1];
            int timeForSignalToTravelFromSourceToTarget = time[2];
            if(!map.containsKey(sourceNode)) {
                map.put(sourceNode, new HashMap<>());
            }
            map.get(sourceNode).put(targetNode, timeForSignalToTravelFromSourceToTarget);
        }
        //[0] node, [1] distance
        //PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[1] - b[1])); //Smaller distance are the priority
        Queue<int[]> queue = new LinkedList<>();
        //[0] -> represents the node, [1] -> represents the distance
        queue.add(new int[]{signalFromGivenNode, 0}); //initialize node signalFromGivenNode with 0 as distance.
        boolean[] visited = new boolean[n + 1]; // 1-indexed based
        int res = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDistance = cur[1];
            if(visited[curNode])  {
                continue;
            }
            visited[curNode] = true; //Set this node as visited
            res = curDistance;
            n--; //if 0 means we have reached all the nodes
            if(map.containsKey(curNode)) { //If not contains mean this node does not have neighbors associated
                for(int neighbor : map.get(curNode).keySet()) { //Loop through all neighbor
                    int distNeighbor = map.get(curNode).get(neighbor); //Get distance from each neighbor
                    int sumDistance = curDistance + distNeighbor; //sum with previous distance node
                    queue.add(new int[]{neighbor, sumDistance}); //sum distance associated with that node
                }
            }
        }
        return n == 0 ? res : -1;
    }
}
