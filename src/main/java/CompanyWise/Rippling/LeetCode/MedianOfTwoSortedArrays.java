package CompanyWise.Rippling.LeetCode;

/*
4. Median of Two Sorted Arrays
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
*/
public class MedianOfTwoSortedArrays {


    static void main() {
        int[] nums1 = {1, 3, 8, 9};
        int[] nums2 = {2, 4, 7, 10, 11, 12, 13, 15};

    }


    /*
    partition1 = number of elements we take from nums1 into LEFT side
    partition2 = number of elements we take from nums2 into LEFT side

    We basically want to see what values fit in partition1 from nums1 and nums2, so that our condition gets satisfied.

        partition1   LEFT                           RIGHT
     *                              |
     *                              |
     *                              |
     *                              |
     *       --------------------------------------------------
     *  partition2                  |
     *                              |
     *                              |
     *                              |

    Median of Two Sorted Arrays — Binary Search Partition

    nums1 → [1, 3, 8, 9]
    nums2 → [2, 4, 7, 10, 11, 12, 13, 15]

                    LEFT PARTITION           |        RIGHT PARTITION
    --------------------------------------------------------------------------------
    nums1 →   1,  3,  8,  9                  |        15
                ↑
            leftMaxNums1 = 9                          rightMinNums1 = 15

    nums2 →   2,  4,  7, 10, 11               |    12, 13
                       ↑
                  leftMaxNums2 = 11                   rightMinNums2 = 12

    --------------------------------------------------------------------------------

    Total elements = 12
    LEFT size = (m + n + 1) / 2 = 6

    partition1 = 4 (from nums1)
    partition2 = 6 - 4 = 2 (from nums2)

    --------------------------------------------------------------------------------

    VALID CONDITION:

        leftMaxNums1 <= rightMinNums2   →   9  <= 12  ✅
        leftMaxNums2 <= rightMinNums1   →  11  <= 15  ✅

    --------------------------------------------------------------------------------

    Since total is EVEN:

        median = ( max(leftMaxNums1, leftMaxNums2) + min(rightMinNums1, rightMinNums2) ) / 2
               = ( max(9, 11) + min(15, 12) ) / 2
               = ( 11 + 12 ) / 2
               = 11.5

    --------------------------------------------------------------------------------

    Intuition:

    - We binary search on nums1 (smaller array)
    - Pick partition1 → derive partition2 automatically
    - Ensure LEFT side has correct size
    - Ensure all LEFT elements <= RIGHT elements

    Key Formula:
        partition2 = (m + n + 1)/2 - partition1
*/

    private static double medianOfTwoSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            return medianOfTwoSortedArrays(nums2, nums1);
        }

        int low = 0;
        int high = m;

        while (low <= high) {

            int partition1 = low + (high - low) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;


            /*
             * leftMaxNums1 -> Largest element on LEFT side of nums1
             * rightMinNums1 -> Smallest element on RIGHT side of nums1
             * leftMaxNums2 -> Largest element on LEFT side of nums2
             * rightMinNums2 -> Smallest element on RIGHT side of nums2
             */

            // Left Half
            int leftMaxNums1 = partition1 == 0? Integer.MIN_VALUE : nums1[partition1 - 1];
            int leftMaxNums2 = partition2 == 0? Integer.MIN_VALUE: nums2[partition2 - 1];


            // Right Half
            int rightMinNums1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];
            int rightMinNums2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // Correct partition
            if (leftMaxNums1 <= rightMinNums2 && leftMaxNums2 <= rightMinNums1) {

                if ((m + n) % 2 == 0) {
                    return (Math.max(leftMaxNums1, leftMaxNums2) + Math.min(rightMinNums1, rightMinNums2)) / 2.0;
                } else {
                    return Math.max(leftMaxNums1, leftMaxNums2);
                }
            }

            // Move left
            else if (leftMaxNums1 > rightMinNums2) {
                high = partition1 - 1;
            }
            // Move right
            else {
                low = partition1 + 1;
            }
        }

        return 0.0;
    }
}
