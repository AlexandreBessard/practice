package topInterviewQuestion.easy.array;

import java.util.*;

public class SingleNumber {
/*
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        //Unique element is 4
        System.out.println(singleNumberHashTable(nums));
        System.out.println(singleNumberBitManipulation(nums));
        System.out.println(singleNumberMath(nums));
    }

    //Approach 2: HashTable
    /*
    Time complexity: O(n . 1) = O(n) loop is O(n), time complexity of hashtable operation is O(1)
    Space complexity: O(n) caused by the hashtable
     */
    static int singleNumberHashTable(int[] nums) {
        Map<Integer, Integer> hash_table = new HashMap<>();
        for(int i : nums) {
            hash_table.put(i, hash_table.getOrDefault(i, 0) + 1);
        }
        for(int i : nums) {
            if(hash_table.get(i) == 1) {
                return i;
            }
        }
        return 0;
    }



    //Approach 4: Bit Manipulation
    static int singleNumberBitManipulation(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }


    //Approach 3: Math:
    static int singleNumberMath(int[] nums) {
        int sumOfSet = 0, sumOfNums = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sumOfSet += num;
            }
            sumOfNums += num;
        }
        return 2 * sumOfSet - sumOfNums;
    }

        //Approach 1: List operation
    /*
    Time complexity: O(n²) -> we iterate through nums & search and find there is
    a duplicate taking O(n)
    Space complexity: O(n)
     */
    static int singleNumber(int[] nums) {
        List<Integer> no_duplicate_list = new ArrayList<>();
        for (int i : nums) {
            if (!no_duplicate_list.contains(i)) { //Use O(n) time to search for the element
                no_duplicate_list.add(i);
            } else {
                no_duplicate_list.remove(new Integer(i));
            }
        }
        return no_duplicate_list.get(0);
    }
}
