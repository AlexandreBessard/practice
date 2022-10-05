package topInterviewQuestion.medium.treesAndGraphs;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/792/
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid2 = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '0', '0'},
                {'1', '0', '1'}
        };

    }

    //Approach 1: DFS
    /*
    Time complexity: O(M x N) M: rows and N: cols
    Space complexity: O(M x N) caused by recursion.
     */
    /**
    See other solution to avoid override the array :
     * {@link patternsForCodingInterviews.matrixTraversal._2NumberOfDistinctIslands}
     */
    static int numIslandsDFS(char[][] grid) {
        if(grid == null || grid[0].length == 0)
            return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for(int r = 0; r < nr; r++) {
            for(int c = 0; c < nc; c++) {
                if(grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }
        return num_islands;
    }
    private static void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        if(r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0')
            return;
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }



}
