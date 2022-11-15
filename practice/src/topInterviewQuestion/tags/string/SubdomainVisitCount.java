package topInterviewQuestion.tags.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/subdomain-visit-count/
public class SubdomainVisitCount {
    /*
    Input: cpdomains = ["9001 discuss.leetcode.com"]
    Output: ["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
    Explanation: We only have one website domain: "discuss.leetcode.com".
    As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.

    -------------------------------------------------------

    Input: cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
    Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
    Explanation: We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times.
    For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
     */
    public static void main(String[] args) {
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        var obj = new SubdomainVisitCount();
        for(String s : obj.subdomainVisits(cpdomains)) {
            System.out.print(s + ", ");
        }
    }

    //Approach 1: HashMap
    /*
    Time complexity: O(N) where N is the length of cpdomains
    Space complexity: O(N) the space is used in our count
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<>();
        for(String domain :  cpdomains) {
            String[] cpInfo = domain.split("\\s+"); //Split between white space ["900", "google.mail.com"]
            String[] frags = cpInfo[1].split("\\."); //Split through each '.' ["google", "mail", "com"]
            int count = Integer.parseInt(cpInfo[0]);
            String cur = "";
            for(int i = frags.length - 1; i >= 0; i--) {
                                  //Separator between each word
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                System.out.println(cur);
                counts.put(cur, counts.getOrDefault(cur, 0) +count);
            }
        }
        List<String> ans = new ArrayList<>();
        //counts :  "com" -> 951
        //          "google.mail.com" -> 900
        for(String dom : counts.keySet()) {
            ans.add("" + counts.get(dom) + " " + dom);
        }
        return ans;
    }

}
