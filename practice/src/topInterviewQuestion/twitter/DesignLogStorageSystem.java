package topInterviewQuestion.twitter;

import java.util.*;

//https://leetcode.com/problems/design-log-storage-system/
public class DesignLogStorageSystem {

    /*
    You are given several logs, where each log contains a unique ID and timestamp. Timestamp is a string that has the following format:
    Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
     */

    public static void main(String[] args) {
        LogSystem logSystem = new LogSystem();
        logSystem.put(1, "2017:01:01:23:59:59");
        logSystem.put(2, "2017:01:01:22:59:59");
        logSystem.put(3, "2016:01:01:00:00:00");

        List<Integer> res = logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
        System.out.println(List.of(res));
    }

    static class LogSystem {

        private static final String MIN_TIMESTAMP = "2000:01:01:00:00:00";
        private static final String MAX_TIMESTAMP = "2017:12:31:23:59:59";

        private Map<String, Integer> indices;
        //The map is sorted according to the natural ordering of its keys,
        private TreeMap<String, List<Integer>> logs;

        public LogSystem() {
            initializeIndicesMap();
            this.logs = new TreeMap<>();
        }

        private void initializeIndicesMap() {
            this.indices = new HashMap<>();
            //Value represent the end of the concerned granularity (exclusive)
            indices.put("Year", 4);
            indices.put("Month", 7);
            indices.put("Day", 10);
            indices.put("Hour", 13);
            indices.put("Minute", 16);
            indices.put("Second", 19);
        }

        public void put(int id, String timestamp) {
            logs.computeIfAbsent(timestamp, k -> new ArrayList<>()).add(id);
        }

        public List<Integer> retrieve(String s, String e, String granularity) {
            int index = indices.get(granularity);
            String start = s.substring(0, index) + MIN_TIMESTAMP.substring(index);
            String end = e.substring(0, index) + MAX_TIMESTAMP.substring(index);
            return retrieve(start, end);
        }

        private List<Integer> retrieve(String start, String end) {
            List<Integer> allIds = new ArrayList<>();
            for(String timestamp : logs.keySet()) {
                //compareTo() return 0 if they are equal
                //< 0 if the string is lexicographically less than the other string
                //> 0 if the string is lexicographically greater than the other string (more characters)
                /*
                For example, let's take three strings, "short", "shorthand" and "small".
                In the dictionary, "short" comes before "shorthand" and "shorthand" comes before "small".
                This is lexicographical order.
                 */
                if(timestamp.compareTo(start) >= 0 && timestamp.compareTo(end) <= 0) {
                    allIds.addAll(logs.get(timestamp));
                }
            }
            return allIds;
        }

    }


}
