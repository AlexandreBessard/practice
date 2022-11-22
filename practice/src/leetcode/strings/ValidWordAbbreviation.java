package leetcode.strings;

//https://leetcode.com/problems/valid-word-abbreviation/
public class ValidWordAbbreviation {
/*
Input: word = "internationalization", abbr = "i12iz4n"
Output: true
Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").

 Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 */
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
        int wordPointer = 0;
        int abbrPointer = 0;
        //We want to process the latest character
        while (wordPointer < word.length() && abbrPointer < abbr.length()) {
            //If same letter between strings, move forward
            if (word.charAt(wordPointer) == abbr.charAt(abbrPointer)) {
                wordPointer++;
                abbrPointer++;
                continue;
            }
            //A character between 0 and 9 inclusive is numeric.
            //"s010n" (has leading zeros), return false
            //Check if outside that range
            if (abbr.charAt(abbrPointer) <= '0' || abbr.charAt(abbrPointer) > '9') { //If true, it is non numeric
                //means we have leading 0
                return false;
            }
            int start = abbrPointer; //Where the number start (substring)
            while (abbrPointer < abbr.length()
                    && abbr.charAt(abbrPointer) >= '0'
                    && abbr.charAt(abbrPointer) <= '9') {
                //Move pointer forward if it is a number
                abbrPointer++; //Use to be the delimiter (length of number)
            }
            int num = Integer.parseInt(abbr.substring(start, abbrPointer));
            wordPointer += num;
        }
        //We reached the end for both pointers
        return wordPointer == word.length() && abbrPointer == abbr.length();
    }


}
