package crackingTheCodingInterview.hashTable;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable<K, V> {
    private static class LinkedListNode<K, V> {
        K key;
        V value;
        LinkedListNode<K, V> next;
        LinkedListNode<K, V> prev;
        public LinkedListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String printForward() {
            String data  = " key: " + key + ", value: " + value;
            if(next != null) {
                return data + next.printForward();
            } else {
                return data;
            }
        }
    }

    ArrayList<LinkedListNode<K, V>> arr;
    public HashTable(int capacity) {
        arr = new ArrayList<>(capacity);
        arr.ensureCapacity(capacity);
        for(int i = 0; i < capacity; i++) {
            arr.add(null);
        }
    }

    public V remove(K key) {
        LinkedListNode<K, V> node = getNodeForKey(key);
        if(node == null)
            return null;
        if(node.prev != null) {
            node.prev.next = node.next;
        } else {
            //Removing head - update
            int hashKey = getIndexForKey(key);
            arr.set(hashKey, node.next);
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }
        return node.value;
    }

    public V get(K key) {
        if(key == null)
            return null;
        LinkedListNode<K, V> node = getNodeForKey(key);
        return node == null ? null : node.value;
    }

    public V put(K key, V value) {
        LinkedListNode<K, V> node = getNodeForKey(key);
        if(node != null) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        node = new LinkedListNode<>(key, value);
        int index = getIndexForKey(key);
        if(arr.get(index) != null) {
            node.next = arr.get(index);
            node.next.prev = node;
        }
        arr.set(index, node);
        return null;
    }

    private LinkedListNode<K, V> getNodeForKey(K key) {
        int index = getIndexForKey(key);
        LinkedListNode<K, V> current = arr.get(index);
        while(current != null) {
            if(current == current.value)
                return current;
            current = current.next;
        }
        return null;
    }

    private int getIndexForKey(K key) {
        return Math.abs(key.hashCode() % arr.size());
    }

    public void printTable() {
        for(int i = 0; i < arr.size(); i++) {
            String s = arr.get(i) == null ? "" : arr.get(i).printForward();
            System.out.println(s);
        }
    }

}
