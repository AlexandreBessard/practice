package academy.learnprogramming.graphs.BFS;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
public class BFS {

    /*
    Finding the shortest path between two nodes u and v,
    with path length measured by the total number of edges (an advantage over depth–first search).
     */
    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.BFS(2);

    }
}

class Graph {
    private int V; //Vertices
    private LinkedList<Integer>[] adj;

    public Graph(int v) {
        V = v;
        adj = new LinkedList[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }
    //Print BFS traversal from a given source
    /*
    Time complexity: O(V + E) where V is the number of nodes and E is the number of edges.
    Space complexity: O(V) used by visited array.
     */
    public void BFS(int s) {
        boolean[] visited = new boolean[V];
        //Create Queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        //Mark the current node as visited
        visited[s] = true;
        queue.add(s);
        while( ! queue.isEmpty()) {
            s = queue.poll();
            System.out.println(s);
            //Get all adjacent vertices if not visited, then mark it visited and enqueue it.
            Iterator<Integer> itr = adj[s].iterator();
            while(itr.hasNext()) {
                int n = itr.next();
                if( ! visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

    }
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }
}
