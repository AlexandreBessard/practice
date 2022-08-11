package topInterviewQuestion.amazon.arraysAndStrings;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/502/
public class CompareVersionNumbers {

    public static void main(String[] args) {
        String v1 = "1.01";
        String v2 = "1.001";
        String v11 = "1.2.0.2";
        String v22 = "1.2";
        System.out.println(compareVersion(v1, v2));
        System.out.println(compareVersionOnePass(v22, v11));
    }

    //Approach 1: Split + Parse, Two Pass
    public static int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\."); //Can be optimized, see other approach.
        String[] nums2 = version2.split("\\.");
        int n1 = nums1.length;
        int n2 = nums2.length;
        //compare version
        int i1, i2;
        for(int i = 0; i < Math.max(n1, n2); i++) {
            i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
            i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
            if(i1 != i2){
                return i1 > i2 ? 1 : -1;
            }
        }
        //The version are equal
        return 0;
    }

    //Approach 2: One Pass
    //split the string on the fly
    /*
    Time: O(max(N, M))
    Space: O(max(N, M))
     */
    public static int compareVersionOnePass(String version1, String version2) {
        Set<Map.Entry<Integer, Integer>> entries = new HashSet<>();
        int p1 = 0, p2 = 0;
        int n1 = version1.length();
        int n2 = version2.length();
        //Comapre versions
        int i1, i2;
        Map.Entry<Integer, Integer> pair;
        while(p1 < n1 || p2 < n2) {
            pair = getNextChunk(version1, n1, p1);
            i1 = pair.getKey();
            p1 = pair.getValue();

            pair = getNextChunk(version2, n2, p2);
            i2 = pair.getKey();
            p2 = pair.getValue();
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
        // the versions are equal
        return 0;
    }

    private static Map.Entry<Integer, Integer> getNextChunk(String version, int n, int p) {
        // if pointer is set to the end of string
        // return 0
        if(p > n - 1){
            return Pair.of(0, p);
        }
        //Find end of chunk
        int i, pEnd = p;
        while(pEnd < n && version.charAt(pEnd) != '.') {
            ++pEnd;
        }
        //Retrieve the chunk
        if(pEnd != n - 1){
            i = Integer.parseInt(version.substring(p, pEnd));
        } else {
            i = Integer.parseInt(version.substring(p, n));
        }
        //Find the beginning of the next chunk
        p = pEnd + 1;
        return Pair.of(i, p);
    }
}

class Pair {
    public static <T, U> Map.Entry<T, U> of(T first, U second) {
        return new AbstractMap.SimpleEntry<>(first, second);
    }
}
