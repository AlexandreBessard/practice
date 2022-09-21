package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.*;

//https://leetcode.com/problems/exclusive-time-of-functions/
public class ExclusiveTimeOfFunctions {

    /*
    logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}".
    For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3,
    and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2.
    Note that a function can be called multiple times, possibly recursively.
     */
    public static void main(String[] args) {
        List<String> logs = new ArrayList<>(List.of("0:start:0","1:start:2","1:end:5","0:end:6"));
        int n = 2;
        System.out.println(Arrays.toString(exclusiveTime(n, logs)));
    }

    /*
    //Correction: https://leetcode.com/problems/exclusive-time-of-functions/discuss/153497/Java-solution-using-stack-wrapper-class-and-calculation-when-pop-element-from-the-stack.
    Extract the log parsing logic as a inner class.
    Calculate the function's running time when encounter an "end" log entry. If current ended func has a main func still running (in the stack), substract the running time advance. So we don't need to use a "prev" variable.
    Another idea is using a field in the inner class to track the real running time for a function. I believe this way would be the most straightforward for myself.
    Both methods follows the O(n) time complexiy, and O(n/2) extra space consumption.
    Time: O(n)
    Space: (On/2)
     */
    static int[] exclusiveTime(int n, List<String> logs) {
        Deque<Log> stack = new ArrayDeque<>();
        int[] result = new int[n];
        int duration = 0;
        for (String content : logs) {
            Log log = new Log(content);
            if (log.isStart) {
                stack.push(log);
            } else {
                Log top = stack.pop();
                result[top.id] += (log.time - top.time + 1 - top.subDuration);
                if (!stack.isEmpty()) {
                    stack.peek().subDuration += (log.time - top.time + 1);
                }
            }
        }
        return result;
    }

    //Extract the log parsing logic as a inner class.
    static class Log {
        int id;
        boolean isStart;
        int time;
        int subDuration;

        Log(String content) {
            String[] strs = content.split(":");
            id = Integer.parseInt(strs[0]);
            isStart = strs[1].equals("start");
            time = Integer.parseInt(strs[2]);
            subDuration = 0;
        }
    }



}
