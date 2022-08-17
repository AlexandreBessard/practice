package topInterviewQuestion.amazon.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2986/
public class CutOffTreesForGolfEvent {

    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(2, 3, 4));
        forest.add(Arrays.asList(0, 0, 5));
        forest.add(Arrays.asList(8, 7, 6));
        System.out.println(bfs(forest, 0, 0, 2, 0));
    }

    /*
    Must use BFS and NOT DSF because
Since 2D grid is actually a unweighted graph ,
to find a shortest path, the most recommended way is to use BFS.
It is noted that DFS can ALSO find the shortest path,
but it needs to find all feasible paths and find the shortest one.
In this regard, BFS is much faster than DFS!
BFS always gives the shortest path, Here we need minimum steps and so BFS can be used
     */


    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    //Approach 1: BFS
    /*

     */
    public static int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size();
        int C = forest.get(0).size();
        //Queue :  (tail)  [] - []  (head)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sr, sc, 0});
        boolean[][] seen = new boolean[R][C];
        seen[sr][sc] = true;
        while( ! queue.isEmpty()) {
            int[] cur = queue.poll();
            // If the node we are processing is our
            // destination 'target' (tr, tc), we'll return the answer.
            if(cur[0] == tr && cur[1] == tc)
                return cur[2];
            //Directions
            for(int di = 0; di < 4; di++)
            {
                int r = cur[0] + dr[di];
                int c = cur[1] + dc[di];
                if ( r >= 0 && r < R && c >= 0 && c < C //Stay in the forest
                        && !seen[r][c] //Not seen, we can go
                        && forest.get(r).get(c) > 0) //We can walk on it (O) is forbidden
                {
                    seen[r][c] = true;
                    queue.add(new int[] {r, c, cur[2] + 1});
                }
            }
        }
        return -1;
    }
}
