package algorithms.dijkstra;

import java.util.*;

//https://leetcode.com/explore/featured/card/graph/622/single-source-shortest-path-algorithm/3863/
public class NetworkDelayTime {

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
    // Dijkstra's Algorithm : solves single-source shortest path
    /*
    Time: O(N + E log N) E represents vertices and N are the nodes. PriorityQueue -> O(log E) time.
    Space: O(N + E)
     */
    static int networkDelayTime(int[][] times, int n, int k) {
        //<Key: Node, Value <: Key node neighbor and time associated as value>>
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] time : times) {
            if(!map.containsKey(time[0])) {
                map.put(time[0], new HashMap<>());
            }
            map.get(time[0]).put(time[1], time[2]);
        }
        //[0] node, [1] distance
        //PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[1] - b[1])); //Smaller distance are the priority
        Queue<int[]> queue = new LinkedList<>();
        //[0] -> represents the node, [1] -> represents the distance
        queue.add(new int[]{k, 0}); //initialize node k with 0 as distance.
        boolean[] visited = new boolean[n + 1];
        int res = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDistance = cur[1];
            if(visited[curNode]) continue;
            visited[curNode] = true; //Set this node as visited
            res = curDistance;
            n--; //if 0 means we have reached all the nodes
            if(map.containsKey(curNode)) { //If not contains mean this node does not have neighbors associated
                for(int next : map.get(curNode).keySet()) { //Loop through all neighbors
                    int distNeighbor = map.get(curNode).get(next); //Get distance from each neighbor
                    int sumDistance = curDistance + distNeighbor; //sum with previous distance node
                    queue.add(new int[]{next, sumDistance}); //sum distance associated with that node
                }
            }
        }
        return n == 0 ? res : -1;
    }
}
