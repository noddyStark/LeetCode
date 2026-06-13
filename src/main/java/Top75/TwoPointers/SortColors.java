package Top75.TwoPointers;

import java.util.Arrays;

/*
Write a function to sort a given integer array nums in-place (and without the built-in sort function),
where the array contains n integers that are either 0, 1, and 2 and represent the colors red, white, and blue. Arrange the objects so that same-colored ones are adjacent, in the order of red, white, and blue (0, 1, 2).

0 -> red
1 -> white
2 -> blue

order that we want ->  red(0), white(1), and blue(2)

Input:
nums = [2,1,2,0,1,0,1,0,1]
Output:
[0,0,0,1,1,1,1,2,2]
* */
public class SortColors {

    static void main() {

        int[] nums = {1, 0, 2, 1, 2, 2, 0, 1, 0, 1, 2};
        System.out.println(Arrays.toString(sortColors(nums)));
    }

    /*
    Dutch National Flag algorithm.

     We are dividing the array into 3 regions while scanning:
     0s region | 1s region | unknown region | 2s region

     Use three pointers:

    i -> position where next 0 should go
    j -> current element being checked
    k -> position where next 2 should go

    Correct intuition

    At any point:

    [0 ... i-1]      -> all 0s
    [i ... j-1]      -> all 1s
    [j ... k]        -> unknown
    [k+1 ... end]    -> all 2s
    * */
    public static int[] sortColors(int[] nums) {

        int len = nums.length;
        int i = 0; // for 0
        int j = 1; // for 1
        int k = len - 1; // for 2

        while (j <= k) {

            if (nums[j] == 1) {
                // Case 1: nums[j] == 1
                // A 1 is already in the middle region, so just move forward.
                j++;
            } else if (nums[j] == 2) {
                // Case 2: nums[j] == 2
                // A 2 belongs at the end, so swap it with nums[k].
                // swap(nums, j, k);
                // k--;
                swap(nums, j, k);
                k--;
            } else {
                // Case 3: nums[j] == 0
                // A 0 belongs at the front, so swap it with nums[i].
                // Because after swapping, the value that came from i is already processed. It is either a 1 or the same 0, so j can move forward.
                swap(nums, j, i);
                j++;
                i++;
            }
        }
        return nums;
    }

    public static void swap(int[] nums, int j, int k) {
        int temp = nums[j];
        nums[j] = nums[k];
        nums[k] = temp;
    }
}
