package leetcode.design;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/explore/interview/card/amazon/81/design/478/
public class LRUCache {
    /*
    Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.
     */
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }

    private final Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size; //Used to count the actual size of the LinkedNode
    //Space: O(capacity)
    private int capacity;
    private final DLinkedNode head;
    private final DLinkedNode tail;

    public LRUCache(int capacity) {
        this.size = 0; //Actual size of the linkedNode
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
        addNodeToTheHead(node);
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
            addNodeToTheHead(newNode); //Add the new created DLinkedNode
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
    private void addNodeToTheHead(DLinkedNode node) {
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
