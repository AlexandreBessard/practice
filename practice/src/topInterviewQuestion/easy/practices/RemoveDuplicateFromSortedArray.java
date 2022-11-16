package topInterviewQuestion.easy.practices;

public class RemoveDuplicateFromSortedArray {

    public static void main(String[] args) {
        //0,0,1,1,1,2,2,3,3,4
        //Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicate(nums));
    }

    //Do not allocate extra-space
    /*
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static int removeDuplicate(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length < 2)
            return 1;
        int i = 0; //Pointer for the first element
        for(int j = 1; j < nums.length; j++) { //Pointer for the second element
            if(nums[i] != nums[j]) { //Compare these 2 elements
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

}
