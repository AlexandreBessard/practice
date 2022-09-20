package topInterviewQuestion.facebook.arraysAndStrings;

//https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3018/
public class ValidateIPAddress {

    public static void main(String[] args) {
        String queryIP = "172.16.254.1";
        String queryIP2 = "2001:0db8:85a3:0:0:8A2E:0370:733";
        String queryIP3 = "256.256.256.256";
        var obj = new ValidateIPAddress();
        System.out.println(obj.validIPAddress(queryIP));
    }

    private final static String NEITHER = "Neither";

    //Approach 2: Divide and Conquer
    //Time: 0(N) -> we count the number of dots requires to parse the entire input string.
    //Space: 0(1)
    public String validIPAddress(final String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            return validateIPV4(IP);
        } else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return validateIPV6(IP);
        }
        return NEITHER;
    }

    private String validateIPV6(String IP) {
        String[] nums = IP.split("\\.");
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : nums) {
            // 1. at least one and not more than 4 hexdigits in one chunk
            if (x.length() == 0 || x.length() > 4) {
                return NEITHER;
            }
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (char ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) { //If character does not occur return -1 else return index of the first occurrence.
                    return NEITHER;
                }
            }
        }
        return "IPV6";
    }

    private String validateIPV4(String IP) {
        //String[] nums = IP.split("\\.", -1);
        String[] nums = IP.split("\\."); //split at each '.'
        for (String x : nums) {
            // Validate integer in range (0, 255):
            if (x.length() == 0 || x.length() > 3)
                return NEITHER;
            //No extra leading zeros
            if (x.charAt(0) == '0' && x.length() != 1)
                return NEITHER;
            //Only digits are allowed
            for (char ch : x.toCharArray()) {
                if (!Character.isDigit(ch)) {
                    return NEITHER;
                }
            }
            //Less than 255
            if (Integer.parseInt(x) > 255) {
                return NEITHER;
            }
        }
        return "IPV4";
    }

}
