package patternsForCodingInterviews.subsets;

import java.util.HashMap;
import java.util.Map;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744103124_75Unit
public class _3CountOfStructurallyUniqueBinarySearchTrees {
    //Explanation: https://www.youtube.com/watch?v=Ox0TenN3Zpg
    public static void main(String[] args) {
        var ct = new _3CountOfStructurallyUniqueBinarySearchTrees();
        int count = ct.countTrees(3);
        System.out.print("Total trees: \n" + count);
    }

    //Memoization
    Map<Integer, Integer> map = new HashMap<>();

    /*
    Time: O(n²)
    Space: O(n)
     */
    public int countTrees(int n) {
        if(map.containsKey(n)) {
            return map.get(n);
        }
        if (n <= 1)
            return 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // making 'i' root of the tree
            int countOfLeftSubtrees = countTrees(i - 1);
            int countOfRightSubtrees = countTrees(n - i);
            count += (countOfLeftSubtrees * countOfRightSubtrees);
        }
        map.put(n, count);
        return count;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}
