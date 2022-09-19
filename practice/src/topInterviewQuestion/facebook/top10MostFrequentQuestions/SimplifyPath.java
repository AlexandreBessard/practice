package topInterviewQuestion.facebook.top10MostFrequentQuestions;

import java.util.ArrayDeque;

//https://leetcode.com/problems/simplify-path/
public class SimplifyPath {

    public static void main(String[] args) {
        String s = "/home/"; //Output: "/home"
        String s1 = "/../"; //Output: "/"
        String s2 = "/home//foo/"; //Output: "/home/foo"
        System.out.println(simplifyPath(s2));
    }

    //Approach Using Stack (Deque)
    static String simplifyPath(String path) {
        //Initialize a stack
        ArrayDeque<String> deque = new ArrayDeque<>();
        String[] components = path.split("/"); //See java doc but skip latest characters if '/' at the end
        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for(String directory : components) {
            // A no-op for a "." or an empty string
            if(directory.equals(".") || directory.isEmpty()) {
                continue;
            }
            else if(directory.equals("..")) {
                // If the current component is a "..", then
                // we pop an entry from the stack if it's non-empty
                if(!deque.isEmpty()) {
                    deque.pop();
                }
            }
            else {
                // Finally, a legitimate directory name, so we add it
                // to our stack
                deque.add(directory);
            }
        }
        // Stich together all the directory names together
        var result = new StringBuilder();
        while(!deque.isEmpty()) {
            result.append("/");
            result.append(deque.pollFirst()); //Return the first element of the stack (deque)
        }
        return result.length() > 0 ? result.toString() : "/";
    }


}
