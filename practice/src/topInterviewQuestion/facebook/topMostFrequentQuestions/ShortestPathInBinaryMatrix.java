package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/shortest-path-in-binary-matrix/
public class ShortestPathInBinaryMatrix {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };
        System.out.println(shortestPathBinaryMatrix(grid));
    }

    /*
    For find the shortest path in the problem, use BFS
     */
    //Approach 2: BFS (without overwriting the input) good for multithreaded environment because other thread might read the grid too.
    static int shortestPathBinaryMatrix(int[][] grid) {
        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }
        //Set up the BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1}); //Put distance with index 2 on the queue
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;
        //Carry out the BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int distance = cell[2];
            // Check if this is the target cell.
            if (row == grid.length - 1 && col == grid[0].length - 1) { //True: Stop the program because we have our answer
                return distance;
            }
            for (int[] neighbor : getNeighbours(row, col, grid)) { //Get valid neighbors from that grid[row][col]
                int neighborRow = neighbor[0];
                int neighborCol = neighbor[1];
                if (visited[neighborRow][neighborCol]) {
                    continue;
                }
                visited[neighborRow][neighborCol] = true;
                queue.add(new int[]{neighborRow, neighborCol, distance + 1});
            }
        }
        //The target was unreachable
        return -1;
    }                            //Directions from diagonal up left -> up -> diagonal up right -> right and so on...

    private final static int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private static List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbors = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            //Check boundaries and if it is 0 or not. If not zero we continue the next element
            if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length || grid[newRow][newCol] != 0) {
                continue;
            }
            neighbors.add(new int[]{newRow, newCol});
        }
        return neighbors;
    }

}
