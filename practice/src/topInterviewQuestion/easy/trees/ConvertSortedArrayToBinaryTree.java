package topInterviewQuestion.easy.trees;

import java.util.Random;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/631/
public class ConvertSortedArrayToBinaryTree {

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        System.out.println(sortedArrayPreorderRecursive(nums));
        System.out.println(sortedArrayToBST(nums));
    }

    //Approach 2: Preorder Traversal: Always Choose right Middle Node as a Root
    //[-10, -3, 0, 5, 9]
    //       - mid    -
    static TreeNode sortedArrayToBSTRightMiddleNodeAsRoot(int[] nums) {
        ConvertSortedArrayToBinaryTree.nums = nums;
        return helperSortedArrayToBST(0, nums.length - 1);
    }
    private static TreeNode helperSortedArrayToBST(int left, int right) {
        if(left > right)
            return null;
        //Choose middle node as root node
        int mid = left + (right - left) / 2;
        if((left + right) % 2 == 1)
            mid++;
        //Preorder Traversal
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helperSortedArrayToBST(left, mid - 1);
        root.right = helperSortedArrayToBST(mid + 1, right);
        return root;
    }


    //Approach 3: Preorder Traversal, Choose Random middle Node as a root
    static Random rand = new Random();
    static TreeNode sortedArrayToBST(int[] nums) {
        ConvertSortedArrayToBinaryTree.nums = nums;
        return helperv2(0, nums.length - 1);
    }
    static TreeNode helperv2(int left, int right) {
        if(left > right)
            return null;

        // always choose right middle node as a root
        /*
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1) ++p;
        */

        int p = (left + right) / 2;
        if((left + right) % 2 == 1)
            p += rand.nextInt(2);
        //Preorder Traversal
        TreeNode root = new TreeNode(nums[p]);
        root.left = helperv2(left, p -1);
        root.right = helperv2(p + 1, right);
        return root;
    }


    static int[] nums;
    //Approach 1: Preorder traversal recursive : always choose left middle as a root
    //Choose lieft middle Node as Root
    //Preorder -> Node, Left, Right
    /*
    Time complexity: O(N)
    Space complexity: O(log(N)) because the tree is height-balanced
     */
    static TreeNode sortedArrayPreorderRecursive(int[] nums) {
        ConvertSortedArrayToBinaryTree.nums = nums;
        return helper(0, nums.length  - 1);
    }
    private static TreeNode helper(int left, int right) {
        if(left > right)
            return null;
        //Choose middle
        int mid = left - (right + left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(left, mid - 1);
        root.right = helper(mid + 1, right);
        return root;
    }

}
