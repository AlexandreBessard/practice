package algorithms.unionFind;

import java.util.*;

//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3916/
public class OptimizedWaterDistributionInAVillage {

    public static void main(String[] args) {
        int n = 3;
        int[] wells = {1, 2, 2};
        int[][] pipes = {
                {1, 2, 1},
                {2, 3, 1}
        };
        var obj = new OptimizedWaterDistributionInAVillage();
        //Output : 3
        System.out.println(obj.minCostToSupplyWater(n, wells, pipes));
    }
    //Greedy algorithm, best edges to be added with the lower cost
    //used by the Heap

    //Approach 2: Kruskal's Algorithm with Union-Find
    // Kruskal's is an algo to construct a minimum spanning tree
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        //min heap to maintain the order of edges to be visited
        PriorityQueue<Map.Entry<Integer, Integer>> edgesHeap =
                new PriorityQueue<>(n, (a, b) -> a.getKey() - b.getKey());
        //Representation of the graph in adjacency list
        List<List<Map.Entry<Integer, Integer>>> graph =
                new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        //add a virtual vertex indexed with 0
        // then add an edge to each of the house weighted by the cost
        for (int i = 0; i < wells.length; i++) {
            Map.Entry<Integer, Integer> virtualEdge = Pair.of(wells[i], i + 1);
            graph.get(0).add(virtualEdge);
            //Initialize the heap with the edges from the virtual vertex
            edgesHeap.add(virtualEdge);
        }
        // add the bidirectional edges to the graph
        for (int i = 0; i < pipes.length; i++) {
            int house1 = pipes[i][0];
            int house2 = pipes[i][1];
            int cost = pipes[i][2];
            //graph is undirected (bidirectional) we need to add two entries
            graph.get(house1).add(Pair.of(cost, house2));
            graph.get(house2).add(Pair.of(cost, house1));
        }
        //Kick off the exploration from the virtual vertex 0
        //We need a set to maintain all the vertices that we have
        // added to the final minimum spanning tree.
        Set<Integer> mstSet = new HashSet<>();
        mstSet.add(0);
        int totalCost = 0;
        while (mstSet.size() < n + 1) {
            Map.Entry<Integer, Integer> edge = edgesHeap.poll();
            int cost = edge.getKey();
            int nextHouse = edge.getValue();
            if (mstSet.contains(nextHouse)) {
                continue;
            }
            //adding the new vertex into the set
            mstSet.add(nextHouse);
            totalCost += cost;
            //Expanding the candidates of edge to choose from in the next round
            for (Map.Entry<Integer, Integer> neighborEdge : graph.get(nextHouse)) {
                if (!mstSet.contains(neighborEdge.getValue())) {
                    edgesHeap.add(neighborEdge);
                }
            }
        }
        return totalCost;
    }

    static class Pair {
        public static <T, U> Map.Entry<T, U> of(T first, U second) {
            return new AbstractMap.SimpleEntry<>(first, second);
        }
    }

}
