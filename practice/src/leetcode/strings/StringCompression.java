package leetcode.strings;

// https://leetcode.com/problems/string-compression/description/?envType=study-plan-v2&envId=leetcode-75
public class StringCompression {

    public static void main(String[] args) {
        char[] input = new char[] {'a','a','b','b','c','c','c'};
        System.out.println(compress(input));
        String input1 = "a,b,b,b,b,b,b,b,b,b,b,b,b";
        char[] charArray = input1.toCharArray();
        System.out.println(compress(charArray));
    }

    /*
    Time complexity: O(n)
    Space complexity: O(1)
     */
    static int compress(char[] chars) {
        int ans = 0;
        int writeIndex = 0; // Index to write compressed characters
        int readIndex = 0; // Index to read characters

        while (readIndex < chars.length) {
            ans++;
            char currentChar = chars[readIndex]; // Get the current character
            int count = 0; // Count of consecutive occurrences of the current character

            // Count consecutive occurrences of the current character
            while (readIndex < chars.length && chars[readIndex] == currentChar) {
                count++;
                readIndex++;
            }

            // Write the character to the compressed array
            chars[writeIndex++] = currentChar;

            // If count is greater than 1, write count as a string to the compressed array
            if (count > 1) {
                char[] countChars = String.valueOf(count).toCharArray(); // Convert count to character array
                for (char c : countChars) {
                    chars[writeIndex++] = c; // Write each character of the count
                }
            }
        }
        return ans;
    }

}
