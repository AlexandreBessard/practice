package algorithms.unionFind;
//https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3910/
public class GraphValidTree {

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 4}
        };
        int[][] edges1 = new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {1, 3},
                {1, 4}
        };
        var obj = new GraphValidTree();
        System.out.println(obj.validTree(5, edges));
        System.out.println(obj.validTree(6, edges1));
    }

    /*
    Time: O(N . & (N))
     */
    public boolean validTree(int n, int[][] edges) {
        //Graph must contains n - 1 edges
        if(edges.length != n - 1)
            return false;
        UnionFind unionFind = new UnionFind(n);
        // Add each edge. Check if a merge happened, because if it
        // didn't, there must be a cycle.
        for(int[] edge : edges) {
            int A = edge[0];
            int B = edge[1];
            if(!unionFind.union(A, B)) {
                return false;
            }
        }
        //No cycle
        return true;
    }

    //Union Find approach
    //Optimizations path compression and union by size
    static class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for(int node = 0; node < n; node++) {
                parent[node] = node;
                size[node] = 1;
            }
        }

        public int find(int A) {
            //Step 1: find the root
            if(A == parent[A]) {
                return A;
            }
            return parent[A] = find(parent[A]);
        }
        // The union method, with optimization union by size. It returns True if a
        // merge happened, False if otherwise.
        public boolean union(int A, int B) {
            // Find the roots for A and B.
            int rootA = find(A);
            int rootB = find(B);
            // Check if A and B are already in the same set.
            if (rootA == rootB) {
                return false;
            }
            // We want to ensure the larger set remains the root.
            if (size[rootA] < size[rootB]) {
                // Make rootB the overall root.
                parent[rootA] = rootB;
                // The size of the set rooted at B is the sum of the 2.
                size[rootB] += size[rootA];
            }
            else {
                // Make rootA the overall root.
                parent[rootB] = rootA;
                // The size of the set rooted at A is the sum of the 2.
                size[rootA] += size[rootB];
            }
            return true;
        }

    }

}
