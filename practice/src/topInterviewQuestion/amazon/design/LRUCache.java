package topInterviewQuestion.amazon.design;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/amazon/81/design/478/
public class LRUCache {

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    //Space: O(capacity)
    private int capacity;
    private final DLinkedNode head;
    private final DLinkedNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        //Instantiate head and tail
        head = new DLinkedNode();
        tail = new DLinkedNode();
        //Link with head and tails
        head.next = tail;
        tail.prev = head;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private void removeNode(DLinkedNode node) {
        //Remove an existing node from the linkedList
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    //Time: O(1)
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null)
            return -1;
        //move the accessed node to the head
        moveToHead(node);
        return node.value;
    }

    //Time: O(1)
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) { //New key and value added
            DLinkedNode newNode = new DLinkedNode(); //Create a new DLinkedNode with key and value associated
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            addNode(newNode); //Add the new created DLinkedNode
            size++;
            if (size > capacity) { // If true, we have attained the full capacity
                //pop the tail
                DLinkedNode tail = popTail(); //Return the Node just before the tail
                cache.remove(tail.key);
                --size;
            }
        } else {
            //update the value
            node.value = value;
            moveToHead(node);
        }
    }

    private DLinkedNode popTail() { //Remove element just before the tail
        //pop the current tail
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
    /* add node just next to the head */
    private void addNode(DLinkedNode node) {
        //add the new node right after the head
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }


    static class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }


}
