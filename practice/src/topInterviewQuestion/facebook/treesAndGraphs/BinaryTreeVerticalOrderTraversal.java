package topInterviewQuestion.facebook.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/298/
public class BinaryTreeVerticalOrderTraversal {

    public static void main(String[] args) {
        TreeNode three = new TreeNode(3);
        three.left = new TreeNode(9);
        three.left.left = new TreeNode(4);
        three.left.right = new TreeNode(0);
        three.left.right.left = new TreeNode(5);
        three.right = new TreeNode(8);
        three.right.left = new TreeNode(1);
        three.right.left.right = new TreeNode(2);
        three.right.right = new TreeNode(7);
        var obj = new BinaryTreeVerticalOrderTraversal();
        var res = obj.verticalOrderWithoutSorting(three);
        for (var o : res) {
            System.out.println(o);
        }
    }

    //Approach 2 : BFS without sorting (Optimized)
    /*
    Time: O(N)
    Space: O(N)
     */
    public List<List<Integer>> verticalOrderWithoutSorting(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        //Key: number of column, Value: each node's value associated to this column
        Map<Integer, List<Integer>> columnTable = new HashMap<>();
        // Pair of node and its column offset
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        int column = 0;
        queue.offer(new Pair<>(root, column));
        int minColumn = 0, maxColumn = 0;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.root;
            column = p.column;
            if (root != null) {
                if (!columnTable.containsKey(column)) {
                    columnTable.put(column, new ArrayList<Integer>());
                }
                columnTable.get(column).add(root.val);
                minColumn = Math.min(minColumn, column);
                maxColumn = Math.max(maxColumn, column);
                queue.offer(new Pair<>(root.left, column - 1));
                queue.offer(new Pair<>(root.right, column + 1));
            }
        }
        for (int i = minColumn; i < maxColumn + 1; ++i) { //Avoid to sort by fetching the corresponding column via array's index directly
            output.add(columnTable.get(i));
        }
        return output;
    }


    //Approach 1 : BFS
    /*
    Time: O(N log N) where N is the number of nodes in the tree
    In the second part, in order to return the ordered results, we then sort the obtained hash table by its keys,
    which could result in the O(NlogN) time complexity in the worst case scenario where the binary tree is extremely
    imbalanced (for instance, each node has only left child node.)
    Space: O(N)
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null)
            return null;
        Map<Integer, List<Integer>> columnTable = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        int column = 0;
        queue.offer(new Pair<>(root, column));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.root;
            column = p.column;
            if (root != null) {
                if (!columnTable.containsKey(column)) {
                    columnTable.put(column, new ArrayList<>());
                }
                columnTable.get(column).add(root.val);
                queue.offer(new Pair<>(root.left, column - 1));
                queue.offer(new Pair<>(root.right, column + 1));
            }
        }
        List<Integer> sortedKeys = new ArrayList<>(columnTable.keySet());
        Collections.sort(sortedKeys);
        for (int k : sortedKeys) {
            output.add(columnTable.get(k));
        }
        return output;
    }


    static class Pair<T, U> {
        T root;
        U column;
        Pair(T root, U column) {
            this.root = root;
            this.column = column;
        }
    }

    /*
    static class Pair {
        public static <T, U> Map.Entry<T, U> of(T first, U second) {
            return new AbstractMap.SimpleEntry<>(first, second);
        }
    }

     */

}
