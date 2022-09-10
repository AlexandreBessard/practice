package topInterviewQuestion.facebook.treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/3028/
public class IsGraphBipartite {

    /*
    A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the
    graph connects a node in set A and a node in set B.
     */
    public static void main(String[] args) {
        //False
        int[][] graphArray2 = new int[][]{
                {1, 2, 3}, //0 -> 1, 2, 3
                {0, 2}, //1
                {0, 1, 3}, //2
                {0, 2} //3
        };
        //True
        int[][] graphArray = new int[][]{
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };
        var obj = new IsGraphBipartite();
        System.out.println(obj.isBipartiteBFS(graphArray));
    }

    //DFS
    //https://leetcode.com/problems/is-graph-bipartite/discuss/115487/Java-Clean-DFS-solution-with-Explanation
    /*
    Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
Initialize a color[] array for each node. Here are three states for colors[] array:
0: Haven't been colored yet.
1: Blue.
-1: Red.
For each node,

If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
If it has been colored, check if the current color is the same as the color that is going to be used to color it.
    Time: O(V + E)
    Space: O(V + E)
     */
    public boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {              //This graph might be a disconnected graph. So check each unvisited node.
            if (colors[i] == 0 && !validColor(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean validColor(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }
        colors[node] = color;
        for (int next : graph[node]) {
            if (!validColor(graph, colors, -color, next)) {
                return false;
            }
        }
        return true;
    }


    //BFS same logic as DFS with different colors
    /*
    Time: O(V + E)
    Space: O(V + E)
     */
    public boolean isBipartiteBFS(int[][] graph) {
        int len = graph.length;
        int[] colors = new int[len];
        for(int i = 0; i < len; i++) {
            if(colors[i] != 0)
                continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1; //Blue
            while(!queue.isEmpty()) {
                int curr = queue.poll();
                for(int next : graph[curr]) {
                    if(colors[next] == 0) { // If this node hasn't been colored;
                        colors[next] = -colors[curr]; //Color it with a different color
                        queue.offer(next);
                    } else if(colors[next] != -colors[curr]) { // If it is colored and its color is different, return false;
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
