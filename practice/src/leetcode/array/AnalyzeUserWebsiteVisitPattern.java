package leetcode.array;

import java.util.*;

//https://leetcode.com/problems/analyze-user-website-visit-pattern/
public class AnalyzeUserWebsiteVisitPattern {
    /*
    You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].

    A pattern is a list of three websites (not necessarily distinct).

    For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
    The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.

    For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
    Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
    Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
    Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.
     */
    //Correction: https://leetcode.com/problems/analyze-user-website-visit-pattern/discuss/355606/Java-Very-Easy-Understand-With-Explanation
    public static void main(String[] args) {
        String[] username = {"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};
        System.out.println(mostVisitedPattern(username, timestamp, website));
    }

    private final static String WHITE_SPACE = " ";

    /*
    joe, 1, home  X
    joe, 2, about Y
    joe, 3, career Z

    james, 4, home  X
    james, 5, cart
    james, 6, maps
    james, 7, home  X

    mary, 8, home  X
    mary, 9, about, Y
    mary, 10, career Z

     */
    /*
    The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.

    [username[i], website[i], timestamp[i]] indicates that the user
    username[i] visited the website website[i] at time timestamp[i].
     */
    /*
    Time: O(n ^ 3) check 3 patterns by 3 patterns
    //Space: O(N) to store our result
     */
    public static List<String> mostVisitedPattern(String[] username,
                                                  int[] timestamp,
                                                  String[] website) {
        //Key: username, value: (timestamp, website)
        Map<String, List<Pair>> map = new HashMap<>();
        int length = username.length;
        //Each user have Pair<Timestamp, Website>, Structure our data
        for (int i = 0; i < length; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            int currTimestamp = timestamp[i];
            String currWebsite = website[i];
            Pair pair = new Pair(currTimestamp, currWebsite);
            map.get(username[i]).add(pair);
        }
        Map<String, Integer> patternCount = new HashMap<>();
        String res = "";
        for (String usernameKey : map.keySet()) { //For each username
            //Set used to avoid visit the same 3-seq in one user
            Set<String> uniquePattern = new HashSet<>();
            List<Pair> list = map.get(usernameKey);
            //Sort by time by ascending order
            list.sort((a, b) -> a.timestamp - b.timestamp);
            //brutal force O(n ^ 3)
            //Loop by pattern, (3 elements by 3 elements)
            //job -> home about career
            //james -> home cart maps // home cart home // cart maps home
            for (int i = 0; i < list.size() - 2; i++) {
                for (int j = i + 1; j < list.size() - 1; j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                        var sb = new StringBuilder();
                        //Concatenate all website (get 1 pattern)
                        sb.append(list.get(i).website)
                                .append(WHITE_SPACE)
                                .append(list.get(j).website)
                                .append(WHITE_SPACE)
                                .append(list.get(k).website);
                        final String pattern = sb.toString();
                        if (!uniquePattern.contains(pattern)) { // true if not contained
                            //Key: home about career : 1
                            patternCount.put(pattern, patternCount.getOrDefault(pattern, 0) + 1);
                            uniquePattern.add(pattern);
                        }
                        if (res.isEmpty()
                                //True if this pattern appears the most
                                || patternCount.get(res) < patternCount.get(pattern)
                                // same largest score and lexicographically smallest such patter
                                || (patternCount.get(res) == patternCount.get(pattern)
                                && res.compareTo(pattern) > 0)) { //Lexico order
                            res = pattern; //Our new pattern to be returned
                        }
                    }
                }
            }
        }
        // grab the right answer
        String[] r = res.split(" ");
        //The pattern ("home", "about", "career") has score 2 (joe and mary).
        return new ArrayList<>(Arrays.asList(r));
    }

    static class Pair {
        int timestamp;
        String website;

        public Pair(int time, String web) {
            this.timestamp = time;
            this.website = web;
        }
    }
}
