package topInterviewQuestion.top100likedQuestions;

import patternsForCodingInterviews.treeBFS.TreeNode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/path-sum-iii/
public class PathSumIII {

    public static void main(String[] args) {
        TreeNode ten = new TreeNode(10);
        ten.left = new TreeNode(5);
        ten.left.left = new TreeNode(3);
        ten.left.left.left = new TreeNode(3);
        ten.left.left.right = new TreeNode(-2);
        ten.left.right = new TreeNode(2);
        ten.left.right.right = new TreeNode(1);
        ten.right = new TreeNode(-3);
        ten.right.right = new TreeNode(11);
        System.out.println(pathSum(ten, 8));
        System.out.println(pathSumDFS(ten, 8));
    }

    //Approach 1 : Prefix Sum
    /*
    Time: O(N) where N is a number of nodes
    Space: O(N) to keep the hashmap of prefix sum where N is a number of nodes
     */
    /** {@link algorithms.prefixSum.PrefixSum} */
    /** {@link patternsForCodingInterviews.twoPointers.PairWithTargetSum#searchWithHashMap(int[], int)} */
    static int k;
    static int count = 0;
    static Map<Long, Integer> h = new HashMap<>();
    static int pathSum(TreeNode root, int sum) {
        k = sum;
        preorder(root, 0L);
        return count;
    }
    //Preorder : Node -> Left -> Right
    private static void preorder(TreeNode node, long currSum) {
        if(node == null) {
            return;
        }
        //current prefixSum
        currSum += node.val;
        // number of times the curr_sum − k has occured already,
        // determines the number of times a path with sum k
        // has occured upto the current node
        count += h.getOrDefault(currSum - k, 0);
        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);
        //process left tree
        preorder(node.left, currSum);
        //process right
        preorder(node.right, currSum);
        // remove the current sum from the hashmap
        // in order not to use it during
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }

    //Approach DFS: Kind of brut force, not efficient
    /*
    Time: O(n²), no branching, best case: O(n log n) best case for balanced tree
    Space: O(n) due to recursive
     */
    static int pathSumDFS(TreeNode root, int sum) {
        if(root == null) return 0;
        return helper(root, sum) +  //Current one
                pathSumDFS(root.left, sum) + //Left
                pathSumDFS(root.right, sum); //Right
    }
    private static int helper(TreeNode node, int sum) {
        if(node == null)
            return 0;
        if(sum < 0)
            return 0;
        if(node.val == sum)
            return 1;
        return helper(node.left, sum - node.val) + helper(node.right, sum - node.val);
    }

}
