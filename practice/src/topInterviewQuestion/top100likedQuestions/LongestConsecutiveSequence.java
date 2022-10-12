package topInterviewQuestion.top100likedQuestions;

import java.util.*;

//https://leetcode.com/problems/longest-consecutive-sequence/
public class LongestConsecutiveSequence {
    /*
    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

    You must write an algorithm that runs in O(n) time.
     */
    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutiveBrutForce(nums1));
        System.out.println(longestConsecutiveSorted(new int[]{9, 1, -1, 2, 3, 3, 4}));
        System.out.println(longestConsecutiveHashSet(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println("UF -> " + longestConsecutiveUnionFind(new int[]{100, 4, 200, 1, 3, 2}));
    }

    //Union Find:  https://leetcode.com/problems/longest-consecutive-sequence/discuss/166544/Union-Find-Thinking-Process
    /*
    A node is a value of an element in nums in this case.
    Two nodes are connected if they are consecutive.
    Time: O(n)

     */
    static int longestConsecutiveUnionFind(int[] nums) {
        UF uf = new UF(nums.length);
        //Map val to index from nums
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (valToIndex.containsKey(nums[i])) { //For duplicate element, we count only once.
                continue;
            }
            if (valToIndex.containsKey(nums[i] - 1)) {
                uf.union(i, valToIndex.get(nums[i] - 1)); //connect these two node by using their index
            }
            if (valToIndex.containsKey(nums[i] + 1)) {
                uf.union(i, valToIndex.get(nums[i] + 1));
            }
            valToIndex.put(nums[i], i);
        }
        return uf.getLargetComponentSize();
    }

    static class UF {
        private int[] parent;
        private int[] size;

        public UF(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) { //connected if consecutive
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) { //If different means there are not connected
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        }

        private int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public int getLargetComponentSize() {
            int maxSize = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == i && size[i] > maxSize) {
                    maxSize = size[i];
                }
            }
            return maxSize;
        }
    }

    //Approach 3: HashSet and Intelligent Sequence Building
    /*
    Time: O(n)
    Space: O(n)
     */
    static int longestConsecutiveHashSet(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }
        int longestStreak = 0;
        for (int num : num_set) {
            if (!num_set.contains(num - 1)) { //Constant operation
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum + 1)) { //Constant operation
                    currentNum++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }


    //Approach 2: Sorting
    /*
    Time: O(n log n)
    Space: O(1)
     */
    static int longestConsecutiveSorted(int[] nums) { // [-1, 1, 2, 3, 3, 4, 9]
        if (nums.length == 0) return 0;
        Arrays.sort(nums); // -> Dual pivot QuickSort : space 0(1)
        int longestStreak = 1;
        int currentStreak = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) { //If duplicate, stop the current Streak
                if (nums[i] == (nums[i - 1] + 1)) {
                    currentStreak++;
                }
            } else {
                longestStreak = Math.max(longestStreak, currentStreak);
                currentStreak = 1;
            }
        }
        return Math.max(longestStreak, currentStreak);
    }


    //Brut force
    /*
    Time: O(n3) -> cubic time -> 3 loops
    Space: O(1)
     */
    static int longestConsecutiveBrutForce(int[] nums) {
        int longestStreak = 0;
        for (int num : nums) {
            int currentNum = num;
            int currentStreak = 1;
            while (arrayContains(nums, currentNum + 1)) { //True, the next element exist in the array
                currentNum++; //Increment for the next element to be found
                currentStreak++; // Every time we found that element, increment by 1 to find the max streak once done.
            }
            longestStreak = Math.max(longestStreak, currentStreak);
        }
        return longestStreak;
    }

    private static boolean arrayContains(int[] arr, int num) { //Look if the element increment by 1 exist
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }

}
