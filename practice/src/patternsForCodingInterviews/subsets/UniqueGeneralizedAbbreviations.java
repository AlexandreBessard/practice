package patternsForCodingInterviews.subsets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://designgurus.org/path-player?courseid=grokking-the-coding-interview&unit=grokking-the-coding-interview_1628744084164_72Unit
public class UniqueGeneralizedAbbreviations {

    public static void main(String[] args) {
        List<String> result = generateGeneralizedAbbreviation("BAT");
        System.out.println("Generalized abbreviation are: " + result);

        result = generateGeneralizedAbbreviation("code");
        System.out.println("Generalized abbreviation are: " + result);
    }

    /*
    Recursive
     */
    public static List<String> generateGeneralizedAbbreviationRecursive(String word) {
        List<String> result = new ArrayList<String>();
        generateAbbreviationRecursive(word, new StringBuilder(), 0, 0, result);
        return result;
    }

    private static void generateAbbreviationRecursive(String word, StringBuilder abWord,
                                                      int start, int count, List<String> result) {

        if (start == word.length()) {
            if (count != 0)
                abWord.append(count);
            result.add(abWord.toString());
        } else {
            // continue abbreviating by incrementing the current abbreviation count
            generateAbbreviationRecursive(word, new StringBuilder(abWord),
                    start + 1, count + 1, result);

            // restart abbreviating, append the count and the current character to the string
            if (count != 0)
                abWord.append(count);
            generateAbbreviationRecursive(word,
                    new StringBuilder(abWord).append(word.charAt(start)), start + 1, 0, result);
        }
    }

    /*
    Iterative
     */
    public static List<String> generateGeneralizedAbbreviation(String word) {
        int wordLen = word.length();
        List<String> result = new ArrayList<String>();
        Queue<AbbreviatedWord> queue = new LinkedList<>();
        //Start with empty word
        queue.add(new AbbreviatedWord(new StringBuilder(), 0, 0));
        while (!queue.isEmpty()) {
            //Take combinations from the previous step
            AbbreviatedWord abWord = queue.poll();
            //Abbrevate
            if (abWord.start == wordLen) { //Abbreviate the current character
                if (abWord.count != 0)
                    abWord.str.append(abWord.count);
                result.add(abWord.str.toString());
            } else {
                //Skip
                // continue abbreviating by incrementing the current abbreviation count
                queue.add(new AbbreviatedWord(new StringBuilder(abWord.str),
                        abWord.start + 1, abWord.count + 1));

                // restart abbreviating, append the count and the current character to the string
                if (abWord.count != 0)
                    abWord.str.append(abWord.count);

                queue.add(
                        new AbbreviatedWord(new StringBuilder(
                                abWord.str).append(word.charAt(abWord.start)),
                                abWord.start + 1,
                                0));
            }
        }

        return result;
    }

    static class AbbreviatedWord {
        StringBuilder str;
        int start;
        int count;
        AbbreviatedWord(StringBuilder str, int start, int count) {
            this.str = str;
            this.start = start;
            this.count = count;
        }
    }

}
