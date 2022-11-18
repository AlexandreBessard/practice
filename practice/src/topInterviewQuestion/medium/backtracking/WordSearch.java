package topInterviewQuestion.medium.backtracking;

import java.util.LinkedList;
import java.util.Queue;
//https://leetcode.com/problems/word-search/
public class WordSearch {
/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once.
 */
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(exist(board, word));
        System.out.println(existBFS(board, word));
    }

    //Approach 2: BFS
    public static boolean existBFS(char[][] matrix, String word) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == word.charAt(0) && !visited[row][col]) {
                    return bfs(matrix, row, col, word, visited);
                }
            }
        }
        return false;
    }

    private static boolean bfs(char[][] matrix, int row, int col, String word, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        //0 value represents the index to get the corresponding letter from that word
        q.add(new int[]{row, col, 0});
        while (!q.isEmpty()) {
            int currRow = q.peek()[0];
            int currCol = q.peek()[1];
            int currIndex = q.peek()[2];
            q.poll();
            if (currIndex == word.length()) { //true means we found the entire word
                return true;
            }
            if (isOutOfBond(matrix, currRow, currCol)) { //Check boundaries
                continue;
            }
            if (matrix[currRow][currCol] != word.charAt(currIndex)) { //Check if the current letter is the same based on the current index
                continue;
            }
            if (visited[currRow][currCol]) { //Check if visited
                continue;
            }
            visited[currRow][currCol] = true; //Mark the current cell as visited
            q.add(new int[]{currRow + 1, currCol, currIndex + 1}); //down
            q.add(new int[]{currRow, currCol - 1, currIndex + 1}); // left
            q.add(new int[]{currRow - 1, currCol, currIndex + 1}); // up
            q.add(new int[]{currRow, currCol + 1, currIndex + 1}); // right
        }
        return false;
    }

    private static boolean isOutOfBond(char[][] matrix, int row, int col) {
        return row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length;
    }

    //Approach 1: Backtracking:
    /*
    Time complexity: O(N x 3l) where N is the number of cells, direction: 3l (0,1,2) because
    we won't go back to where we come from.
    Space complexity: O(L) where L is the length of the word matched. (Caused by the recursion)
     */
    private static char[][] board;
    private static int ROWS;
    private static int COLS;

    static boolean exist(char[][] board, String word) {
        WordSearch.board = board;
        WordSearch.ROWS = board.length;
        WordSearch.COLS = board[0].length;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (WordSearch.backtrack(row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean backtrack(int row, int col, String word, int index) {
        //Bottom case
        // # If the solution is found
        if (index >= word.length()) {
            return true;
        }
        //Check boundaries
        if (row < 0 || row == ROWS || col < 0 | col == COLS
                || board[row][col] != word.charAt(index))
            return false;

        //Explore neighbor in DFS
        boolean ret = false;
        //mark path before next exploration
        board[row][col] = '#';
        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        // # Iterate all possible candidates
        for (int d = 0; d < 4; d++) { //(0,1,2,3)
            //# Move on the next element in 2d array
            ret = backtrack(row + rowOffsets[d],
                    col + colOffsets[d],
                    word,
                    index + 1);
            if (ret)
                break;
        }
        //Clean upa and return result
        // # Backtrack
        board[row][col] = word.charAt(index);
        return ret;
    }

}
