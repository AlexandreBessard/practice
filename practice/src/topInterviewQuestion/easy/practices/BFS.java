package topInterviewQuestion.easy.practices;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/explore/learn/card/graph/620/breadth-first-search-in-graph/3894/
public class BFS {

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 0}
        };
        int n = 4;
        int source = 0;
        int destination = 2;
        //2 paths: 0 -> 1 -> 2 AND 0 -> 2
    }

    //BFS
    static boolean validPath(int n, int[][] edges, int source, int destination) {
        boolean[] visited = new boolean[n];
        Set<Integer>[] graph = new HashSet[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for(int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        visited[source] = true;
        q.add(source);
        while(!q.isEmpty()) {
            int current = q.poll();
            if(current == destination)
                return true;
            for(Integer neighbor: graph[current]) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        return false;
    }
}
