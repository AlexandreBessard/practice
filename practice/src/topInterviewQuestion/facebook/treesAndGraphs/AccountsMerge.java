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
        for (List<String> account : obj.accountsMergeDFS(accounts)) {
            System.out.println(account);
        }
    }

    //DFS
    /*
    Time: L(NK log NK) -> N is the number of accounts and K is the maximum length of an account.
    Space: O(NK) -> build adjacency list take 0(NK)
     */
    Map<String, List<String>> adjacent = new HashMap<>(); //Key is the first email
    Set<String> visited = new HashSet<>();
    public List<List<String>> accountsMergeDFS(List<List<String>> accountList) {
        int accountListSize = accountList.size();
        for (List<String> account : accountList) {
            int accountSize = account.size();
            // Building adjacency list
            // Adding edge between first email to all other emails in the account
            String accountFirstEmail = account.get(1); //Get the First email
            for (int j = 2; j < accountSize; j++) { //Loop from second email until the end
                String accountEmail = account.get(j); //Get next email

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
        for (List<String> account : accountList) { //Loop through the input
            String accountName = account.get(0); //Get name
            String accountFirstEmail = account.get(1); //Get the first email

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
    /*
    Time: O(NK log NK)
    Space: O(NK) -> representative and size from DSU store information O(N)
    All emails get stored in emailGroup and component hence space used is O(NK)
     */
    public List<List<String>> accountsMergeDisjointSetUnion(List<List<String>> accountList) {
        int accountListSize = accountList.size();
        DSU dsu = new DSU(accountListSize);
        // Maps email to their component index
        Map<String, Integer> emailGroup = new HashMap<>();
        for (int i = 0; i < accountListSize; i++) {
            int accountSize = accountList.get(i).size();
            for (int j = 1; j < accountSize; j++) {
                String email = accountList.get(i).get(j);
                String accountName = accountList.get(i).get(0);
                // If this is the first time seeing this email then
                // assign component group as the account index
                if (!emailGroup.containsKey(email)) {
                    emailGroup.put(email, i);
                } else {
                    // If we have seen this email before then union this
                    // group with the previous group of the email
                    dsu.unionBySize(i, emailGroup.get(email));
                }
            }
        }
        // Store emails corresponding to the component's representative
        Map<Integer, List<String>> components = new HashMap<Integer, List<String>>();
        for (String email : emailGroup.keySet()) {
            int group = emailGroup.get(email);
            int groupRep = dsu.findRepresentative(group);
            if (!components.containsKey(groupRep)) {
                components.put(groupRep, new ArrayList<String>());
            }
            components.get(groupRep).add(email);
        }

        // Sort the components and add the account name
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (int group : components.keySet()) {
            List<String> component = components.get(group);
            Collections.sort(component);
            component.add(0, accountList.get(group).get(0));
            mergedAccounts.add(component);
        }
        return mergedAccounts;
    }

    static class DSU {
        int[] representative;
        int[] size;

        DSU(int size) {
            representative = new int[size];
            this.size = new int[size];
            for (int i = 0; i < size; i++) {
                // Initially each group is its own representative
                representative[i] = i;
                // Intialize the size of all groups to 1
                this.size[i] = 1;
            }
        }

        //Unite the group that contains "a" with the group that contains "b"
        public void unionBySize(int a, int b) {
            int representativeA = findRepresentative(a);
            int representativeB = findRepresentative(b);
            //If nodes a and b are already belong to the same group, do nothing
            if (representativeA == representativeB)
                return;
            // Union by size: point the representative of the smaller
            // group to the representative of the larger group.
            if (size[representativeA] >= size[representativeB]) {
                this.size[representativeA] += this.size[representativeB];
                this.representative[representativeB] = representativeA;
            } else {
                this.size[representativeB] += this.size[representativeA];
                this.representative[representativeA] = representativeB;
            }
        }

        private int findRepresentative(int x) {
            if (x == this.representative[x]) {
                return x;
            }
            return representative[x] = findRepresentative(representative[x]);
        }
    }


}
