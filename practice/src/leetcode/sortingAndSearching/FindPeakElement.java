package leetcode.sortingAndSearching;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/801/
public class FindPeakElement {
/*
A peak element is an element that is strictly greater than its neighbors.
 */
    public static void main(String[] args) {
        //1,2,3,1
        //Output 2: Return index
        System.out.println(findPeakElementIterativeBinarySearchTree(new int[]{1, 2, 3, 1}));
    }

    /**
     * This template is used to search for an element which requires accessing the current Index and its immediate
     * right's neighbor
     * {@link algorithms.binarySearch.templates.Template2}
     */
    //Approach 3: Iterative Binary Search
    /*
    Time complexity: O(log N) where N in the total element in the array
    Space complexity: O(1)
     */
    static int findPeakElementIterativeBinarySearchTree(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1; // mid + 1 because we already checked the next number with 'nums[mid + 1]'
            }
        }
        return left; //Return the peak
    }

    //Approach 2: Recursive Binary Search
    /*
    Time complexity: O(log²(n)) reduce space in half at every step.
    Space complexity: O(log²(n)) caused by depth of recursion.
     */
    static int findPeakElementRecursiveBinarySearchTree(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    private static int search(int[] nums, int l, int r) {
        if(l == r)
            return l;
        int mid =  (r + l) / 2;
        if(nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }



        //Approach 1: Linear Scan
    /*
    Time complexity: O(n)
    Space complexity: O(1)
     */
    static int findPeakElement(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

}
