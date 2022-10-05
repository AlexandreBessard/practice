package patternsForCodingInterviews.matrixTraversal;

import java.util.LinkedList;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d53be009288Unit
public class BiggestIsland {

    public static void main(String[] args) {
        System.out.println("DFS -> " + maxAreaOfIslandDFS(
                new int[][]{
                        {1, 1, 1, 0, 0},
                        {0, 1, 0, 0, 1},
                        {0, 0, 1, 1, 0},
                        {0, 1, 1, 0, 0},
                        {0, 0, 1, 0, 0}
                }));
        System.out.println("BFS -> " + maxAreaOfIslandsBFS(
                new int[][]{
                        {1, 1, 1, 0, 0},
                        {0, 1, 0, 0, 1},
                        {0, 0, 1, 1, 0},
                        {0, 1, 1, 0, 0},
                        {0, 0, 1, 0, 0}
                }));
    }

    //Approach BFS
    /*
    Time: O(M * N)
    Space: O(M * N) -> because the visited array and max size of the Q. M is the number of rows and N is the number of columns.
     */
    public static int maxAreaOfIslandsBFS(int[][] matrix) {
        int biggestArea = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1 && !visited[row][col]) {
                    int res = visitIslandBFS(matrix, visited, row, col);
                    biggestArea = Math.max(biggestArea, res);
                }
            }
        }
        return biggestArea;
    }

    private static int visitIslandBFS(int[][] matrix, boolean[][] visited, int row, int col) {
        int area = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            int r = queue.peek()[0];
            int c = queue.peek()[1];
            queue.poll();
            if (isNotAValidCell(matrix, r, c)) { //Check boundaries
                continue;
            }
            if (matrix[r][c] == 0 || visited[r][c]) { //Check if cell is 0 or if it is visited
                continue;
            }
            visited[r][c] = true;
            area++;
            queue.add(new int[]{r + 1, c});
            queue.add(new int[]{r - 1, c});
            queue.add(new int[]{r, c + 1});
            queue.add(new int[]{r, c - 1});
        }
        return area;
    }

    //---------------------------------------------------------------------------
    //Approach DFS
    /*
    Time: O(M * N)
    Space: DFS recursion stack can go M*N whre M numbers of rows and N is the number of columns
     */
    public static int maxAreaOfIslandDFS(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int biggestIslandArea = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    biggestIslandArea = Math.max(
                            biggestIslandArea,
                            visitIslandDFS(matrix, i, j));
                }
            }
        }
        return biggestIslandArea;
    }

    private static int visitIslandDFS(int[][] matrix, int row, int col) {
        if (isNotAValidCell(matrix, row, col)) { //Check boundaries
            return 0; // no valid cell
        }
        if (matrix[row][col] == 0) {
            return 0; // is a water cell
        }
        matrix[row][col] = 0; // mark the cell
        int area = 1; //Counting current cell
        area += visitIslandDFS(matrix, row + 1, col); // lower cell
        area += visitIslandDFS(matrix, row - 1, col); // upper cell
        area += visitIslandDFS(matrix, row, col + 1); // right cell
        area += visitIslandDFS(matrix, row, col - 1); // left cell
        return area;
    }

    private static boolean isNotAValidCell(int[][] matrix, int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length;
    }

}
