package algorithms.greedy;

public class GreedyTemplate {

    //https://www.baeldung.com/java-greedy-algorithms

    /*
    Remember: Here is where we perform a greedy choice. As such, every time we call this method,
    we'll choose one and only one element from the list and move on: We won't ever go back on our decisions!

    The essence of greedy algorithm is to find the best solution for the current iteration in order to reach the goal.
    It never takes back its choices but iteratively constructs the final solution.
    -------------------------------------------------------------------------------------
     */
    /*
--------------------------------------------------------------------------------------

    int currentLevel = 0;
    final int maxLevel = 3;

    public long findMostFollowersPath(String account) {
    long max = 0;
    SocialUser toFollow = null;

    List followers = sc.getFollowers(account);          //Get the followers
    for (SocialUser el : followers) {                   //Loop through each follower
        long followersCount = el.getFollowersCount();   //Get the numbers of each follower
        if (followersCount > max) {                     //If true get the one with the most followers
            toFollow = el;                              //Choose one element from that list and move on
            max = followersCount;
        }
    }
    if (currentLevel < maxLevel - 1) {
        currentLevel++;
        max += findMostFollowersPath(toFollow.getUsername());       //Recursive call
    }
    return max;
}
-----------------------------------------------------------------------------------------

     */


}
