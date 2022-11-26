package leetcode.design;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
public class DotProductOfTwoSparseVectors {
    /*
    Given two sparse vectors, compute their dot product.
    Implement class SparseVector:
    SparseVector(nums) Initializes the object with the vector nums
    dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
    A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

    Follow up: What if only one of the vectors is sparse?

    Example 1:
    Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
    Output: 8
    Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
    v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
     */
    public static void main(String[] args) {
        // Your SparseVector object will be instantiated and called as such:
        int[] nums1 = {1, 0, 0, 2, 3};
        int[] nums2 = {0, 3, 0, 4, 0};
        SparseVector v1 = new SparseVector(nums1);
        SparseVector v2 = new SparseVector(nums2);
        int ans = v1.dotProduct(v2);
        System.out.println(ans);
    }

    //Correction: https://leetcode.com/problems/dot-product-of-two-sparse-vectors/discuss/826713/Java-100-O(min(dm-dn))-solution
    static class SparseVector {
        //Key: Index of that element, Value: element
        Map<Integer, Integer> indexMap = new HashMap<>();

        SparseVector(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) { //Ignore 0 because if we multiply any element with 0, it is going to be 0
                    indexMap.put(i, nums[i]);
                }
            }
        }

        public int dotProduct(SparseVector sparseVector) {
            if (this.indexMap.size() == 0 || sparseVector.indexMap.size() == 0) {
                return 0;
            }
            if (this.indexMap.size() > sparseVector.indexMap.size()) {
                return sparseVector.dotProduct(this); //work with the smaller indexMap in size
            }
            int productSum = 0;
            for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()) {
                int index = entry.getKey(); //get the index
                Integer vecValue = sparseVector.indexMap.get(index); //get value from that same index
                if (vecValue == null) {
                    continue;
                }
                productSum += entry.getValue() * vecValue;
            }
            return productSum;
        }
    }

}
