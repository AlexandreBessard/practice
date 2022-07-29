package topInterviewQuestion.medium.design;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/112/design/813/
public class InsertDeleteGetRandomO1 {

    public static void main(String[] args) {

    }

    static class RandomizedSet {
        private Map<Integer, Integer> dict;
        private Random rand = new Random();
        private List<Integer> list;

        public RandomizedSet() {
            dict = new HashMap<>();
            list = new ArrayList<>();
        }
        //Insert value to the set
        //Time complexity: O(1)
        //worst case is O(N) when operations exceeds the capacity of currently allocated array/hashmap
        //and invokes space reallocation
        public boolean insert(int val){
            if(dict.containsKey(val))
                return false;
            dict.put(val, list.size());
            list.add(list.size(), val);
            return true;
        }

        //Time complexity: O(1)
        public boolean remove(int val) {
            if(!dict.containsKey(val))
                return false;
            int lastElement = list.get(list.size() - 1);
            int idx = dict.get(val);
            list.set(idx, lastElement);
            dict.put(lastElement, idx);
            //delete the last element
            list.remove(list.size() - 1);
            dict.remove(val);
            return true;
        }
        //Time complexity: O(1)
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }

    }



}
