package topInterviewQuestion.easy.others;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/601/
public class PascalsTriangle {

    public static void main(String[] args) {
        generate(5);
    }

    /*
    Time complexity: O(N²) (2 loops)
    Space complexity: 0(1), the input and output does not count towards the space complexity
     */
    static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        //Base case:
        //First row is always 1
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);
        for(int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum - 1);
            //The first row elements always 1
            row.add(1);
            for(int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }
}
