package topInterviewQuestion.amazon.arraysAndStrings;
//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/499/
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        var obj = new ProductOfArrayExceptSelf();
        for(int i : obj.productExceptSelf(nums)) {
            System.out.print(i + ", ");
        }
    }


    //Approach 2: O(1) Space approach
    /*
    Time: O(N)
    Space: O(1), we do not use additional array for our computations.
     */
    public int[] productExceptSelfO1Space(int[] nums) {
        // The length of the input array
        int length = nums.length;
        // Final answer array to be returned
        int[] answer = new int[length];
        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {

            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] * answer[i - 1];
        }
        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // For the index 'i', R would contain the
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }
        return answer;
    }


    //Approach 1: left and right product lists
    /*
    Time: O(N)
    Space: O(N)
        [1,  2,  3, 4]
    L   [1,  1,  2, 6]
    R   [24, 12, 4, 1]
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] L = new int[length]; //contains product of all elements to the left
        int[] R = new int[length]; //contains product of all elements to the right
        //final answer
        int[] answer = new  int[length];
        // Note: for the element at index '0', there are no elements to the left,
        // so L[0] would be 1
        L[0] = 1;
        for(int i = 1; i < length; i++) {
            // L[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            L[i] = nums[i - 1] * L[i - 1];
        }
        // R[i] contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R[length - 1] would be 1
        R[length - 1] = 1;
        for(int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        // Constructing the answer array
        for (int i = 0; i < length; i++) {
            // For the first element, R[i] would be product except self
            // For the last element of the array, product except self would be L[i]
            // Else, multiple product of all elements to the left and to the right
            answer[i] = L[i] * R[i];
        }
        return answer;
    }


}
