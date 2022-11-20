package leetcode.design;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/112/design/813/
public class InsertDeleteGetRandomO1 {
/*
Implement the RandomizedSet class:
RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.
 */
    public static void main(String[] args) {
        var obj = new RandomizedSet();
        System.out.println(obj.insert(1)); // Inserts 1 to the set. Returns true as 1 was inserted
        System.out.println(obj.remove(2)); // Returns false as 2 does not exist in the set.
        obj.insert(2);
        System.out.println(obj.getRandom()); // getRandom() should return either 1 or 2 randomly.
        System.out.println(obj.remove(1));
    }

    static class RandomizedSet {
        //Key: element, Value: index where this element is located in the list
        private final Map<Integer, Integer> dict;
        private final Random rand = new Random();
        private final List<Integer> list; //Index used to get our value, used by our random method

        public RandomizedSet() {
            dict = new HashMap<>();
            list = new ArrayList<>(); //Size if 0
        }
        //Insert value to the set
        //Time complexity: O(1)
        //worst case is O(N) when operations exceed the capacity of currently allocated array/hashmap
        //and invokes space reallocation
        public boolean insert(int val){
            if(dict.containsKey(val)) {
                return false;
            }
            int sizeAsIndex  = list.size(); //Allows you to know the index where the element is going to be stored in the map
            //Ex with value to add : 1
            //map : 1 -> 0
            //list : idx 0 -> 1
            dict.put(val, sizeAsIndex );
            list.add(sizeAsIndex , val); //Add value at this index
            return true;
        }

        //Time complexity: O(1)
        public boolean remove(int val) {
            if(!dict.containsKey(val)) {
                return false;
            }
            int lastElement = list.get(list.size() - 1); //Take the latest element
            int idx = dict.get(val); //get the index concerning the element to be removed
            list.set(idx, lastElement); // replace element to be removed with the latest element
            dict.put(lastElement, idx); // replace old by new index where was located the old element (index)
            //delete the last element
            list.remove(list.size() - 1); //Delete the latest element
            dict.remove(val); //Remove the value from the map
            return true;
        }
        //Time complexity: O(1)
        public int getRandom() {
            //Get element from 0 (included) to list.size() (excluded)
            return list.get(rand.nextInt(list.size()));
        }

    }



}
