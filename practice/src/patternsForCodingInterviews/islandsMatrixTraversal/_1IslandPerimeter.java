package patternsForCodingInterviews.islandsMatrixTraversal;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_62d679f549f28Unit
public class _1IslandPerimeter {

    public static void main(String[] args) {
        System.out.println(findIslandPerimeter(
                new int[][] {
                        { 1, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));

        System.out.println(findIslandPerimeter(
                new int[][] {
                        { 0, 0, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 1, 1, 0 },
                        { 0, 1, 0, 0 }
                }));
    }

    /*
    Time: O(M * N)
    Space: O(M * N)
     */
    public static int findIslandPerimeter(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 1 && !visited[i][j]) {
                    return islandPerimeterDFS(matrix, visited, i, j);
                }
            }
        }
        return 0;
    }
    private static int islandPerimeterDFS(int[][] matrix, boolean[][] visited, int x, int y) {
        if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            return 1; //return 1 since this a boundary cell initiated this DFS call
        }
        if(matrix[x][y] == 0) {
            return 1; // return 1 because of the shared side b/w a water and land cell
        }
        if(visited[x][y]) {
            return 0; // we have already taken care of this cell
        }
        visited[x][y] = true;
        int edgeCount = 0;
        // recursively visit all neighboring cells (horizontally & vertically)
        edgeCount += islandPerimeterDFS(matrix, visited, x + 1, y); // lower cell
        edgeCount += islandPerimeterDFS(matrix, visited, x - 1, y); // upper cell
        edgeCount += islandPerimeterDFS(matrix, visited, x, y + 1); // right cell
        edgeCount += islandPerimeterDFS(matrix, visited, x, y - 1); // left cell
        return edgeCount;
    }


}
