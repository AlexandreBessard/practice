package topInterviewQuestion.leetcode.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//https://leetcode.com/explore/interview/card/top-interview-questions-easy/98/design/670/
public class ShuffleAnArray {

    public static void main(String[] args) {
        var obj = new Approach2(new int[]{10, 20, 30, 40});
        obj.shuffle();
    }

}
//Approach 2: Fisher-Yates Algo
class Approach2 {
    private int[] array;
    private int[] original;
    Random rand = new Random();

    public Approach2(int[] nums) {
        this.array = nums;
        this.original = nums.clone(); //Must clone the array!
    }

    //min: include, max : exclude
    private int randRange(int min, int max) {
        //Means take random number from min (included) to max (excluded)
        return rand.nextInt(max - min) + min;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int[] shuffle() {
        for(int i = 0; i < array.length; i++) {
            int randomIdx = randRange(i, array.length);
            swap(i, randomIdx);
        }
        return array;
    }

    public int[] reset() {
        this.array = original;
        //Keep the original untouched from the Stack
        this.original = original.clone(); //Create a new reference
        return original;
    }
}



//Approach 1: Brut Force Accepted
/*
Time complexity: O(n²) caused by list.remove() method.
Space complexity: O(n) cause by the reset method
 */
class Approach1 {

    private int[] array;
    private int[] original;
    private Random rand = new Random();

    public Approach1(int[] nums) {
        this.array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }

    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();
        for(int i = 0; i < array.length; i++) {
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }
        return array;
    }
    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<>();
        for(int i = 0; i < this.array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }
}
