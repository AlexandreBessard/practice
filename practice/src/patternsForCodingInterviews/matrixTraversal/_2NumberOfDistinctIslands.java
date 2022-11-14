package patternsForCodingInterviews.matrixTraversal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d679f3bf35bUnit
public class _2NumberOfDistinctIslands {

    public static void main(String[] args) {
        System.out.println(findDistinctIslandsBFS(
                new int[][]{
                        {1, 1, 0, 1, 1},
                        {1, 1, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {0, 1, 1, 0, 1},
                        {0, 1, 1, 0, 1}
                }));


        System.out.println(findDistinctIslandsBFS(
                new int[][]{
                        {1, 1, 0, 1},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 1, 0}
                }));


    }
    /*
    BFS approach
     */
    public static int findDistinctIslandsBFS(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Set<String> islandSet = new HashSet<>();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 1 && !visited[i][j]) {
                    var strBuilder = new StringBuilder();
                    traverseIslandBFS(matrix, visited, i, j,
                            strBuilder, Direction.ORIGIN);
                    islandSet.add(strBuilder.toString());
                }
            }
        }
        return islandSet.size();
    }

    private static void traverseIslandBFS(int[][] matrix, boolean[][] visited,
                                          int row, int col, StringBuilder strBuilder,
                                          Direction direction)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        while(!q.isEmpty()) {
            int currRow = q.peek()[0];
            int currCol = q.peek()[1];
            q.poll();
            if(isOutOfBoundaries(matrix, currRow, currCol)) {
                continue;
            }
            if(visited[currRow][currCol] || matrix[currRow][currCol] == 0) {
                continue;
            }
            visited[currRow][currCol] = true;
            strBuilder.append(direction);
            q.add(new int[]{currRow + 1, currCol}); // lower cell
            q.add(new int[]{currRow - 1, currCol}); // upper cell
            q.add(new int[]{currRow, currCol + 1}); // right cell
            q.add(new int[]{currRow, currCol - 1}); // left cell
        }
        strBuilder.append(Direction.BACK);
    }

    private static boolean isOutOfBoundaries(int[][] matrix, int row, int col) {
        return row < 0 || col < 0
                || row >= matrix.length || col >= matrix[0].length;
    }

    /* DFS
    Time: O(M * N)
    Space: O(M * N)
     */
    public static int findDistinctIslandsDFS(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Set<String> islandSet = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    var islandTraversal = new StringBuilder();
                    traverseIslandDFS(matrix, visited, i, j, islandTraversal, Direction.ORIGIN); //origin
                    islandSet.add(islandTraversal.toString());
                }
            }
        }
        return islandSet.size();
    }

    private static void traverseIslandDFS(int[][] matrix, boolean[][] visited, int x, int y,
                                          StringBuilder islandTraversal, Direction direction) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) { //check boundaries
            return;
        }
        if (matrix[x][y] == 0 || visited[x][y]) { //Continue to move forward if true
            return;
        }
        visited[x][y] = true;
        islandTraversal.append(direction);
        // recursively visit all neighboring cells (horizontally & vertically)
        traverseIslandDFS(matrix, visited, x + 1, y, islandTraversal, Direction.DOWN); // down
        traverseIslandDFS(matrix, visited, x - 1, y, islandTraversal, Direction.UP); // up
        traverseIslandDFS(matrix, visited, x, y + 1, islandTraversal, Direction.RIGHT); // right
        traverseIslandDFS(matrix, visited, x, y - 1, islandTraversal, Direction.LEFT); // left
        islandTraversal.append(Direction.BACK); // back
    }

    enum Direction {
        ORIGIN, UP, DOWN, RIGHT, LEFT, BACK;
    }

}
