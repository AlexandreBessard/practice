package crackingTheCodingInterview.DP;

public class MagicIndex {

    public static void main(String[] args) {
        int[] array2 = {
          -10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13
        };
        int[] array = {1, 1, 2};
        System.out.println(magicSlow(array));
        System.out.println(magicFast(array));
        System.out.println(magicFastDuplicate(array2));
    }

    //With duplicate nums
    static int magicFastDuplicate(int[] array) {
        return magicFastDupl(array, 0, array.length - 1);
    }
    private static int magicFastDupl(int[] array,
                                     int start,
                                     int end) {
        if(end < start)
            return -1;

        int midIndex = (start + end) / 2;
        int midValue = array[midIndex];
        if(midValue == midIndex)
            return midIndex;

        //Search left
        int leftIndex = Math.min(midIndex - 1, midValue);
        int left = magicFastDupl(array, start, leftIndex);
        if(left >= 0) {
            return left;
        }
        //Seach right
        int rightIndex = Math.max(midIndex + 1, midValue);
        int right = magicFastDupl(array, rightIndex, end);
        return right;
    }




    static int magicFast(int[] array) {
        return magicFast(array, 0, array.length - 1);
    }
    private static int magicFast(int[] array, int start, int end) {
        if(end < start)
            return -1;
        int mid = (start + end) / 2;
        if(array[mid] == mid) {
            return mid;
        } else if(array[mid] > mid) {
            return magicFast(array, start, mid - 1);
        } else {
            return magicFast(array, mid + 1, end);
        }
    }


    //Brut force
    static int magicSlow(int[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == i)
                return i;
        }
        return -1;
    }
}
