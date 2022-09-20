package topInterviewQuestion.facebook.topMostFrequentQuestions;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
public class DotProductOfTwoSparseVectors {

    public static void main(String[] args) {
        int[] nums1 = {1, 0, 0, 2, 3};
        int[] nums2 = {0, 3, 0, 4, 0};
        var vec1 = new SparseVector(nums1);
        var vec2 = new SparseVector(nums2);
        System.out.println(vec1.dotProduct(vec2));
    }

    //Correction: https://leetcode.com/problems/dot-product-of-two-sparse-vectors/discuss/1522271/Java-O(n)-solution-using-Two-Pointers-with-detailed-explanation-and-Follow-up
    //Two Pointers
    static class SparseVector {
        //Space: O(n)
        List<int[]> list;
        //Time: O(n)
        SparseVector(int[] nums) {
            list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) { // Avoid to add 0 (because 0 * ? always return 0)
                    list.add(new int[]{i, nums[i]});
                }
            }
        }

        //Two Pointers
        // Return the dotProduct of two sparse vectors
        /*
        Our goal is to avoid compute with '0' value since 0 multiply by another value return always '0'
        Time: O(n)
        Space: O(1)
         */
        public int dotProduct(SparseVector vec) {
            int dotProduct = 0;
            int p = 0; //Pointer for our this object
            int q = 0; //Pointer for our vec object from our parameter
            while (p < this.list.size() && q < vec.list.size()) {
                if (this.list.get(p)[0] == vec.list.get(q)[0]) { // True if they have the same index
                    dotProduct += this.list.get(p)[1] * vec.list.get(q)[1];
                    p++;
                    q++;
                } else if (this.list.get(p)[0] < vec.list.get(q)[0]) {
                    p++;
                } else {
                    q++;
                }
            }
            return dotProduct;
        }
    }

}
