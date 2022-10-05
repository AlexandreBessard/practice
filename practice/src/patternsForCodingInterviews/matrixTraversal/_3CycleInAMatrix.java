package patternsForCodingInterviews.matrixTraversal;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d679f1e93e0Unit
public class _3CycleInAMatrix {

    public static void main(String[] args) {
        /*
        System.out.println(hasCycle(
                new char[][]{
                        {'a', 'a', 'a', 'a'},
                        {'b', 'a', 'c', 'a'},
                        {'b', 'a', 'c', 'a'},
                        {'b', 'a', 'a', 'a'}
                }));
         */
        /*
        System.out.println(hasCycle(
                new char[][]{
                        {'a', 'a', 'a', 'a'},
                        {'a', 'b', 'b', 'a'},
                        {'a', 'b', 'a', 'a'},
                        {'a', 'a', 'a', 'c'}
                }));

        /*
        System.out.println(hasCycle(
                new char[][]{
                        {'a', 'b', 'e', 'b'},
                        {'b', 'b', 'b', 'b'},
                        {'b', 'c', 'c', 'd'},
                        {'c', 'c', 'd', 'd'}
                }));

         */
        System.out.println(hasCycle(
                new char[][]{
                        {'a', 'a', 'a'},
                        {'a', 'b', 'a'},
                        {'a', 'a', 'a'}
                }));

    }

    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    public static boolean hasCycle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    //-1 and -1 for prevX and prevX are simply used to initialize a value which is not contained in the 2D array
                    //Avoid infinite loop
                    if (containsCycleDFS(matrix, visited, matrix[i][j], i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean containsCycleDFS(char[][] matrix, boolean[][] visited, char startChar,
                                            int row, int col, int prevX, int prevY) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) { //Check boundaries
            return false;
        }
        if (matrix[row][col] != startChar) {
            return false;
        }
        if (visited[row][col]) {
            return true; // Found a cycle, we are visiting an already visited valid cell
        }
        visited[row][col] = true; // mark the cell visited
        // recursively visit all neighboring cells (horizontally & vertically)
        if (row + 1 != prevX //This condition avoid to go backward (avoid infinite loop)
                && containsCycleDFS(matrix, visited, startChar, row + 1, col, row, col)) // down
            return true;
        if (row - 1 != prevX //Once we reached top down we look to go up now but it has already been visited with prevX (row) and prevY (col)
                && containsCycleDFS(matrix, visited, startChar, row - 1, col, row, col)) // up
            return true;
        if (col + 1 != prevY
                && containsCycleDFS(matrix, visited, startChar, row, col + 1, row, col)) // right
            return true;
        if (col - 1 != prevY
                && containsCycleDFS(matrix, visited, startChar, row, col - 1, row, col)) // left
            return true;

        return false;
    }


}
