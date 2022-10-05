package patternsForCodingInterviews.matrixTraversal;

import java.util.LinkedList;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1654828199398_262Unit
public class NumberOfIslands {

    public static void main(String[] args) {
        System.out.println(countIslandsWithVisitedMatrix(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));

        System.out.println(countIslandsWithVisitedMatrix(
                new int[][] {
                        { 1, 1, 1, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }));
    }

    //Approach BFS with visited matrix
    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    public static int countIslandsWithVisitedMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int totalIsland = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // if the cell has not been visited before and is a land
                if (!visited[i][j] && matrix[i][j] == 1) {
                    // we have found an island
                    totalIsland++;
                    visitIslandBFS(matrix, visited, i, j);
                }
            }
        }
        return totalIsland;
    }

    private static void visitIslandBFS(int[][] matrix, boolean[][] visited, int x, int y) {
        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(new int[]{x, y});
        while (!neighbors.isEmpty()) {
            int row = neighbors.peek()[0];
            int col = neighbors.peek()[1];
            neighbors.poll();
            if (isNotAValidCell(matrix, row, col))  //If condition is true, not valid
                continue; // continue, if it is not a valid cell
            if (matrix[row][col] == 0 || visited[row][col])
                continue; // continue if the cell is water or visited

            visited[row][col] = true; // mark the visited array

            // insert all neighboring cells to the queue for BFS
            neighbors.add(new int[]{row + 1, col}); // lower cell
            neighbors.add(new int[]{row - 1, col}); // upper cell
            neighbors.add(new int[]{row, col + 1}); // right cell
            neighbors.add(new int[]{row, col - 1}); // left cell
        }
    }


    //Approach BFS:
    /*
    Time: O(M*N)
    Space: O(min(M, N) worst case when matrix is completely filled with land cells.
    Size of the Q can grow up to min(M, N)
     */
    public static int countIslandsBFS(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int totalIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) { // only if the cell is a land
                    // we have found an island
                    totalIslands++;
                    visitIslandBFS(matrix, i, j);
                }
            }
        }
        return totalIslands;
    }
    private static void visitIslandBFS(int[][] matrix, int x, int y) {
        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(new int[] { x, y });
        while (!neighbors.isEmpty()) {
            int row = neighbors.peek()[0];
            int col = neighbors.peek()[1];
            neighbors.poll();

            if (isNotAValidCell(matrix, row, col))
                continue; // continue, if it is not a valid cell
            if (matrix[row][col] == 0)
                continue; // continue if it is a water cell

            matrix[row][col] = 0; // mark the cell visited by making it a water cell

            // insert all neighboring cells to the queue for BFS
            neighbors.add(new int[] { row + 1, col }); // lower cell
            neighbors.add(new int[] { row - 1, col }); // upper cell
            neighbors.add(new int[] { row, col + 1 }); // right cell
            neighbors.add(new int[] { row, col - 1 }); // left cell
        }
    }

    //Approach DFS:
    /*
    Time: O(M*N) where M number of rows and N number of columns.
     */
    public static int countIslandsDFS(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int totalIslands = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1) { //If cell is a land
                    totalIslands++;
                    visitIslandsDFS(matrix, i, j);
                }
            }
        }
        return totalIslands;
    }

    private static void visitIslandsDFS(int[][] matrix, int x, int y) {
        if(isNotAValidCell(matrix, x, y)) { //No valid cell
            return;
        }
        if(matrix[x][y] == 0) { //If water cell
            return;
        }
        matrix[x][y] = 0; //mark the cell as visited b y making it a water cell
        //Recursively visit all neighbors
        visitIslandsDFS(matrix, x + 1, y); // lower cell
        visitIslandsDFS(matrix, x - 1, y); // upper cell
        visitIslandsDFS(matrix, x, y + 1); // right cell
        visitIslandsDFS(matrix, x, y - 1); // left cell
    }

    private static boolean isNotAValidCell(int[][] matrix, int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length;
    }

}
