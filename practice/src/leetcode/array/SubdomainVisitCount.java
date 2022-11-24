package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/subdomain-visit-count/
public class SubdomainVisitCount {
    /*
A website domain "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com" and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.

A count-paired domain is a domain that has one of the two formats "rep d1.d2.d3" or "rep d1.d2" where rep is the number of visits to the domain and d1.d2.d3 is the domain itself.

For example, "9001 discuss.leetcode.com" is a count-paired domain that indicates that discuss.leetcode.com was visited 9001 times.
Given an array of count-paired domains cpdomains, return an array of the count-paired domains of each subdomain in the input. You may return the answer in any order.

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
        for (String s : obj.subdomainVisits(cpdomains)) {
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
        for (String domain : cpdomains) {
            String[] cpInfo = domain.split("\\s+"); //Split between white space ["900", "google.mail.com"]
            String[] currDomains = cpInfo[1].split("\\."); //Split through each '.' ["google", "mail", "com"]
            int count = Integer.parseInt(cpInfo[0]);
            var strBuilder = new StringBuilder(); //Initialize empty String
            for (int i = currDomains.length - 1; i >= 0; i--) { //Start at the end
                //Separator between each word
                //If it is the latest letter, append "" else .
                if(i < currDomains.length - 1) { //true if the letter is NOT the latest one
                    strBuilder.append("."); //Separate between 2 words
                }
                strBuilder.append(currDomains[i]); //Append current word
                String currString = strBuilder.toString(); // Create a copy, don't share the array
                counts.put(currString, counts.getOrDefault(currString, 0) + count);
            }
        }
        /*
        map:
        count -> 951
        org.wiki -> 5
        com.mail.google -> 900
        ....
         */
        final List<String> ans = new ArrayList<>();
        //counts :  "com" -> 951
        //          "google.mail.com" -> 900
        final String EMPTY_SPACE = " ";
        for (String dom : counts.keySet()) {
            StringBuilder strBuilder = new StringBuilder()
                    .append(EMPTY_SPACE)
                    .append(counts.get(dom)) //Get num count
                    .append(EMPTY_SPACE)
                    .append(dom); // Domain name which comes from the key
            ans.add(strBuilder.toString());
        }
        return ans;
    }

}
