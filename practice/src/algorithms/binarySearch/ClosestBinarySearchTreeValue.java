package algorithms.binarySearch;
//https://leetcode.com/explore/learn/card/binary-search/136/template-analysis/1028/
public class ClosestBinarySearchTreeValue {

    public static void main(String[] args) {
        double target = 3.714286;
        TreeNode four = new TreeNode(4);
        four.left = new TreeNode(2);
        four.right = new TreeNode(5);
        four.left.left = new TreeNode(1);
        four.left.right = new TreeNode(3);
        System.out.println(closestValue(four, 2));
    }

    //Binary Search
    public static int closestValue(TreeNode root, double target) {
        int val, closest = root.val;
        while (root != null) {
            val = root.val;
            closest = Math.abs(val - target) < Math.abs(closest - target)
                    ? val
                    : closest;
            root =  target < root.val ? root.left : root.right;
        }
        return closest;
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
