package topInterviewQuestion.facebook.arraysAndStrings;

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
        int n = 6;
        var obj = new ReadNCharactersGivenRead4();
        System.out.println(obj.read(new char[n], n));
    }

    public int read(char[] buf, int n) {
        char[] temp = new char[4];  //Store our read chars from Read4
        int total = 0;
        while (total < n) {
            /*Read and store characters in Temp. Count will store total chars read from Read4*/
            int count = read4(temp);
            System.out.println(count);
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
    String file = "abccde";
    int idx = 0;
    int read4(char[] buf4) {
        int total = idx + 4;
        int idxBuf = 0;
        while(idx < total) {
            if(idx >= file.length()) {
                return 0;
            }
            buf4[idxBuf++] = file.charAt(idx);
            idx++;
        }
        return idx;
    }
}