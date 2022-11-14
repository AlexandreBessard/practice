package patternsForCodingInterviews.matrixTraversal;

import java.util.LinkedList;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d6145eebddeUnit
public class NumberOfClosedIslands {

    public static void main(String[] args) {
        //Closed island  -> means surrounded by the water
        System.out.println(countClosedIslandsDFS(
                new int[][] {
                        { 1, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 0 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));


        System.out.println(countClosedIslandsDFS(
                new int[][] {
                        { 0, 0, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 }
                }));




    }

    //return false if out of booundaries else if 0 or visited -> continue
    /*
    BFS approach
     */
    public static int countClosedIslandsBFS(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int countClosedIslands = 0;
        boolean[][] visited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 1 && !visited[i][j]) {
                    if(isClosedIslandBFS(matrix, visited, i, j)) {
                        countClosedIslands++;
                    }
                }
            }
        }
        return countClosedIslands;
    }
    private static boolean isClosedIslandBFS(int[][] matrix,
                                             boolean[][] visited,
                                             int row,
                                             int col)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        while(!q.isEmpty()) {
            int currRow = q.peek()[0];
            int currCol = q.peek()[1];
            q.poll();
            //Check if element is not placed on the limit matrix border
            if(isOutOfBoundaries(matrix, currRow, currCol)
                    && matrix[currRow][currCol] == 1) {
                return false;
            }
            if(visited[currRow][currCol] || matrix[currRow][currCol] == 0) {
                continue;
            }
            visited[currRow][currCol] = true;
            q.add(new int[]{currRow + 1, currCol}); // lower cell
            q.add(new int[]{currRow - 1, currCol}); // upper cell
            q.add(new int[]{currRow, currCol + 1}); // right cell
            q.add(new int[]{currRow, currCol - 1}); // left cell
        }
        return true;
    }

    private static boolean isOutOfBoundaries(int[][] matrix, int row, int col) {
        return row >= matrix.length - 1 || col >= matrix[0].length - 1
                || row < 1 || col < 1;
    }

    //Approach DFS
    /*
    Time: O(N)
    Space: O(1)
     */
    public static int countClosedIslandsDFS(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int countClosedIslands = 0;
        boolean[][] visited = new boolean[rows][cols];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1 && !visited[i][j]) {
                    if(isClosedIslandDFS(matrix, visited, i, j)) {
                        countClosedIslands++;
                    }
                }
            }
        }
        return countClosedIslands;
    }
    private static boolean isClosedIslandDFS(int[][] matrix, boolean[][] visited, int x, int y) {
        if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) //Boundaries
            return false; // returning false since the island is touching an edge
        if(matrix[x][y] == 0 || visited[x][y])
            return true; // returning true as the island is surrounded by water
        visited[x][y] = true;
        boolean isClosed;
        // recursively visit all neighboring cells (horizontally & vertically)
        // a &= b; is equivalent to a = a & b;.
        isClosed = isClosedIslandDFS(matrix, visited, x + 1, y); // lower cell
        // =& if true all condition are true
        isClosed &= isClosedIslandDFS(matrix, visited, x - 1, y); // upper cell
        isClosed &= isClosedIslandDFS(matrix, visited, x, y + 1); // right cell
        isClosed &= isClosedIslandDFS(matrix, visited, x, y - 1); // left cell

        return isClosed;
    }

}
