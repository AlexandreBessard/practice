package algorithms.prefixSum;

//https://takeuforward.org/data-structure/prefix-sum-technique/
public class ProblemBasedOnPrefixSumTechnique {

    /*
    Problem Statement: Given an array of n integers,
    find if any index exists such that the sum of elements to its right is equal to
    the sum of elements to its left. If yes print the index, otherwise print “No Equilibrium”.

    Example 1:
Input: N = 5, array[] = {7,2,1,5,4}
Output: 2
Explanation: Sum of elements to the left of index 2 is 7+2=9 and to the right of index 2 is 5+4=9.

    Example 2:
Input:  N=4, array[]={23,32,12,4}
Output: No Equilibrium
Explanation: No such index exists for which the sum to its right and left is equal
     */

    public static void main(String[] args) {
        int ar[] = {7, 2, 1, 5, 4};
        equilibriumBrutForce(ar);
        equilibriumPrefixSum(ar);
    }

    //Optimal approach using Prefix Sum technique
    /*
    Time: O(n)
    Space: O(1)
    We take the sum of the whole array and keep a left sum.
    For every index, we remove that index from the total sum
    and match it with the leftsum, if it is equal we break,
    otherwise add the current element to the leftsum and move forward.
     */
    static void equilibriumPrefixSum(int[] nums) {
        int ans = -1; //to store the final required index
        int totalSum = 0;
        int leftSum = 0; //Initialization
        for(int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        for(int i = 0; i < nums.length; i++) {
            totalSum -= nums[i];
            if(leftSum == totalSum) {
                ans = i;
                break;
            }
            leftSum += nums[i];
        }
        printResult(ans, "Prefix Sum");
    }

    //Brut force approach
    /*
    Time: O(n²) -> loop for middle, every time we traverse from beginning left and beginning right
    Spapce: O(1)
     */
    static void equilibriumBrutForce(int[] nums) {
        int ans = -1; //To store the final required index
        for (int middle = 0; middle < nums.length; middle++) {
            int leftSum = 0, rightSum = 0;
            for (int leftP = 0; leftP < middle; leftP++) { //Sum leftSide
                leftSum += nums[leftP];
            }
            for (int rightP = middle + 1; rightP < nums.length; rightP++) { //Sum right side
                rightSum += nums[rightP];
            }
            if (leftSum == rightSum) {
                ans = middle;
                break;
            }
        }
        printResult(ans, "Brut Force");
    }

    private static void printResult(int ans, String algoName) {
        if (ans != -1) {
            System.out.println(algoName + " result -> \n" + ans);
        } else {
            System.out.println("No equilibrium");
        }
    }
}
