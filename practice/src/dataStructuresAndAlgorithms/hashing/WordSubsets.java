package dataStructuresAndAlgorithms.hashing;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/word-subsets/
public class WordSubsets {

    public static void main(String[] args) {
        String[] words1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2 = {"e","o"};
        System.out.println(wordSubets(words1, words2));
    }

    //Correction: https://leetcode.com/problems/word-subsets/discuss/175854/JavaC%2B%2BPython-Straight-Forward
    static List<String> wordSubets(String[] A, String[] B) {
        int[] count = new int[26];
        int[] tmp;
        int  i;
        for(String b : B) {
            tmp = counter(b);
            for(i = 0; i < 26; i++) {
                count[i] = Math.max(count[i], tmp[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for(String a : A) {
            tmp = counter(a);
            for(i = 0; i < 26; i++) {
                if(tmp[i] <  count[i]) {//If we have more letters in count than the current word, skip it
                    break;
                }
            }
            if(i == 26) {
                res.add(a);
            }
        }
        return res;
    }
    private static int[] counter(String word) {
        int[] count = new int[26];
        for(char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        return count;
    }

}
