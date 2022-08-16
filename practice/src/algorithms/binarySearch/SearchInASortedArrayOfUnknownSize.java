package algorithms.binarySearch;
//https://leetcode.com/explore/learn/card/binary-search/136/template-analysis/1061/
public class SearchInASortedArrayOfUnknownSize {

    public static void main(String[] args) {

    }

    /*
    Time: O(log T) where T is an index of target value caused by Binary Search
    Space: O(1)
     */
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target)
            return 0;
        // search boundaries
        int left = 0, right = 1;
        //While target is on the right to the right boundary: reader.get(right) < target:
        while (reader.get(right) < target) {
            //Set left boundary equal to the right one: left = right.
            left = right;
            //Extend right boundary: right *= 2. To speed up, use right shift instead of multiplication: right <<= 1.
            //Left shift: x << 1. The same as multiplying by 2: x * 2.
            right <<= 1;
        }
        // binary search
        int pivot, num;
        while (left <= right) {
            //Right shift: x >> 1. The same as dividing by 2: x / 2.
            pivot = left + ((right - left) >> 1);
            //Retrieve the element at this index: num = reader.get(pivot).
            num = reader.get(pivot);
            if (num == target)
                return pivot;
            if (num > target)
                right = pivot - 1;
            else
                left = pivot + 1;
        }
        // there is no target element
        return -1;
    }

    interface ArrayReader {
        /*
        returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
        returns 231 - 1 if the i is out of the boundary of the array.
         */
        int get(int i);
    }

}
