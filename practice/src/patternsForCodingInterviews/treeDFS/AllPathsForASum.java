package patternsForCodingInterviews.treeDFS;

import java.util.ArrayList;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743930963_55Unit
public class AllPathsForASum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }

    /*
    Time complexity: O(N²), N total number in the tree.
    We traverse each node once.
    We might have to store its path: O(N)
    Space complexity: O(N)
     */
    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathsRecursive(root, sum, currentPath, allPaths);
        return allPaths;
    }
    private static void findPathsRecursive(TreeNode currentNode, int sum,
                                           List<Integer> currentPath,
                                           List<List<Integer>> allPaths)
    {
        //Base case 1
        if(currentNode == null) {
            return;
        }
        //Add the current node to the path
        currentPath.add(currentNode.val);
        //Base case 2
        if(currentNode.val == sum && currentNode.left == null && currentNode.right == null)
        {
            allPaths.add(new ArrayList<>(currentPath));
        }
        else
        {
            //Left side
            findPathsRecursive(currentNode.left,
                    sum - currentNode.val,
                    currentPath,
                    allPaths);
            //Right side
            findPathsRecursive(currentNode.right,
                    sum - currentNode.val,
                    currentPath,
                    allPaths);
        }
        //Remove to backtrack
        currentPath.remove(currentPath.size() - 1);
    }


}
