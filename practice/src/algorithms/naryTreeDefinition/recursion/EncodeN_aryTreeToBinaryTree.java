package algorithms.naryTreeDefinition.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/explore/learn/card/n-ary-tree/131/recursion/920/
public class EncodeN_aryTreeToBinaryTree {

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        var codec = new Codec();
        codec.encode(one);

    }

    static class Codec {
        //Encodes an n-ary tree to binary tree
        //BFS
        /*<<
        Time: O(N)
        Space: O(N)
         */
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode newRoot = new TreeNode(root.val);
            Pair<TreeNode, Node> head = new Pair<TreeNode, Node>(newRoot, root);
            // Add the first element to kickoff the loop
            Queue<Pair<TreeNode, Node>> queue = new LinkedList<>();
            queue.add(head);
            while (queue.size() > 0) {
                Pair<TreeNode, Node> pair = queue.remove();
                TreeNode bNode = pair.key;
                Node nNode = pair.value;
                // Encoding the children nodes into a list of TreeNode.
                TreeNode prevBNode = null, headBNode = null;
                for (Node nChild : nNode.children) {
                    TreeNode newBNode = new TreeNode(nChild.val);
                    if (prevBNode == null) {
                        headBNode = newBNode; //Initialise to become the new head of that level and stay as is
                    } else {
                        prevBNode.right = newBNode;
                    }
                    prevBNode = newBNode; //prev pointer go forward to link the next node from the queue

                    Pair<TreeNode, Node> nextEntry = new Pair<TreeNode, Node>(newBNode, nChild);
                    queue.add(nextEntry);
                }
                // Attach the list of children to the left node.
                bNode.left = headBNode; //link old head to the new head
            }
            return newRoot;
        }

        //Decode binary tree to n-ary tree
        //BFS
        /*
        Time: O(n)
        Space: O(L) where L is the max number of nodes that reside at the same level.
         */
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            Node newRoot = new Node(root.val, new ArrayList<Node>());
            // adding the first element to kickoff the loop
            Queue<Pair<Node, TreeNode>> queue = new LinkedList<>();
            Pair<Node, TreeNode> head = new Pair<Node, TreeNode>(newRoot, root);
            queue.add(head);
            while (queue.size() > 0) {
                Pair<Node, TreeNode> entry = queue.remove();
                Node nNode = entry.key;
                TreeNode bNode = entry.value;
                // Decoding the children list
                TreeNode firstChild = bNode.left;
                TreeNode sibling = firstChild;
                while (sibling != null) {
                    Node nChild = new Node(sibling.val, new ArrayList<Node>());
                    nNode.children.add(nChild);

                    // prepare the decoding the children of the child, by standing in the queue.
                    Pair<Node, TreeNode> nextEntry = new Pair<Node, TreeNode>(nChild, sibling);
                    queue.add(nextEntry);
                    sibling = sibling.right;
                }
            }
            return newRoot;
        }
    }

    static class Pair<K, V> {
        K key;
        V value;
        Pair(K k, V v) {
            key = k;
            value = v;
        }
    }

    static class Node {
        int val;
        List<Node> children;
        Node(int val) {
            this.val = val;
        }
        Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

}
