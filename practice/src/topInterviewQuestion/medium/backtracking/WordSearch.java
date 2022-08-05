package topInterviewQuestion.medium.backtracking;

public class WordSearch {

    public static void main(String[] args) {

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
    static boolean exist(char[][] board, String word){
        WordSearch.board = board;
        WordSearch.ROWS = board.length;
        WordSearch.COLS = board[0].length;
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLS; col++) {
                if(WordSearch.backtrack(row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean  backtrack(int row, int col, String word, int index) {
        //Bottom case
        // # If the solution is found
        if(index >= word.length()) {
            return true;
        }
        //Check boundaries
        if(row < 0 || row == ROWS || col < 0 | col == COLS
                || board[row][col] != word.charAt(index))
            return false;

        //Explore neighbor in DFS
        boolean ret = false;
        //mark path before next exploration
        board[row][col] = '#';
        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        // # Iterate all possible candidates
        for(int d = 0; d < 4; d++) { //(0,1,2,3)
            //# Move on the next element in 2d array
            ret = backtrack(row + rowOffsets[d],
                    col + colOffsets[d],
                    word,
                    index + 1);
            if(ret)
                break;
        }
        //Clean upa and return result
        // # Backtrack
        board[row][col] = word.charAt(index);
        return ret;
    }

}
