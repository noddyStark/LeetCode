package CompanyWise.Rippling.LeetCode;

public class MedianOfTwoSortedArrays_BruteForce_O_1_Space {
    public static void main(String[] args) {

        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        double result = medianOfArrays(nums1, nums2);
        System.out.println("Median is = " + result);
    }

    private static double medianOfArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int i = 0;

        int n = nums2.length;
        int j = 0;

        int size = m + n;

        int index1 = (size / 2) - 1;
        int element1 = -1;

        int index2 = size / 2;
        int element2 = -1;

        int k = 0;

        // nums1 =>  [1, 7, 15]
        // nums2 =>  [4, 9, 12]

        // 1 4 7 9 12 15,  median -> (7+9)/2, index1 = 2, index2 = 3
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {

                if (k == index1) {
                    element1 = nums1[i];
                }

                if (k == index2) {
                    element2 = nums1[i];
                }

                i++;
            } else {
                if (k == index1) {
                    element1 = nums2[j];
                }

                if (k == index2) {
                    element2 = nums2[j];
                }

                j++;
            }
            k++;
        }

        while (i < m) {
            if (k == index1) {
                element1 = nums1[i];
            }

            if (k == index2) {
                element2 = nums1[i];
            }

            i++;
            k++;
        }

        while (j < n) {
            if (k == index1) {
                element1 = nums2[j];
            }

            if (k == index2) {
                element2 = nums2[j];
            }
            j++;
            k++;
        }

        System.out.println("element1 = " + element1 + " element2 = " + element2);

        if (size % 2 == 0) {
            return (element1 + element2) / 2.0;
        } else {
            return element2;
        }

    }
}
