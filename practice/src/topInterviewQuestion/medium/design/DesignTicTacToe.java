package topInterviewQuestion.medium.design;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/112/design/814/
public class DesignTicTacToe {

    public static void main(String[] args) {

    }


    /*
    OPTIMIZED
     */
    //Approach 2:
    static class TicTacToeOptimized {

        private int[] rows;
        private int[] cols;
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
     */
    static class TicTacToe {
        private int[][] board;
        private int n;

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
                    || col == n - row - 1 && checkDiagonal(player)) {
                return player;
            }
            return 0;
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
                if(board[row][row] != player){
                    return false;
                }
            }
            return true;
        }

        private boolean checkColumn(int col, int player) {
            for(int row = 0; row < n; row++) {
                if(board[row][col] != player)
                    return false;
            }
            return true;
        }


        private boolean checkRow(int row, int player) {
            for(int col = 0; col < n; col++) {
                if(board[row][col] != player){
                    return false;
                }
            }
            return true;
        }


    }

}
