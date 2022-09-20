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
        //FOLLOWUP if arrays do not have the same length
        var vec3 = new SparseVector(new int[]{1, 0, 0, 2, 3});
        var vec4 = new SparseVector(new int[]{10, 5});
        //Output: 10
        System.out.println(vec3.doProductFollowUp(vec4));
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
            System.out.println();
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

        //FOLLOW UP
        //If you get a follow up that for non-sparse array how to optimize solution,
        // use binary search as the indexes of non-zero elements will be sorted
        public int doProductFollowUp(SparseVector vec) {
            //calculate sum such that iteration happens over vector with lesser number of non-zero elements.
            if (this.list.size() < vec.list.size()){
                return helper(this, vec);
            } else {
                return helper(vec, this);
            }
        }
        private int helper(SparseVector smallVec, SparseVector largeVec) {
            int result = 0;
            for(int[] curr : smallVec.list) {
                // perform binary search to find the curr non-zero element index in larger non-zero element vector.
                int[] exists = binarySearch(largeVec.list, curr[0]);
                if(exists[0] == curr[0])
                    result += exists[1] * curr[1];
            }
            return result;
        }
        // perform binary search.
        private int[] binarySearch(List<int[]> listParam, int index) {
            int[] result = new int[]{-1, 0};
            int low = 0;
            int high = listParam.size() - 1;
            if(index < listParam.get(low)[0] || index > listParam.get(high)[0]) //True, index is OutOfBound
                return result;
            while(low <= high) {
                int mid = low + (high - low) / 2;
                if(listParam.get(mid)[0] == index) {
                    return listParam.get(mid);
                } else if(listParam.get(mid)[0] > index) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return result;
        }
    }

}
