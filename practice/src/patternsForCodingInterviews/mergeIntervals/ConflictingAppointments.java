package patternsForCodingInterviews.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628743642980_24Unit
public class ConflictingAppointments {
/*
Given an array of intervals representing ‘N’ appointments, find out if a person can attend all the appointments.
 */
    public static void main(String[] args) {
        Interval[] intervals = {new Interval(1, 4), new Interval(2, 5),
                new Interval(7, 9)};
        boolean result = canAttendAllAppointments(intervals);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals1 = {new Interval(6, 7), new Interval(2, 4),
                new Interval(8, 12)};
        result = canAttendAllAppointments(intervals1);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals2 = {new Interval(4, 5), new Interval(2, 3),
                new Interval(3, 6)};
        result = canAttendAllAppointments(intervals2);
        System.out.println("Can attend all appointments: " + result);

        Interval[] nums = {new Interval(4, 5),
                new Interval(2, 3),
                new Interval(3, 6),
                new Interval(5, 7),
                new Interval(7, 8)
        };
        for (List<Interval> ints : allConflictingAppointments(nums)) {
            System.out.println("\n");
            for (Interval i : ints) {
                System.out.print("[ " + i.start + " and " + i.end + " conflicts \n");
            }
        }
    }

    /*
    Time: O(N * log * N) where N number of appointments, caused by sort
    Space: O(N) needed for sorting algo. For Java, Arrays.sort() uses Timesort which
    need O(N) space.
     */
    public static boolean canAttendAllAppointments(Interval[] intervals) {
        //Sort appointment by start time
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        //Find overlapping appointment
        for (int i = 1; i < intervals.length; i++) {
            // please note the comparison above, it is "<" and not "<="
            // while merging we needed "<=" comparison, as we will be merging the two
            // intervals having condition "intervals[i].start == intervals[i - 1].end" but
            // such intervals don't represent conflicting appointments as one starts right
            // after the other
            if (intervals[i].start < intervals[i - 1].end) { //If true means the previous appointment is not finished.
                return false;
            }
        }
        return true;
    }


    //Problem 1: Problem 1: Given a list of appointments, find all the conflicting appointments.
    public static List<List<Interval>> allConflictingAppointments(Interval[] intervals) {
        List<List<Interval>> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            return res;
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        Interval longestAppointment = null; //Keep in memory the longest appointment because it causes conflicts with other appointments (older appointements)
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end //True if previous appointment is not finished
                    || (longestAppointment != null && intervals[i].start < longestAppointment.end))
            { //Conflict bracket condition
                List<Interval> list;
                if (longestAppointment != null && intervals[i].start < longestAppointment.end)
                {//Case if we still have a conflict with an older appointment
                    list = new ArrayList<>();
                    list.add(longestAppointment);
                    //One new interval
                    list.add(new Interval(intervals[i].start, intervals[i].end));
                    if (longestAppointment.end < intervals[i].end) {
                        longestAppointment = intervals[i];
                    }
                }
                else
                { //Conflict with the previous appointment
                        list = new ArrayList<>();
                        Interval currInterval = new Interval(intervals[i].start, intervals[i].end);
                        Interval prevInterval = new Interval(intervals[i - 1].start, intervals[i - 1].end);
                        //Two new intervals
                        list.add(currInterval);
                        list.add(prevInterval);
                        if (intervals[i].end >= intervals[i - 1].end) { //Get the longest appointment
                            longestAppointment = intervals[i];
                        } else {
                            longestAppointment = intervals[i - 1];
                        }
                }
                res.add(list);
            } //End of 'Conflict' bracket
        }
        return res;
    }
}

