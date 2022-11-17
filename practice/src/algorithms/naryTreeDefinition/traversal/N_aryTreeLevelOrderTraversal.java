package algorithms.naryTreeDefinition.traversal;
//https://leetcode.com/explore/learn/card/n-ary-tree/130/traversal/915/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Inoder traversal -> Node, left, right
//A->B->C->D->E->F->G.
public class N_aryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Node one = new Node(1);
        Node three = new Node(3);
        Node two = new Node(2);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        one.children.addAll(List.of(three, two, four));
        three.children.addAll(List.of(five, six));
        for (List<Integer> el : inorderRecursive(one)) {
            System.out.print(el);
        }
        System.out.println();
        for (List<Integer> el : inorderIterative(one)) {
            System.out.print(el);
        }
    }

    //Iterative
    static List<List<Integer>> inorderIterative(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                //queue.addAll(node.children); //Equivalent below
                for(Node n : node.children) {
                    queue.add(n);
                }
            }
            result.add(level);
        }
        return result;
    }

    //Recursive
    static List<List<Integer>> result = new ArrayList<>();
    static List<List<Integer>> inorderRecursive(Node root) {
        if(root != null)
            traverseNode(root, 0);
        return result;
    }
    private static void traverseNode(Node node, int level) {
        if(result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.val);
        for(Node child : node.children) {
            traverseNode(child, level + 1);
        }
    }

}
