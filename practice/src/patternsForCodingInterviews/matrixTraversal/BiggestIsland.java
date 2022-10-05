package patternsForCodingInterviews.matrixTraversal;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d53be009288Unit
public class BiggestIsland {

    public static void main(String[] args) {
        System.out.println(maxAreaOfIsland(
                new int[][]{
                        {1, 1, 1, 0, 0},
                        {0, 1, 0, 0, 1},
                        {0, 0, 1, 1, 0},
                        {0, 1, 1, 0, 0},
                        {0, 0, 1, 0, 0}
                }));
    }

    //Approach DFS
    /*
    Time: O(M * N)
    Space: DFS recursion stack can go M*N whre M numbers of rows and N is the number of columns
     */
    public static int maxAreaOfIsland(int[][] matrix) {
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
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
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

}
