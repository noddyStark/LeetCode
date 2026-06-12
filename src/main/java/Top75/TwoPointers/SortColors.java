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

    public static int[] sortColors(int[] nums) {

        int len = nums.length;
        int i = 0; // for 0
        int j = 1; // for 1
        int k = len - 1; // for 2

        while (j <= k) {

            if (nums[j] == 1) {
                j++;
            } else if (nums[j] == 2) {
                swap(nums, j, k);
                k--;
            } else {
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
