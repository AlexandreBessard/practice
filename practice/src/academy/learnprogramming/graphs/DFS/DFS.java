package academy.learnprogramming.graphs.DFS;

import java.util.*;

//https://www.geeksforgeeks.org/iterative-depth-first-traversal/
public class DFS {

    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 4);

        g.DFS(0);
    }
}
class Graph {

    int V; //Number of vertices;
    LinkedList<Integer>[] adj; // adjacency lists

    public Graph(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for(int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    //Print all not yet visited vertices reachable from s
    /*
    Time complexity: O(V + E): where V is the number of vertices and E is the number of edges in the graph.
    Space complexity: O(V) Since an extra visited array is needed
     */
    public void DFS(int s) {
        List<Boolean> visited = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            visited.set(i, false);
        }
        //Create Stack for DFS
        Stack<Integer> stack = new Stack<>();
        //Push the current source node
        stack.push(s);
        while( ! stack.isEmpty()) {
            //Pop vertex from stack
            s = stack.pop();
            //Stack may contain same vertex, check if already visited
             if( ! visited.get(s)) {
                 System.out.println(s);
                 visited.set(s, true);
             }
             //Get all adjacent vertices, if adjacent has not been visited,
            //then push it to the stack
            Iterator<Integer> itr = adj[s].iterator();
             while(itr.hasNext()) {
                 int v = itr.next();
                 if( ! visited.get(v)) {
                     stack.push(v);
                 }
             }
        }
    }

    //Add an edge to graph
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

}
