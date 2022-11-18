package leetcode.treesAndGraphs;

import topInterviewQuestion.medium.treesAndGraphs.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/787/
public class BinaryTreeZigzagLevelOrder {
/*
Given the root of a binary tree, return the zigzag level order traversal
of its nodes' values.
(i.e., from left to right, then right to left for the next level
and alternate between).
 */
    public static void main(String[] args) {

    }

    //BFS approach
    /*
    Time complexity: O(n)
    Space complexity: 0(n)
     */
    static List<List<Integer>> zigzagLevelOrderBFS(topInterviewQuestion.medium.treesAndGraphs.TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> results = new ArrayList<>();
        //Used to append new element to the end and retrieved element from the beginning
        LinkedList<topInterviewQuestion.medium.treesAndGraphs.TreeNode> node_queue = new LinkedList<>();
        node_queue.addLast(root); // 3
        node_queue.addLast(null); // 3, null,  is used to differentiate each level
        LinkedList<Integer> level_list = new LinkedList<>();
        boolean is_order_left = true;
        while (node_queue.size() > 0) {
            topInterviewQuestion.medium.treesAndGraphs.TreeNode curr_node = node_queue.pollFirst();
            if (curr_node != null) {
                if (is_order_left)
                    level_list.addLast(curr_node.val);
                else {
                    level_list.addFirst(curr_node.val);
                }
                if (curr_node.left != null)
                    node_queue.addLast(curr_node.left);
                if (curr_node.right != null)
                    node_queue.addLast(curr_node.right);
            } else { //We finished with that level, add to the final result
                results.add(level_list);
                level_list = new LinkedList<>();
                if (node_queue.size() > 0) {
                    node_queue.addLast(null); //append to the end
                }
                is_order_left = !is_order_left;
            }
        }
        return results;
    }

    //DSF
    /*
    Time complexity: O(n)
    Space complexity: O(n)
    If tree balanced: O(log n) (better space complexity than BFS)
     */
    static List<List<Integer>> zigzagLevelOrderDFS(topInterviewQuestion.medium.treesAndGraphs.TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> results = new ArrayList<>();
        DFS(root, 0, results);
        return results;
    }

    private static void DFS(TreeNode node, int level, List<List<Integer>> results) {
        if (level >= results.size()) {
            LinkedList<Integer> newLevel = new LinkedList<>();
            newLevel.add(node.val);
            results.add(newLevel);
        } else {
            if (level % 2 == 0) {
                results.get(level).add(node.val);
            } else {
                results.get(level).add(0, node.val);
            }
        }
        if (node.left != null)
            DFS(node.left, level + 1, results);
        if (node.right != null)
            DFS(node.right, level + 1, results);

    }

}
