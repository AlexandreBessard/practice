package leetcode.topinterview150.intervals;

import java.util.ArrayList;
import java.util.List;

public class Interval {

    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static List<Interval> convertArrayToList(int[][] intervals) {
        List<Interval> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new Interval(interval[0], interval[1]));
        }
        return list;
    }
}
