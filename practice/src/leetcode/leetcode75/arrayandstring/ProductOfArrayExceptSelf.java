package leetcode.leetcode75.arrayandstring;

import java.util.Arrays;

//https://leetcode.com/problems/product-of-array-except-self/?envType=study-plan-v2&envId=leetcode-75
public class ProductOfArrayExceptSelf {


    public static void main(String[] args) {
        int[] input1 = new int[]{1, 2, 3, 4};
        // Brut force
        System.out.println(Arrays.toString(productExceptSelfBrutForce(input1)));
        input1 = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelfDividingProduct(input1)));
        input1 = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelfPrefixAndSuffix(input1)));
        input1 = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelfProdAndSuffFinalAnswer(input1)));
    }

    /*
    Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
    You must write an algorithm that runs in O(n) time and without using the division operation.
    Example 1:
    Input: nums = [1,2,3,4]
    Output: [24,12,8,6]
    Example 2:

    Input: nums = [-1,1,0,-3,3]
    Output: [0,0,9,0,0]
     */

    //1. Brut force
    /*
    Time: O(n2), we have 2 for loops
    Space: O(n) for the result.
     */
    public static int[] productExceptSelfBrutForce(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int pro = 1;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                pro *= nums[j];
            }
            ans[i] = pro;
        }

        return ans;
    }

    //2. Dividing the product of array with the number
    /*
    Time: O(n)
    Can cause issue if we have a 0 in the array. prod will be at 0
     */
    public static int[] productExceptSelfDividingProduct(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int prod = 1;

        for (int i : nums) {
            prod *= i;
        }
        for (int i = 0; i < nums.length; i++) {
            ans[i] = prod / nums[i];
        }
        return ans;
    }

    //3. Finding the prefix product and suffix product
    // 1, 2, 3, 4
    //Time: O(n)
    public static int[] productExceptSelfPrefixAndSuffix(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] suff = new int[n];
        pre[0] = 1;
        suff[n - 1] = 1;

        // [1, 1, 2, 6]
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        // [24, 12, 4, 1]
        for (int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] * nums[i + 1];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = pre[i] * suff[i];
        }
        return ans;
    }

    //4. Directly store the product of prefix and suffix into the final answer.
    // Time: O(n)
    // Space: O(1) if we exclude the result.
    public static int[] productExceptSelfProdAndSuffFinalAnswer(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int curr = 1;
        // 1, 1, 2, 6
        for (int i = 0; i < n; i++) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        curr = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        return ans;
    }

}
