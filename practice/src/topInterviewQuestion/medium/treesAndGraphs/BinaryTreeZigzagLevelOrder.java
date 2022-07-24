package topInterviewQuestion.medium.treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/108/trees-and-graphs/787/
public class BinaryTreeZigzagLevelOrder {

    public static void main(String[] args) {

    }


    //DSF
    /*
    Time complexity: O(n)
    Space complexity: O(n)
    If tree balanced: O(log n) (better space complexity than BFS)
     */
    static List<List<Integer>> zigzagLevelOrderDFS(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();
        DFS(root, 0, results);
        return results;
    }
    private static void DFS(TreeNode node, int level, List<List<Integer>> results) {
        if(level >= results.size()) {
            LinkedList<Integer> newLevel = new LinkedList<>();
            newLevel.add(node.val);
            results.add(newLevel);
        } else {
            if(level % 2 == 0) {
                results.get(level).add(node.val);
            } else {
                results.get(level).add(0, node.val);
            }
        }
            if(node.left != null)
                DFS(node.left, level + 1, results);
            if(node.right != null)
                DFS(node.right, level + 1, results);

     }


        //BFS
    /*
    Time complexity: O(n)
    Space complexity: 0(n)
     */
    static List<List<Integer>> zigzagLevelOrderBFS(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<TreeNode> node_queue = new LinkedList<>();
        node_queue.addLast(root);
        node_queue.addLast(null);
        LinkedList<Integer> level_list = new LinkedList<>();
        boolean is_order_left = true;
        while(node_queue.size() > 0) {
            TreeNode curr_node = node_queue.pollFirst();
            if(curr_node != null) {
                if(is_order_left)
                    level_list.addLast(curr_node.val);
                else {
                    level_list.addFirst(curr_node.val);
                }
                if(curr_node.left != null)
                    node_queue.addLast(curr_node.left);
                if(curr_node.right != null)
                    node_queue.addLast(curr_node.right);
;            } else {
                results.add(level_list);
                level_list = new LinkedList<>();
                if(node_queue.size() > 0) {
                    node_queue.addLast(null);
                }
                is_order_left = !is_order_left;
            }
        }
        return results;
    }

}
