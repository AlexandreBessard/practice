package topInterviewQuestion.easy.array;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/769/
public class ValidSudoku {

    public static void main(String[] args) {
        char[][] board =
                {{'5','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(board));
        System.out.println(isValidSudokuArrayFixedLength(board));
    }

    //Approach 1: Hash Set
    /*
    Time complexity: O(N²) -> traverse array of 2 dimensions.
    Space complexity: O(n²) if the board is full, need same space as the board.
     */
    static boolean isValidSudoku(char[][] board) {
        int N = 9;
        //Arrays with each element is a Set which contained Character
        Set<Character>[] rows = new HashSet[N];
        Set<Character>[] cols = new HashSet[N];
        Set<Character>[] boxes = new HashSet[N];
        for(int r = 0; r < N; r++) {
            rows[r] = new HashSet<>();
            cols[r] = new HashSet<>();
            boxes[r] = new HashSet<>(); // 0 to 8 boxes (total of 9)
        }
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                char val = board[r][c];
                if(val == '.')
                    continue;
                //Check row
                if(rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);
                if(cols[r].contains(val)) {
                    return false;
                }
                cols[r].add(val);
                //Check box
                /*
                Alternatively, we can use the numbers 0 through 8 to represent these boxes,
                 where (r/3) * 3 + (c/3) is used calculate
                 a number in the range from 0 to 8. I.e. the square
                 located at (r, c) belongs to the box (r/3) * 3 + (c/3).
                 */
                int idx = (r / 3) * 3 + c / 3;
                if(boxes[idx].contains(val)) {
                    return false;
                }
                boxes[idx].add(val);
            }
        }
        return true;
    }

    //Approach 2 : Array of Fixed Length
    /*
    Time: O(N²), we need to traverse every position in the board
    Space: O(N²), cause we need to create 3N arrays each
     */
    static boolean isValidSudokuArrayFixedLength(char[][] board) {
        int N = 9;
        int[][] rows = new int[N][N];
        int[][] cols = new int[N][N];
        int[][] boxes = new int[N][N];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(board[r][c] == '.')
                    continue;
                int pos = board[r][c] - '1'; // 5 becomes 4
                if(rows[r][pos] == 1)
                    return false;
                rows[r][pos] = 1;
                if(cols[c][pos] == 1)
                    return false;
                cols[c][pos] = 1;
                //check boxes
                int idx = (r / 3) * 3 + c / 3;
                if(boxes[idx][pos] == 1) {
                    return false;
                }
                boxes[idx][pos] = 1;
            }
        }
        return true;
    }

}
