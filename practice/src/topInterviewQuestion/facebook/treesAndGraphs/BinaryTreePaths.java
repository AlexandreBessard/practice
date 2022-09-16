package topInterviewQuestion.facebook.treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/280/
public class BinaryTreePaths {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        one.left = new TreeNode(2);
        one.left.right = new TreeNode(5);
        one.right = new TreeNode(3);

        var obj = new BinaryTreePaths();
        System.out.println(obj.binaryTreePathsRecursive(one));
    }

    //Approach 1: Recursive
    //https://leetcode.com/problems/binary-tree-paths/discuss/68258/Accepted-Java-simple-solution-in-8-lines
    /*
    Time: O(N)
    Space: O(N)
    In the worst case, when the tree is completely unbalanced,
    e.g. each node has only one child node, the recursion call would occur N
     times (the height of the tree), therefore the storage to keep the call stack would be
     O(N). But in the best case (the tree is balanced),
     the height of the tree would be log(N). Therefore,
     the space complexity in this case would be O(log(N)).
     */
    public List<String> binaryTreePathsRecursive(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, root, sb);
        return res;
    }

    private void helper(List<String> res, TreeNode root, StringBuilder sb) {
        if(root == null) {
            return;
        }
        int len = sb.length();
        sb.append(root.val);
        if(root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            helper(res, root.left, sb);
            helper(res, root.right, sb);
        }
        sb.setLength(len);
    }


    private static final String RIGHT_ARROW = "->";
    //Approach 2: Iterative
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        if(root == null)
            return paths;
        LinkedList<TreeNode> node_stack = new LinkedList<>();
        LinkedList<String> path_stack = new LinkedList<>();
        node_stack.add(root);
        path_stack.add(Integer.toString(root.val));
        TreeNode node;
        String path;
        while(!node_stack.isEmpty()) {
            node = node_stack.pollLast();
            path = path_stack.pollLast();
            if(node.left == null && node.right == null) { //leaf node. we add path to the the result.
                paths.add(path);
            }
            StringBuilder stringBuilder;
            if(node.left != null) {
                node_stack.add(node.left);
                stringBuilder = new StringBuilder(path);
                path_stack.add(stringBuilder.append(RIGHT_ARROW).append(node.left.val).toString());
            }
            if(node.right != null) {
                node_stack.add(node.right);
                stringBuilder = new StringBuilder(path);
                path_stack.add(stringBuilder.append(RIGHT_ARROW).append(node.right.val).toString());
            }
        }
        return paths;
    }





}
