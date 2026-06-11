package Top75.TwoPointers;

import java.util.Arrays;

/*
Given an integer array nums, write a function to rearrange the array by moving all zeros to the end while keeping the order of non-zero elements unchanged.
Perform this operation in-place without creating a copy of the array.

Input:
nums = [2,0,4,0,9]

Output:
[2,4,9,0,0]
* */
public class MoveZeroes {

    static void main() {

        int[] nums = {1, 0, 4, 0, 5, 6, 0, 0, 9};

        System.out.println(Arrays.toString(moveZeros(nums)));
    }

    public static int[] moveZeros(int[] nums) {

        // Index where the next non zero element should be placed
        int nonZeroIndex = 0;

        for (int i=0; i<nums.length; i++) {

            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[nonZeroIndex];
                nums[nonZeroIndex] = temp;
                nonZeroIndex++;
            }
        }
        return nums;
    }
}
