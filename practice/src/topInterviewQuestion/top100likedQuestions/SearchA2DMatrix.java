package topInterviewQuestion.top100likedQuestions;
//https://leetcode.com/problems/search-a-2d-matrix/
public class SearchA2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        System.out.println(searchMatrix(matrix, 3));
    }

    //Approach 1: Binary Search
    /*
    Time: O(log(mn))
    Space: O(1)
     */
    static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) {
            return false;
        }
        int n = matrix[0].length;
        //Binary search
        int left = 0, right = m * n - 1; //Can be represented as a 1D array
        int midIdx, midElement;
        while(left <= right) {
            midIdx = left + (right - left) / 2; //get the mid
            midElement = matrix[midIdx / n][midIdx % n];
            if(target == midElement) {
                return true;
            } else {
                if(target < midElement) {
                    right = midIdx - 1;
                } else {
                    left = midIdx + 1;
                }
            }
        }
        return false;
    }

}
