package topInterviewQuestion.facebook.treesAndGraphs;

import java.util.Stack;

//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/544/
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    public static void main(String[] args) {
        Node four = new Node(4);
        four.left = new Node(2);
        four.left.left = new Node(1);
        four.left.right = new Node(3);
        four.right = new Node(5);
        var obj = new ConvertBinarySearchTreeToSortedDoublyLinkedList();
        Node res = obj.treeToDoublyDivideIterative(four);

    }

    //Approach 2 : Iterative
    //Inorder Traversal approach -> Left, Node, Right
    /*
    Time: O(N)
    Space: O(N)
     */
    public Node treeToDoublyDivideIterative(Node root) {
        if(root == null)
            return root;
        Node dummy = new Node(0);
        Node prev = dummy;
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left; //Put of the left node to the stack from top to bottom
            }
            curr = stack.pop();
            prev.right = curr;
            curr.left = prev;
            prev = curr;
            curr = curr.right; //If right side is null, we are going to get the next node above (stored in stack)
        }
        dummy.right.left = prev;
        prev.right = dummy.right;
        return dummy.right;
    }


        //Approach 3 : Divide and conquer without Dummy Node
    /*
    https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/154659/Divide-and-Conquer-without-Dummy-Node-Java-Solution
    Step 1: Divide:
    We divide tree into three parts: left subtree, root node, right subtree.
    Convert left subtree into a circular doubly linked list as well as the right subtree.
    Be careful. You have to make the root node become a circular doubly linked list

    Step 2: Conquer:
    Firstly, connect left circular doubly linked list with the root circular doubly linked list.
    Secondly, connect them with the right circular doubly linked list. Here we go!
     */
    public Node treeToDoublyDivideAndConquer(Node root) {
        if(root == null) {
            return null;
        }
        Node leftHead = treeToDoublyDivideAndConquer(root.left);
        Node rightHead = treeToDoublyDivideAndConquer(root.right);
        root.left = root;
        root.right = root;
        return connect(connect(leftHead, root), rightHead);
    }
    // Used to connect two circular doubly linked lists. n1 is the head of circular DLL as well as n2.
    private Node connect(Node n1, Node n2) {
        if(n1 == null) {
            return n2;
        }
        if(n2 == null) {
            return n1;
        }
        Node tail1 = n1.left;
        Node tail2 = n2.left;

        tail1.right = n2;
        n2.left = tail1;
        tail2.right = n1;
        n1.left = tail2;

        return n1;
    }

    /*****************************************/

    //Approach 1 : Recursion
    /*
    Here the problem is to implement DFS inorder traversal in a textbook recursion way because of in-place requirement.
    left -> node -> right
     */
    // the smallest (first) and the largest (last) nodes
    /*
    Time: O(N)
    Space: O(N)
     */
    Node first = null;
    Node last = null;

    public void helper(Node node) {
        if (node != null) {
            // left
            helper(node.left);
            // node
            if (last != null) {
                // link the previous node (last)
                // with the current one (node)
                last.right = node;
                node.left = last;
            } else {
                // keep the smallest node
                // to close DLL later on
                first = node;
            }
            last = node;
            // right
            helper(node.right);
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
    }

}
