package topInterviewQuestion.easy.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        one.left = new TreeNode(2);
        one.left.left = new TreeNode(3);
        one.left.right = new TreeNode(4);
        one.right = new TreeNode(2);
        one.right.left = new TreeNode(4);
        one.right.right = new TreeNode(3);

        System.out.println(isSymetricIterative(one));

    }

    //Approach 2: Iterative
    /*
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static boolean isSymetricIterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if(t1 == null && t2 == null)
                continue;
            if(t1 == null || t2 == null)
                return false;
            if(t1.val != t2.val)
                return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    //Approach 1: Recursive
    /*
    Time complexity: O(N)
    Space complexity: O(N) -> Recursive call
     */
    static boolean isSymetricRecursive(TreeNode root) {
        return helper(root, root);
    }
    static boolean helper(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null)
            return true;
        if(t1 == null || t2 == null)
            return false;
        if(t1.val != t2.val)
            return false;
        return helper(t1.left, t2.right)
                && helper(t1.right, t2.left);
    }

    //Approach 2: Iterative
    static boolean isSymetricIterative() {
        return false;
    }

}
