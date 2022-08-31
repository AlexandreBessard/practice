package algorithms.unionFind;
//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3911/
public class NumberOfConnectedComponentsInAnUndirectedGraph {

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {1, 2},
                {3, 4}
        };
        int[][] edges1 = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4}
        };
        var obj = new NumberOfConnectedComponentsInAnUndirectedGraph();
        System.out.println(obj.countComponents(5, edges));
        //System.out.println(obj.countComponents(5, edges1));
    }

    //✔️ Solution 1c: Union-Find with Path Compression and Union by Size ~ 1ms
    //https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/discuss/516491/Java-Union-Find-DFS-BFS-Solutions-Complexity-Explain-Clean-code
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        int components = n;
        for (int[] e : edges) {
            int p1 = findParent(parent, e[0]);
            int p2 = findParent(parent, e[1]);
            //Edges are not connected
            if (p1 != p2) {
                if (size[p1] < size[p2]) { // Merge small size to large size
                    size[p2] += size[p1];
                    //Connection
                    parent[p1] = p2;
                } else {
                    size[p1] += size[p2];
                    //Connection
                    parent[p2] = p1;
                }
                components--;
            }
        }
        return components;
    }
    private int findParent(int[] parent, int i) {
        if (i == parent[i])
            return i;
        return parent[i] = findParent(parent, parent[i]); // Path compression
    }
}
