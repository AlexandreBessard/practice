package topInterviewQuestion.amazon.design;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/amazon/81/design/478/
public class LRUCache {

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    //Space: O(capacity)
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
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
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            addNode(newNode);
            size++;
            if (size > capacity) {
                //pop the tail
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            //update the value
            node.value = value;
            moveToHead(node);
        }
    }

    private DLinkedNode popTail() {
        //pop the current tail
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private void addNode(DLinkedNode node) {
        //add the new node right after the head
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }


    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }


}
