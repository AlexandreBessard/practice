package leetcode.array;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/674/
public class IntersectionOfTwoArrays2 {

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        //Output: [4, 9]
        /* Use approach 1 & 2 for this one
        for (int i : intersectSort(nums1, nums2)) {
            System.out.print(i + ", ");
        }
         */
        //Use approach 3 for this case:
        nums1 = new int[]{9, 4, 9, 8, 4};
        nums2 = new int[]{4, 9, 5};
        System.out.println(Arrays.toString(intersectBinarySearch(nums1, nums2)));

    }

    //Approach 3: In case if the array1 is longer than array2
    //https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/1808056/Java-Binary-Search
    /*
    Let's say nums1 is K size. Then we should do binary search for every element in nums1.
    Each lookup is O(log N), and if we do K times, we have O(K log N).
If K this is small enough, O(K log N) < O(max(N, M)).
Otherwise, we have to use the previous two pointers method.
let's say A = [1, 2, 2, 2, 2, 2, 2, 2, 1], B = [2, 2].
For each element in B, we start a binary search in A. To deal with duplicate entry,
once you find an entry, all the duplicate element is around that that index, so you can do linear search scan afterward.
Time complexity, O(K(logN) + N). Plus N is worst case scenario which you have to linear scan every element in A.
But on average, that shouldn't be the case. so I'd say the Time complexity is O(K(logN) + c), c (constant)
is number of linear scan you did.
//Space: O(N) if we need to sort the array
     */
    public static int[] intersectBinarySearch(int[] nums1, int[] nums2) {
        if(nums2.length < nums1.length) {
            intersectBinarySearch(nums2, nums1);
        }
        //nums1 has equal or smaller length than nums2
        Arrays.sort(nums1); // 4, 5, 9
        Arrays.sort(nums2); // 4, 4, 8, 9, 9
        List<Integer> result = new ArrayList<>();
        int leftIndex = 0;
        for(int num : nums1) {
            int index = binarySearch(nums2, num, leftIndex);
            if(index != -1) {
                result.add(num);
                leftIndex = index + 1;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    private static int binarySearch(int[] nums, int target, int left) {
        int right = nums.length - 1;
        int index = -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                index = mid;
                right = mid - 1;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    //Approach 2: In case if the arrays are sorted
    /*
    Time complexity: O(max(N, M))
     */
    static int[] intersectSort(int[] nums1, int[] nums2) throws InterruptedException {
        int n = nums1.length, m = nums2.length;
        Thread t1 = new Thread(() -> Arrays.sort(nums1));
        Thread t2 = new Thread(() -> Arrays.sort(nums2));
        t1.start(); t2.start();
        int i = 0, j = 0; //One pointer for each array
        List<Integer> list = new ArrayList<>();
        t1.join(); t2.join();
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] ret = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            ret[k] = list.get(k);
        }
        return ret;
    }

    //Approach 1: Hash Table IF the array is NOT sorted
    /*
    Time complexity: O(n + m) -> where n and m are the lengths of the arrays.
    Iterate through the first and second array.
    Look up and insert in hashmap is O(1)
    Space complexity: O(min(n, m), we use hashmap to store numbers from
    the smaller array
     */
    static int[] intersectHashMap(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.get(i) != null && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
