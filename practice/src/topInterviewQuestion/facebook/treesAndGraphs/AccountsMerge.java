package topInterviewQuestion.facebook.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/3027/
public class AccountsMerge {

    /*
    After merging the accounts, return the accounts in the following format: the first element of each account
    is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
     */
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        var john1 = new ArrayList<>(List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        var john2 = new ArrayList<>(List.of("John", "johnsmith@mail.com", "john00@mail.com"));
        var john3 = new ArrayList<>(List.of("John", "johnnybravo@mail.com"));
        var mary1 = new ArrayList<>(List.of("Mary", "mary@mail.com"));
        /*
        List<String> acc = new ArrayList<>() {
            {
                add("John,johnsmith@mail.com,john_newyork@mail.com");
                add("John,johnsmith@mail.com,john00@mail.com");
                add("Mary,mary@mail.com");
                add("John,johnnybravo@mail.com");
            }
        };
         */
        accounts.add(john1);
        accounts.add(john2);
        accounts.add(mary1);
        accounts.add(john3);
        var obj = new AccountsMerge();
        for(List<String> account  : obj.accountsMergeDFS(accounts)) {
            System.out.println(account);
        }
    }
    //DFS
    /*
    Time: L(NK log NK) -> N is the number of accounts and K is the maximum length of an account.
    Space: O(NK) -> build adjacency list take 0(NK)
     */
    public List<List<String>> accountsMergeDFS(List<List<String>> accountList) {
        int accountListSize = accountList.size();
        for (List<String> account : accountList) {
            int accountSize = account.size();
            // Building adjacency list
            // Adding edge between first email to all other emails in the account
            String accountFirstEmail = account.get(1);
            for (int j = 2; j < accountSize; j++) {
                String accountEmail = account.get(j);

                if (!adjacent.containsKey(accountFirstEmail)) {
                    adjacent.put(accountFirstEmail, new ArrayList<String>());
                }
                adjacent.get(accountFirstEmail).add(accountEmail);

                if (!adjacent.containsKey(accountEmail)) {
                    adjacent.put(accountEmail, new ArrayList<String>());
                }
                adjacent.get(accountEmail).add(accountFirstEmail);
            }
        }
        // Traverse over all th accounts to store components
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (List<String> account : accountList) {
            String accountName = account.get(0);
            String accountFirstEmail = account.get(1);

            // If email is visited, then it's a part of different component
            // Hence perform DFS only if email is not visited yet
            if (!visited.contains(accountFirstEmail)) {
                List<String> mergedAccount = new ArrayList<>();
                // Adding account name at the 0th index
                mergedAccount.add(0, accountName);

                DFS(mergedAccount, accountFirstEmail);
                Collections.sort(mergedAccount.subList(1, mergedAccount.size()));
                mergedAccounts.add(mergedAccount);
            }
        }
        return mergedAccounts;
    }

    Map<String, List<String>> adjacent = new HashMap<>();
    Set<String> visited = new HashSet<>();
    //Approach 1: DFS
    private void DFS(List<String> mergedAccount, String email) {
        visited.add(email);
        // Add the email vector that contains the current component's emails
        mergedAccount.add(email);
        if (!adjacent.containsKey(email)) {
            return; //If we have only 1 email
        }
        for (String neighbor : adjacent.get(email)) {
            if (!visited.contains(neighbor)) {
                DFS(mergedAccount, neighbor);
            }
        }
    }

    //Approach 2: Disjoint Set Union


}
