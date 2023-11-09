package leetcode.topinterview150.hashmap.easy;

public class IsomorphicStrings {

    // https://leetcode.com/problems/isomorphic-strings/?envType=study-plan-v2&envId=top-interview-150
    public static void main(String[] args) {
        String s = "egg";
        String t = "add";
        System.out.println(isIsomorphic(s, t));
        System.out.println(isIsomorphicOneArray(s, t));
        s = "foo";
        t = "bar";
        System.out.println(isIsomorphic(s, t));
        System.out.println(isIsomorphicOneArray(s, t));
        s = "paper";
        t = "title";
        System.out.println(isIsomorphic(s, t));
        System.out.println(isIsomorphicOneArray(s, t));
    }

    /*
    Improved version, we use only 1 array
    Time: O(1)
    Space: O(1), use constant space whatever the inputs
     */
    static boolean isIsomorphicOneArray(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // Assuming ASCII characters
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {

            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (map[charS] == 0) {
                map[charS] = charT;
            } else if (map[charS] != charT) {
                return false;
            }
        }
        return true;
    }


    /*
    HashMap
    Time: O(n)
    Space: O(1), space used is constant
     */
    static boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[26];
        int[] mapT = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            // Get the current letter
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (mapS[charS - 'a'] != mapT[charT - 'a']) {
                return false;
            }
            mapS[charS - 'a']++;
            mapT[charT - 'a']++;
        }
        return true;
    }

}
