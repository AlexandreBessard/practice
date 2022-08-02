package topInterviewQuestion.medium.arraysAndStrings;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/776/
public class _3Sum {

    public static void main(String[] args) {
        /*
        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
         */
        int[] nums = {-1,0,1,2,-1,-4};
        int[] nums3 = {-1,-1,-1,-1,2,4};

        int[] nums2 = {-4, -1, -1, 0, 1, 2};
        List<List<Integer>> result = threeSumNoSort(nums);
        List<List<Integer>> result1 = threeSum(nums3);

        for(List<Integer> l1 : result) {
            System.out.println();
            for(int i : l1) {
                System.out.print(i + ", ");
            }
        }
    }

    //Approach 3 : No-Sort, -> If you can not modify the input array
    /*
    Time complexity: O(n²) we have outer and inner loops
    Space complexity: O(n) for hashSet/hashMap
     */
    static List<List<Integer>> threeSumNoSort(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; j++) {
                    int complement = -nums[i] - nums[j]; //Complement - 1 + 0 == 1 to have 0
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        }
        return new ArrayList<>(res);
    }

    //Approach 2: HashSet
    /*
    Time complexity: O(n²)
    Space complexity: O(n) for the hashSet
     */
    static List<List<Integer>> threeSumHashSet(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumHashSet(nums, i, res);
            }
        return res;
    }
    private static void twoSumHashSet(int[] nums,
                                      int i,
                                      List<List<Integer>> res)
    {
        var seen = new HashSet<Integer>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            System.out.println("complement : " + complement);
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1])
                    ++j;
            }
            seen.add(nums[j]);
        }
    }


    //Approach 1: Two pointers
    /*
    Time complexity: O(n²), twoSum2 is O(n)
    Sorting array : O(n log n) (equivalent to O(n²)
    Space complexity: O(log N) depending on sorting algo (we ignore
    the memory required for the output)
     */
    static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(i == 0 || nums[i - 1] != nums[i])
                twoSum2(nums, i , res);
        }
        return res;
    }
    private static void twoSum2(int[] nums,
                                int i,
                                List<List<Integer>> res)
    {
        int lo = i + 1, hi = nums.length - 1;
        while(lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if(sum  < 0) {
                lo++;
            } else if(sum > 0) {
                hi--;
            } else { //Avoid duplicate results ex: -1 -1 -1 -1, 2, 4 -> get (-1, -1  2) once
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi++]));
                while(lo < hi && nums[lo] == nums[lo - 1])
                    lo++;
            }
        }
    }

}
