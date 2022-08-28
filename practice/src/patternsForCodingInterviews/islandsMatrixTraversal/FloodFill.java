package patternsForCodingInterviews.islandsMatrixTraversal;

import java.util.Arrays;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d52d75d7964Unit
public class FloodFill {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(floodFill(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }, 1, 3, 2)));

        System.out.println(Arrays.deepToString(floodFill(
                new int[][] {
                        { 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }, 3, 2, 5)));
    }

    //Approach DFS
    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    public static int[][] floodFill(int[][] matrix, int x, int y, int newColor) {
        if(matrix[x][y] != newColor) {
            fillDFS(matrix, x, y, matrix[x][y], newColor);
        }
        return matrix;
    }
    private static void fillDFS(int[][] matrix, int x, int y, int oldColor, int newColor) {
        if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            return;
        }
        if(matrix[x][y] != oldColor) {
            return; //It is not the required color
        }
        matrix[x][y] = newColor;
        // recursively visit all neighboring cells (horizontally & vertically)
        fillDFS(matrix, x + 1, y, oldColor, newColor); // lower cell
        fillDFS(matrix, x - 1, y, oldColor, newColor); // upper cell
        fillDFS(matrix, x, y + 1, oldColor, newColor); // right cell
        fillDFS(matrix, x, y - 1, oldColor, newColor); // left cell
    }

}
