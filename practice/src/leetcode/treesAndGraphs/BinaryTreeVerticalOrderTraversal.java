package leetcode.treesAndGraphs;

import leetcode.treesAndGraphs.helper.ConstructBinaryTree;
import leetcode.treesAndGraphs.helper.TreeNode;

import java.util.*;

//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/298/
public class BinaryTreeVerticalOrderTraversal {

    public static void main(String[] args) {
        Integer[] nums = {3, 9, 20, null, null, 15, 7};
        TreeNode tree = ConstructBinaryTree.constructTreeNode(nums);
        var obj = new BinaryTreeVerticalOrderTraversal();
        var res = obj.verticalOrderWithoutSorting(tree);
        for (var o : res) {
            if (o.size() == 1 && o.get(0) == null) {  //True if we have unique null element from this list
                continue;
            }
            System.out.print("[ ");
            for (Integer el : o) {
                if (el == null) { //If element null, do not display
                    continue;
                }
                System.out.print(el + ", ");
            }
            System.out.println("] \n");
        }
    }

    //Approach 2 : BFS without sorting (Optimized)
    /*
    Time: O(N)
    Space: O(N)
     */
    public List<List<Integer>> verticalOrderWithoutSorting(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
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
            if (!columnTable.containsKey(column)) {
                columnTable.put(column, new ArrayList<>());
            }
            columnTable.put(column, columnTable.getOrDefault(column, new ArrayList<>())); //Initialize first time and skip it
            columnTable.get(column).add(root.val); //Used for our result final
            //Allows you to know the range of all column combined
            minColumn = Math.min(minColumn, column);
            maxColumn = Math.max(maxColumn, column);
            if (root.left != null) {
                queue.offer(new Pair<>(root.left, column - 1));
            }
            if (root.right != null) {
                queue.offer(new Pair<>(root.right, column + 1));
            }
        }
        final List<List<Integer>> output = new ArrayList<>();
        //Loop in ascending order
        for (int i = minColumn; i < maxColumn + 1; ++i) { //Avoid to sort by fetching the corresponding column via array's index directly
            output.add(columnTable.get(i));
        }
        return output;
    }


    //Approach 1 : BFS
    /*
    TreeMap time complexity: O(log(n))
    Time: O(N log N) where N is the number of nodes in the tree
    In the second part, in order to return the ordered results, we then sort the obtained hash table by its keys,
    which could result in the O(NlogN) time complexity in the worst case scenario where the binary tree is extremely
    imbalanced (for instance, each node has only left child node.)
    Space: O(N)
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return null;
        }
        //Key: column, Value: value of the node inside that column
        //TreeMap: keys are sorted in natural order (ascending order)
        TreeMap<Integer, List<Integer>> columnTable = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        int column = 0; //Initialize with column 0
        queue.offer(new Pair<>(root, column)); //head his column is 0, left node is -1 and right is +1
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.root; //get the current node
            column = p.column; //Get the column of this current node
            if (!columnTable.containsKey(column)) {
                columnTable.put(column, new ArrayList<>());
            }
            columnTable.get(column).add(root.val);
            if (root.left != null) {
                queue.offer(new Pair<>(root.left, column - 1)); // left node decrement column by 1
            }
            if (root.right != null) {
                queue.offer(new Pair<>(root.right, column + 1)); //right node increment column by 1
            }
        }
        for (int k : columnTable.keySet()) {
            output.add(columnTable.get(k));
        }
        return output;
    }


    static class Pair<T, U> {
        T root; //TreeNode
        U column; //Num of column where this Node is located

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
