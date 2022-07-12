package crackingTheCodingInterview.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSet {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2));
        var l = getSubsets(list, 0);
        for(List<Integer> li : l) {
            System.out.println(li);
        }
    }

    static List<List<Integer>> getSubsets(List<Integer> set,
                                          int index) {
        List<List<Integer>> allsubsets;
        if(index == set.size()) {
            allsubsets = new ArrayList<>();
            allsubsets.add(new ArrayList<>());
        } else {
            allsubsets = getSubsets(set, index + 1);
            int item = set.get(index);
            List<List<Integer>> sub = new ArrayList<>();
            for(List<Integer> l : allsubsets) {
                List<Integer> newSub = new ArrayList<>();
                newSub.addAll(l);
                newSub.add(item);
                sub.add(newSub);
            }
            allsubsets.addAll(sub);
        }
        return allsubsets;
    }

}
