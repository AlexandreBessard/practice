package topInterviewQuestion.facebook.arraysAndStrings;

import java.util.Arrays;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/268/
public class ReadNCharactersGivenRead4 extends Reader4 {

    /*
    File file("abcde"); // File is "abcde", initially file pointer (fp) points to 'a'
    char[] buf4 = new char[4]; // Create buffer with enough space to store characters
    read4(buf4); // read4 returns 4. Now buf4 = "abcd", fp points to 'e'
    read4(buf4); // read4 returns 1. Now buf4 = "e", fp points to end of file
    read4(buf4); // read4 returns 0. Now buf4 = "", fp points to end of file
     */

    /*
    Input: file = "abc", n = 4
    Output: 3
    Explanation: After calling your read method, buf
    should contain "abc". We read a total of 3 characters from the file, so return 3.
    Note that "abc" is the file's content, not buf. buf is the
    destination buffer that you will have to write the results to.


    Input: file = "abcdABCD1234", n = 12
Output: 12
Explanation: After calling your read method, buf
should contain "abcdABCD1234". We read a total of 12 characters from the file, so return 12.
     */
    public static void main(String[] args) {
        //String file = "abc";
        int n = 2;
        String file = "abcde";
        var obj = new ReadNCharactersGivenRead4();

        char[] res = new char[4];
        System.out.println(obj.read(res, 1));
        System.out.println(Arrays.toString(res));

        res = new char[4];
        System.out.println(obj.read(res, 1));
        System.out.println(Arrays.toString(res));

        res = new char[4];
        System.out.println(obj.read(res, 4));
        System.out.println(Arrays.toString(res));
    }
    public int read(char[] buf, int n) {
        this.n = n;
        char[] temp = new char[4];  //Store our read chars from Read4
        int total = 0;
        while (total < n) {
            /*Read and store characters in Temp. Count will store total chars read from Read4*/
            int count = read4(temp);
            /*Even if we read 4 chars from Read4,
            we don't want to exceed N and only want to read chars till N.*/
            count = Math.min(count, n - total);
            //Transfer all the characters read from Read4 to our buffer
            for (int i = 0; i < count; i++) {
                buf[total] = temp[i];
                total++;
            }
            //done. We can't read more characters.
            if (count < 4) break;
        }
        return total;
    }

}

class Reader4 {
    /*
    Parameter:  char[] buf4
    Returns:    int
    buf4[] is a destination, not a source. The results from read4 will be copied to buf4[].
     */
    String file = "abcde";
    int idx = 0;
    int n;
    int read4(char[] buf4) {
        int total = idx + 4;
        int idxBuf = 0;
        while(idx < total && idxBuf < n) {
            if(idx >= file.length()) {
                return idxBuf;
            }
            buf4[idxBuf++] = file.charAt(idx);
            idx++;
        }
        return idxBuf;
    }
}