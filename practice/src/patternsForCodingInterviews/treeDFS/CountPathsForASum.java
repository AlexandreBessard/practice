package patternsForCodingInterviews.treeDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CountPathsForASum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + countPaths(root, 11));
    }
    /*
    Time complexity: O(N²)
    Traverse each node once but for every node, we iterate the current path.
    If Tree balanced, the current path will be equal to the height of the tree: O(log N) so it is O(N logN)
    Space complexity: O(N)
     */
    public static int countPaths(TreeNode root, int S) {
        List<Integer> currentPath = new LinkedList<>();
        return countPathsRecursive(root, S, currentPath);
    }
    private static int countPathsRecursive(TreeNode currentNode, int S, List<Integer> currentPath) {
        if(currentNode == null)
            return 0;
        //add node to the path
        currentPath.add(currentNode.val);
        int pathCount = 0, pathSum = 0;
        //Find the sums of all sub-paths in the current path list
        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        while(pathIterator.hasPrevious()) {
            pathSum += pathIterator.previous();
            //If the sum of any sub-path is equal to 'S' we increment our path count
            if(pathSum == S) {
                pathCount++;
            }
        }
        //Traverse the left sub-tree
        pathCount += countPathsRecursive(currentNode.left, S, currentPath);
        pathCount += countPathsRecursive(currentNode.right, S, currentPath);
        //Remove the current node from the path to backtrack
        currentPath.remove(currentPath.size() - 1);
        return pathCount;
    }


}
