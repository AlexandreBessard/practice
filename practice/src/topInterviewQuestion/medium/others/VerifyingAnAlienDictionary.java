package topInterviewQuestion.medium.others;
//https://leetcode.com/explore/interview/card/facebook/57/others-3/3042/
public class VerifyingAnAlienDictionary {

    public static void main(String[] args) {
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";

        String[] words2 = {"word","world","row"};
        String order2 = "worldabcefghijkmnpqstuvxyz";

        var obj = new VerifyingAnAlienDictionary();
        //True
        System.out.println(obj.isAlienSorted(words, order));
        //False
        System.out.println(obj.isAlienSorted(words2, order2));
    }

    //Approach 1: Compare adjacent words
    /*
    Time: O(M)
    Space: O(1)
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderMap = new int[26];
        for(int i = 0; i < order.length(); i++) {
            orderMap[order.charAt(i) - 'a'] = i;
        }
        for(int i = 0; i < words.length - 1; i++) { //Loop through each words in the 'words' array
            for(int j = 0; j < words[i].length(); j++) { //Loop through each letter of that word
                // If we do not find a mismatch letter between words[i] and words[i + 1],
                // we need to examine the case when words are like ("apple", "app").
                if(j >= words[i + 1].length())
                    return false; //Cover this case ("apple", "app") when the current word is bigger than the next word
                if(words[i].charAt(j) != words[i + 1].charAt(j)) {
                    int currentWordChar = words[i].charAt(j) - 'a';
                    int nextWordChar = words[i + 1].charAt(j) - 'a';
                    if(orderMap[currentWordChar] > orderMap[nextWordChar]) {
                        return false;
                    }
                    // if we find the first different letter and they are sorted,
                    // then there's no need to check remaining letters
                    else break;
                }
            }
        }
        return true;
    }





}
