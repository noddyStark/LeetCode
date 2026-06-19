package Top75.BinarySearch;


/*
You are given a sorted array that has been rotated at an unknown pivot point, along with a target value. Develop an algorithm to locate the index of the target value in the array.
If the target is not present, return -1. The algorithm should have a time complexity of O(log n).

Note:
The array was originally sorted in ascending order before being rotated.
The rotation could be at any index, including 0 (no rotation).
You may assume there are no duplicate elements in the array.

Example 1:
Input:
nums = [4,5,6,7,0,1,2], target = 0
Output: 4 (The index of 0 in the array)

Example 2:
Input:
nums = [4,5,6,7,0,1,2], target = 3
Output: -1 (3 is not in the array)

*/
public class SearchInRotatedSortedArray {
    static void main() {
        int[] array = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        System.out.println(search(array, target));
    }

    private static int search(int[] array, int target) {

        // 0, 1, 2, 4, 5, 6, 7

        // input = 4, 5, 6, 7, 0, 1, 2, target = 0

        // 6, 7, 0, 1, 2, 4, 5

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            }

            // If this is true, then the right part must be sorted
            if (array[mid] < array[right]) {

                // target must be in between
                if (array[mid] < target && target < array[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // Left part is sorted
            else if (array[left] < array[mid]) {

                if (array[left] < target && target < array[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }
}
