package leetcode.design;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/logger-rate-limiter/
public class LoggerRateLimiter {
    /*
    Design a logger system that receives a stream of messages along with their
    timestamps. Each unique message should only be printed at
    most every 10 seconds (i.e. a message printed at timestamp t will
    prevent other identical messages from being printed until timestamp t + 10).

    Implement the Logger class:

    Logger() Initializes the logger object.
    bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be printed in the given timestamp, otherwise returns false.

    Input
    ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
    [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
    Output
    [null, true, true, false, false, false, true]
     */
    public static void main(String[] args) {
        Logger logger = new Logger();
        System.out.println(logger.shouldPrintMessage(1, "foo"));  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
        logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
        logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
        logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
        logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
        logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21
    }

    //Approach 2: HashTable / Dictionary
    /*
    Time: O(1) the lookup and update of the hashtable takes a constant time
    Space: O(M) where M is all incoming messages
     */
    public static class Logger {

        //Key: message, Value: first timestamp
        private final Map<String, Integer> msgDict;

        public Logger() {
            msgDict = new HashMap<>();
        }

        //True if message should be printed in the given timestamp, else false
        public boolean shouldPrintMessage(int timestamp, String message) {
            //true if message is not contains. put timestamp as associated value
            if (!this.msgDict.containsKey(timestamp)) {
                this.msgDict.put(message, timestamp);
                return true;
            }
            Integer oldTimeStamp = this.msgDict.get(message);
            //Calcul if range of 10 seconds from that timestamp
            if (timestamp - oldTimeStamp >= 10) {//true means after 10 as passed
                this.msgDict.put(message, timestamp);
                return true;
            }
            return false; // We still in the 10 second range from the latest timestamp
        }
    }


}
