package leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//https://leetcode.com/problems/time-based-key-value-store/
public class TimeBasedKeyValueStore {
    /*
    Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

    Implement the TimeMap class:

    TimeMap() Initializes the object of the data structure.
    void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
    String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
     */
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
        System.out.println(timeMap.get("foo", 1));         // return "bar"
        System.out.println(timeMap.get("foo", 3));         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        System.out.println(timeMap.get("foo", 4));         // return "bar2"
        System.out.println(timeMap.get("foo", 5));         // return "bar2"
    }

    static class TimeMap {

        //Key: key : Value: <key: timestamp, value: value>> sorted in increasing order
        private Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if(!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }
            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            Map<Integer, String> treeMap = map.get(key);
            if(treeMap == null) {
                return "";
            }
            // Iterate on time from 'timestamp' to '1'.
            for (int currTime = timestamp; currTime >= 1; --currTime) { //start from the end because it is the most recent timestamp
                // If a value for current time is stored in key's bucket we return the value.
                if (treeMap.containsKey(currTime)) {
                    return treeMap.get(currTime);
                }
            }

            // Otherwise no time <= timestamp was stored in key's bucket.
            return "";
        }
    }
}
