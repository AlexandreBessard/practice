package patternsForCodingInterviews.treeDFS;
//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743947562_57Unit
public class PathWithGivenSequence {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path sequence: " +
                PathWithGivenSequence.findPath(root, new int[] { 1, 1, 6 }));
    }


    /*
    Time complexity: O(N) where N is the number of nodes in the tree.
    Space complexity: O(N) to store the recursion stack.
     */
    public static boolean findPath(TreeNode root, int[] sequence) {
        if(root == null) {
            return sequence.length == 0;
        }
        //sequenceIdx used for our char[] array
        return findPathRecursive(root, sequence, 0);
    }

    private static boolean findPathRecursive(TreeNode currentNode, int[] sequence, int sequenceIndex) {
        //Base case 1
        if(currentNode == null) {
            return false;
        }
        //Check if index is out of bound OR does not have the same value as the sequence
        if(sequenceIndex >= sequence.length || currentNode.val != sequence[sequenceIndex])
        {
            return false;
        }
        if(currentNode.left == null && currentNode.right == null //means we are at a leaf node
                && sequenceIndex == sequence.length - 1) // we reached the end of our sequence array
        {
            return true;
        }
        return findPathRecursive(currentNode.left, sequence, sequenceIndex + 1)
                || findPathRecursive(currentNode.right, sequence, sequenceIndex + 1);
    }


}
