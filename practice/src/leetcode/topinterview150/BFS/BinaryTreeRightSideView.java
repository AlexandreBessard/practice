package leetcode.topinterview150.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    //https://leetcode.com/problems/binary-tree-right-side-view/?envType=study-plan-v2&envId=top-interview-150

    public static void main(String[] args) {
        var one = new TreeNode(1);
        var two = new TreeNode(2);
        var five = new TreeNode(5);
        var three = new TreeNode(3);
        var four = new TreeNode(4);
        one.left = two;
        one.left.right = five;
        one.right = three;
        one.right.right = four;
        for (Integer el : rightSideView(one)) {
            System.out.print(el + ", ");
        }
    }

    /*
    Time: O(n)
    Space: O(m)
     */
    static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // if it is the last node of this level, add it to the result
                if (i == levelSize - 1) { // index-based 0 so remove 1
                    result.add(currentNode.val);
                }
                // Insert children node
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }
        return result;
    }

}
