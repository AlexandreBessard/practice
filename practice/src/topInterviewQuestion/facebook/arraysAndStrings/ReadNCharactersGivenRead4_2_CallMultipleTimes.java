package topInterviewQuestion.facebook.arraysAndStrings;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/269/
public class ReadNCharactersGivenRead4_2_CallMultipleTimes extends Reader4_2 {

    //TODO: need to be reviewed
    public static void main(String[] args) {
        //char[] buf = {'a', 'b', 'c'};
        var obj = new ReadNCharactersGivenRead4_2_CallMultipleTimes();
        char[] buf = new char[4];
        System.out.println(obj.read(buf, 1));
        buf = new char[4];
        System.out.println(obj.read(buf, 2));
        buf = new char[4];
        System.out.println(obj.read(buf, 1));
    }

    /**
     * @param bufCache: store reading results when we call read4() every time. Once it is empty, call read4() again
     * @param bufPtr: globe variable that points next reading position in bufCache. It should always < 4
     * @param bufCount: count number of elements in bufCache. Usually equals to 4, but may less than 4 when reach end of input
     */
    char[] bufCache = new char[4];
    //char[] bufCache = {'a', 'b', 'c'};
    int bufPtr = 0;
    int bufCount = 0;

    public int read(char[] buf, int n) {
        int nIndex = 0;
        // fill out every position until reach n
        while (nIndex < n) {
            // only if bufPtr does not reach the end of bufCache array, we can assign value from bufCache to final buf array
            if (bufPtr < bufCount) {
                buf[nIndex++] = bufCache[bufPtr++]; //buf is a destination, not a source
            }
            // if we already used all characters from bufCache, we need to read new characters by calling read4()
            // and then fill the bufCache
            else {
                bufCount = read4(bufCache); //Read a new character
                bufPtr = 0;
                // if no more characters we can read, we should break the entire loop and return 0
                if (bufCount == 0) { //No more character left
                    break;
                }
            }
        }
        return nIndex;
    }
}

class Reader4_2 {
    char[] bufCache = new char[4];
    {
        bufCache[0] = 'a';
        bufCache[1] = 'b';
        bufCache[2] = 'c';
        bufCache[3] = 0;
    }
    int i = 0;
    int read4(char[] buf4) {
        //Because bufCache has 3 letters in that case.
        if(bufCache[i] == 0) {
            return 0;
        }
        buf4[i] = bufCache[i++];
        return i;
    }
}