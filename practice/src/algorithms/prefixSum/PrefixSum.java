package algorithms.prefixSum;

import java.util.Arrays;

//https://takeuforward.org/data-structure/prefix-sum-technique/
public class PrefixSum {

    /*
     the prefix sum array is the array whose every element is the sum of all elements,
     of the original array, up to the current index.
      array[]={1,2,3,4,5}
      Final Prefix Sum Array would be={1,3,6,10,15}
     */
    public static void main(String[] args) {
        int ar[] = {1, 2, 3, 4, 5};
        int prefix[] = new int[ar.length];
        prefix[0] = ar[0];
        for (int i = 1; i < ar.length; i++) {
            prefix[i] = prefix[i - 1] + ar[i];
        }
        System.out.println(Arrays.toString(prefix));
    }

}
