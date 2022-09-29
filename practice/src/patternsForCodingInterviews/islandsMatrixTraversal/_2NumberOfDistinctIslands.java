package patternsForCodingInterviews.islandsMatrixTraversal;

import java.util.HashSet;
import java.util.Set;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d679f3bf35bUnit
public class _2NumberOfDistinctIslands {

    public static void main(String[] args) {
        System.out.println(findDistinctIslandsDFS(
                new int[][]{
                        {1, 1, 0, 1, 1},
                        {1, 1, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {0, 1, 1, 0, 1},
                        {0, 1, 1, 0, 1}
                }));

        /*
        System.out.println(findDistinctIslandsDFS(
                new int[][]{
                        {1, 1, 0, 1},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 1, 0}
                }));

         */
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
