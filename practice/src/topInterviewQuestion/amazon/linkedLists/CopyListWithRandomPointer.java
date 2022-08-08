package topInterviewQuestion.amazon.linkedLists;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    public static void main(String[] args) {
        Node seven = new Node(7);
        Node thirteen = new Node(13);
        Node eleven = new Node(11);
        Node ten = new Node(10);
        Node one = new Node(1);
        seven.next = thirteen;
        seven.random = null;
        thirteen.next = eleven;
        thirteen.random = seven;
        eleven.next = ten;
        eleven.random = one;
        ten.next = one;
        ten.random = eleven;
        one.next = null;
        one.random = seven;
        var obj = new CopyListWithRandomPointer();
        Node result = obj.copyRandomListRecursive(seven);
    }

    //Approach 3: Iterative with O(1) space
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public Node copyRandomListIterativeSpaceReduced(Node head) {
        if(head == null) {
            return null;
        }
        //Creating new weaved list of originals and copied nodes.
        Node ptr = head;
        while(ptr != null) {
            //Cloned node
            Node newNode = new Node(ptr.val);
            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }
        ptr = head;
        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while(ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }
        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while(ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }

    //Approach 2: Iterative with O(N) Space
    /*
    Time complexity: O(N)
    Space complexity: O(N)
     */
    // Visited dictionary to hold old node reference as "key" and new node reference as the "value"
    Map<Node, Node> visited = new HashMap<>();
    public Node copyRandomListIterative(Node head) {
        if(head == null) {
            return null;
        }
        Node oldNode = head;
        //Creating the new head node
        Node newNode = new Node(oldNode.val);
        this.visited.put(oldNode, newNode);
        //Iterate on linkedList
        while(oldNode != null) {
            //Get the clones of the nodes references by random and next pointers
            newNode.random = this.getCloneNode(oldNode.random);
            newNode.next = this.getCloneNode(oldNode.next);
            //Move on to the next node
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return this.visited.get(head);
    }
    private Node getCloneNode(Node node) {
        if(node != null) {
            //Check if node is in visited dictionary
            if(this.visited.containsKey(node)) {
                return this.visited.get(node);
            } else {
                //Create a new node
                this.visited.put(node, new Node(node.val, null, null));
                return this.visited.get(node);
            }
        }
        return null;
    }


    //Approach 1: Recursive
    /*
    Construct a deep copy of the list. The deep copy should consist of
    exactly n brand new nodes
     */
    //Old nodes as keys and new nodes as its value.
    /*
    Time complexity: O(N)
    Space complexity: stack (recursive call) and hashMap
     */
    Map<Node, Node> visitedHash = new HashMap<>();
    public Node copyRandomListRecursive(Node head) {
        if(head == null) {
            return null;
        }
        //If already processed the current node, simply return the cloned version of it
        if(this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }
        //Create a new node
        Node node = new Node(head.val, null, null);
        //Save this value in hashMap, needed loops during traversal due to randomness
        // of random pointers
        this.visitedHash.put(head, node);
        //Recursively copy the remaining linkedList
        node.next = copyRandomListRecursive(head.next);
        node.random = copyRandomListRecursive(head.random);
        return node;
    }

}
