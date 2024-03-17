package patternsForCodingInterviews.matrixTraversal;

import java.util.LinkedList;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d52d75d7964Unit
public class FloodFill {

    public static void main(String[] args) {
        print2DArray("DFS", floodFillDFS(
                new int[][]{
                        {0, 1, 1, 1, 0},
                        {0, 0, 0, 1, 1},
                        {0, 1, 1, 1, 0},
                        {0, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0}
                }, 1, 3, 2));

        print2DArray("BFS", floodFillBFS(
                new int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 1, 0, 0}
                }, 3, 2, 5));

        print2DArray("BFS", floodFillBFS(
                new int[][]{
                        {1, 1, 1},
                        {1, 1, 0},
                        {1, 0, 1}
                }, 1, 1, 2));

    }

    private static void print2DArray(String algo, int[][] matrix) {
        System.out.println(algo);
        for (int[] m : matrix) {
            for (int i : m) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    //Approach BFS
    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    public static int[][] floodFillBFS(int[][] matrix, int x, int y, int newColor) {
        if(matrix[x][y] != newColor) {
            floodFillBFS(matrix, x, y, matrix[x][y], newColor);
        }
        return matrix;
    }
    private static void floodFillBFS(int[][] matrix, int x, int y, int oldColor, int newColor) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        while(!q.isEmpty()) {
            int row = q.peek()[0];
            int col = q.poll()[1];
            if(isNotAValidCell(matrix, row, col)) {
                continue;
            }
            if(matrix[row][col] != oldColor) {
                continue;
            }
            matrix[row][col] = newColor;
            q.add(new int[]{row + 1, col});
            q.add(new int[]{row, col - 1});
            q.add(new int[]{row - 1, col});
            q.add(new int[]{row, col + 1});
        }
    }

    //Approach DFS
    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    public static int[][] floodFillDFS(int[][] matrix, int x, int y, int newColor) {
        if (matrix[x][y] != newColor) {
            fillDFS(matrix, x, y, matrix[x][y], newColor);
        }
        return matrix;
    }

    private static void fillDFS(int[][] matrix, int x, int y, int oldColor, int newColor) {
        if (isNotAValidCell(matrix, x, y)) {
            return;
        }
        if (matrix[x][y] != oldColor) { //Base case, if we have a different color with the old one it is our boundary
            return; //It is not the required color
        }
        matrix[x][y] = newColor; //set with the new color
        // recursively visit all neighboring cells (horizontally & vertically)
        fillDFS(matrix, x + 1, y, oldColor, newColor); // lower cell
        fillDFS(matrix, x - 1, y, oldColor, newColor); // upper cell
        fillDFS(matrix, x, y + 1, oldColor, newColor); // right cell
        fillDFS(matrix, x, y - 1, oldColor, newColor); // left cell
    }

    private static boolean isNotAValidCell(int[][] matrix, int x, int y) {
        return x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length;
    }

}
