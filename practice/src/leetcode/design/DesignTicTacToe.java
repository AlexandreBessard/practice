package leetcode.design;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/112/design/814/
public class DesignTicTacToe {

    public static void main(String[] args) {

    }

    /*
    OPTIMIZED
     */
    //Approach 2:
    static class TicTacToeOptimized {

        private final int[] rows;
        private final int[] cols;
        int diagonal;
        int antiDiagonal;

        public TicTacToeOptimized(int n) {
            rows = new int[n];
            cols = new int[n];
        }

        //Time complexity:  O(1)
        //Space complexity: O(n) because we use arrays: cols and rows.
        public int move(int row, int col, int player) {
            int currentPlayer = (player == 1) ? 1 : -1;
            rows[row] += currentPlayer;
            cols[col] += currentPlayer;
            //Update diagonal
            if(row == col) {
                diagonal += currentPlayer;
            }
            if(col == (cols.length - row - 1)){
                antiDiagonal += currentPlayer;
            }
            int n = rows.length;
            //check If current player win
            if(Math.abs(rows[row]) == n
                    || Math.abs(cols[col]) == n
                    || Math.abs(diagonal) == n
                    || Math.abs(antiDiagonal) == n){
                return player;
            }
            //no one wins
            return 0;
        }

    }



    /*
    NON OPTIMIZED
    Time: O(n) for every move we are iterating over n cells 4 times to check for each column, row, diagonal row, anti-diagonal
    Space: O(n2) as we are storing 2-dimensional array
     */
    static class TicTacToe {
        private final int[][] board;
        private final int n;

        public TicTacToe(int n) {
            board = new int[n][n];
            this.n = n;
        }

        //Time complexity: O(n), every move we are iterating over n cells 4 times
        //O(4 x n) which is equivalent of O(n)
        /*
        Space complexity: O(n²) because we are using 2 dimensional array
         */
        public int move(int row, int col, int player) {
            //Return 0 if no one win else 1 if player 1 or 2 wins
            board[row][col] = player;
            //Check if player wins
            if(checkRow(row, player)
                    || checkColumn(col, player)
                    || row == col && checkDiagonal(player)
                    || col == n - row - 1 && checkAntiDiagonal(player)) {
                return player; //This player won
            }
            return 0; //Nobody wins
        }

        private boolean checkAntiDiagonal(int player) {
            for(int row = 0; row < n; row++) {
                if(board[row][n - row - 1] != player)
                    return false;
            }
            return true;
        }

        private boolean checkDiagonal(int player) {
            for(int row = 0; row < n; row++) {
                if(board[row][row] != player){ //Check diagonal
                    return false;
                }
            }
            return true;
        }

        private boolean checkColumn(int col, int player) { //Check the entire column
            for(int row = 0; row < n; row++) {
                if(board[row][col] != player) {
                    return false;
                }
            }
            return true; //Means we have the same value vertically
        }


        private boolean checkRow(int row, int player) { //Check the entire row
            for(int col = 0; col < n; col++) {
                if(board[row][col] != player){
                    return false;
                }
            }
            return true; //Means we have the same value horizontally
        }

    }

}
