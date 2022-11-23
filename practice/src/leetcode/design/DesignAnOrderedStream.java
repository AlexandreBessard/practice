package leetcode.design;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/design-an-ordered-stream/
public class DesignAnOrderedStream {
    /*
    There is a stream of n (idKey, value) pairs arriving in an arbitrary order, where idKey is an integer between 1 and n and value is a string. No two pairs have the same id.

    Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values after each insertion. The concatenation of all the chunks should result in a list of the sorted values.

    Implement the OrderedStream class:

    OrderedStream(int n) Constructs the stream to take n values.
    String[] insert(int idKey, String value) Inserts the pair (idKey, value) into the stream, then returns the largest possible chunk of currently inserted values that appear next in the order.
     */
    public static void main(String[] args) {
        // Note that the values ordered by ID is ["aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"].
        OrderedStream os = new OrderedStream(5);
        System.out.println(os.insert(3, "ccccc")); // Inserts (3, "ccccc"), returns [].
        System.out.println(os.insert(1, "aaaaa")); // Inserts (1, "aaaaa"), returns ["aaaaa"].
        System.out.println(os.insert(2, "bbbbb")); // Inserts (2, "bbbbb"), returns ["bbbbb", "ccccc"].
        System.out.println(os.insert(5, "eeeee")); // Inserts (5, "eeeee"), returns [].
        System.out.println(os.insert(4, "ddddd")); // Inserts (4, "ddddd"), returns ["ddddd", "eeeee"].
// Concatentating all the chunks returned:
// [] + ["aaaaa"] + ["bbbbb", "ccccc"] + [] + ["ddddd", "eeeee"] = ["aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"]
// The resulting order is the same as the order above.
    }

    public static class OrderedStream {
        int ptr;
        String[] res;

        public OrderedStream(int n) {
            ptr = 0;
            res = new String[n];
        }

        public List<String> insert(int idKey, String value) {
            List<String> list = new ArrayList<>(); //Initialise new list for our result
            //Store the value to the array
            res[idKey - 1] = value; // 0-indexed, remove 1 to the id index
            while (ptr < res.length && res[ptr] != null) {
                list.add(res[ptr]);
                ptr++;
            }
            return list;
        }

    }


}
