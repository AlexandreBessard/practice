package topInterviewQuestion.facebook.topMostFrequentQuestions;

//https://leetcode.com/problems/valid-word-abbreviation/
public class ValidWordAbbreviation {

    public static void main(String[] args) {
        String s = "internationalization";
        String abbr = "i12iz4n";
        //Output : true
        System.out.println(validWordAbbreviation(s, abbr));
        String s1 = "apple";
        String abbr1 = "a2e";
        //Output: false
        System.out.println(validWordAbbreviation(s1, abbr1));
        String s2 = "substitution";
        String abbr2 = "12";
        //Output: true
        System.out.println(validWordAbbreviation(s2, abbr2));
    }

    //Two pointers
    //Correction: https://leetcode.com/problems/valid-word-abbreviation/discuss/89523/Short-and-easy-to-understand-Java-Solution
    /*
    Time: O(n)
    Space: O(1)
     */
    static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            //A character between 0 and 9 inclusive is numeric.
            //A character outside of that range is non-numeric.
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') { //If true, it is non numeric
                return false;
            }
            int start = j;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                j++;
            }
            int num = Integer.parseInt(abbr.substring(start, j));
            i += num;
        }
        return i == word.length() && j == abbr.length();
    }


}
