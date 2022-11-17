package patternsForCodingInterviews.treeDFS;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743939828_56Unit
public class SumOfPathNumbers {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers(root));

        System.out.println(findRootToLeafPathNumbersStr(root));
    }

    //Using StringBuilder
    /*
    Time complexity: O(N) where N is the total number of nodes in the tree.
    Space complexity: O(N) Space for recursion stack.
     */
    private static int findRootToLeafPathNumbersStr(TreeNode root) {
        int result = 0;
        return helper(root, new StringBuilder(), result);
    }

    private static int helper(TreeNode node,
                              StringBuilder path,
                              int result) {
        if (node == null) {
            return 0;
        }
        //Add to the path
        path.append(node.val);
        //It is a leaf node
        if (node.left == null && node.right == null) {
            return Integer.parseInt(path.toString());
        }
        result = helper(node.left, path, result);
        //Remove the latest String number format
        path.deleteCharAt(path.length() - 1);
        result += helper(node.right, path, result);
        //Return total of that path including left and right paths
        return result;
    }

    //--------------------------------------------

    public static int findSumOfPathNumbers(TreeNode root) {
        return findRootToLeafPathNumbers(root, 0);
    }

    /*
    Time complexity: O(N) where N is the total number of nodes in the tree.
    Space complexity: O(N) Space for recursion stack.
     */
    private static int findRootToLeafPathNumbers(TreeNode current, int pathSum) {
        if (current == null) {
            return 0;
        }
        //See below for alternative solution by using StringBuilder
        pathSum = 10 * pathSum + current.val;
        if (current.left == null && current.right == null)
            return pathSum;
        //Traverse left and right
        return findRootToLeafPathNumbers(current.left, pathSum)
                + findRootToLeafPathNumbers(current.right, pathSum);
    }

}
