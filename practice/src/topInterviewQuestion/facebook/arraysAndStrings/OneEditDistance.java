package topInterviewQuestion.facebook.arraysAndStrings;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3015/
public class OneEditDistance {

    /*
    A string s is said to be one distance apart from a string t if you can:

    Insert exactly one character into s to get t.
    Delete exactly one character from s to get t.
    Replace exactly one character of s with a different character to get t.
     */
    public static void main(String[] args) {
        String s = "tob";
        String t = "otc";
        var obj = new OneEditDistance();
        //Output true
        System.out.println(obj.isOneEditDistance(s, t));
    }

    //Time: O(N)
    //Space: O(N) because String are immutable and create substring cost O(N) space
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        //Ensure that s is shorter than t
        if (ns > nt) {
            return isOneEditDistance(t, s);
        }
        //The string are NOT one edit away if the length diff is more than 1
        if (nt - ns > 1) {
            return false;
        }
        for (int i = 0; i < ns; i++) {
            if (s.charAt(i) != t.charAt(i)) { //letters are different
                //If string have the same length
                if (ns == nt) {
                    System.out.println("s: " + s.substring(i + 1));
                    System.out.println("t: " + t.substring(i + 1));
                    //Space: O(N)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else { //If strings have different lengths
                    //Space:  O(N)
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        // If there is no diffs on ns distance
        // the strings are one edit away only if
        // t has one more character.
        return (ns + 1 == nt);
    }


}
