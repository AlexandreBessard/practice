package topInterviewQuestion.medium.design;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/112/design/813/
public class InsertDeleteGetRandomO1 {

    public static void main(String[] args) {
        var obj = new RandomizedSet();
        System.out.println(obj.insert(1)); // Inserts 1 to the set. Returns true as 1 was inserted
        System.out.println(obj.remove(2)); // Returns false as 2 does not exist in the set.
        obj.insert(2);
        System.out.println(obj.getRandom()); // getRandom() should return either 1 or 2 randomly.
        System.out.println(obj.remove(1));
    }

    static class RandomizedSet {
        private Map<Integer, Integer> dict;
        private Random rand = new Random();
        private List<Integer> list;

        public RandomizedSet() {
            dict = new HashMap<>();
            list = new ArrayList<>(); //Size if 0
        }
        //Insert value to the set
        //Time complexity: O(1)
        //worst case is O(N) when operations exceeds the capacity of currently allocated array/hashmap
        //and invokes space reallocation
        public boolean insert(int val){
            if(dict.containsKey(val))
                return false;
            int size = list.size();
            //Ex with value to add : 1
            //map : 1 -> 0
            //list : idx 0 -> 1
            dict.put(val, size);
            list.add(size, val);
            return true;
        }

        //Time complexity: O(1)
        public boolean remove(int val) {
            if(!dict.containsKey(val))
                return false;
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
            return list.get(rand.nextInt(list.size()));
        }

    }



}
