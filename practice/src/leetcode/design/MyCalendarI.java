package leetcode.design;

import java.util.*;

//https://leetcode.com/problems/my-calendar-i/
public class MyCalendarI {
/*
You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
Implement the MyCalendar class:
MyCalendar() Initializes the calendar object.
boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
Example 1:
Input
["MyCalendar", "book", "book", "book"]
[[], [10, 20], [15, 25], [20, 30]]
Output
[null, true, false, true]
 */
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20)); // return True
        System.out.println(myCalendar.book(15, 25)); // return False, It can not be booked because time 15 is already booked by another event.
        System.out.println(myCalendar.book(20, 30)); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
    }

    static class MyCalendar {
        //tree map, using the natural ordering of its keys
        //Key: start, Value: end
        //Time: O(logN)
        TreeMap<Integer, Integer> books; //Sort the interval by start time
        public MyCalendar() {
            books = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            for(Map.Entry<Integer, Integer> entry : books.entrySet()) {
                if(start < entry.getValue()) { //True means overlapping
                    return false;
                }
            }
            //Time to insert first element: O(1)
            //Time to insert second element: O(logN)
            books.put(start, end);
            return true;
        }
    }
}
