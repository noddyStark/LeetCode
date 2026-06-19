package Top75.BinarySearch;


/*
Given a sorted array of integers nums and a target value target, write a function to determine if target is in the array.
If target is present in the array, return its index. Otherwise, return -1.

Example 1:
Input:
nums = [-1,0,3,5,9,12], target = 9
Output: 4 (nums[4] = 9)
Example 2:
Input:
nums = [-1,0,3,5,9,12], target = 2
Output: -1 (2 is not in the array, so we return -1.)
*/
public class FindTargetValue {

    static void main() {
        int[] numbers = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int result = findTargetElementIndex(numbers, target);
        System.out.println("Index is = " + result);

    }

    private static int findTargetElementIndex(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (numbers[mid] == target) {
                return mid;
            }

            if (target > numbers[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;

    }
}
