package topInterviewQuestion.facebook.topMostFrequentQuestions;

import patternsForCodingInterviews.treeBFS.TreeNode;

import java.util.*;

//https://leetcode.com/problems/sum-root-to-leaf-numbers/
public class SumRootToLeafNumbers {

    public static void main(String[] args) {
        TreeNode four = new TreeNode(4);
        four.left = new TreeNode(9);
        //four.left.left = new TreeNode(5);
        four.left.right = new TreeNode(1);
        four.right = new TreeNode(0);
        //Output: 1026
        System.out.println(sumNumbersDFS(four));
        System.out.println(sumNumbersBFS(four));
    }

    //BFS
    static int sumNumbersBFS(TreeNode root) {
        int rootToLeaf = 0;
        int currentNumber = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.key; //Can not caused a NullPointerException because everytime we add a new created Pair object.
            currentNumber = p.val;
            if (root != null) {
                currentNumber = currentNumber * 10 + root.val;
                // if it's a leaf, update root-to-leaf sum
                if (root.left == null && root.right == null) {
                    rootToLeaf += currentNumber;
                } else {
                    queue.add(new Pair<>(root.left, currentNumber));
                    queue.add(new Pair<>(root.right, currentNumber));
                }
            }
        }
        return rootToLeaf;
    }

    static class Pair<K, V> {
        K key;
        V val;

        Pair(K k, V v) {
            key = k;
            val = v;
        }
    }

    //DFS
    static int rootToLeaf = 0;

    static int sumNumbersDFS(TreeNode root) {
        //Preorder: Node -> Left -> Right
        preorder(root, 0);
        return rootToLeaf;
    }

    static void preorder(TreeNode node, int curentNumber) {
        if (node != null) {
            curentNumber = curentNumber * 10 + node.val;
            if (node.left == null && node.right == null) {
                rootToLeaf += curentNumber;
            }
            preorder(node.left, curentNumber);
            preorder(node.right, curentNumber);
        }
    }


}
