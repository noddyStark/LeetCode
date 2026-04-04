package org.leetcode.Sorting;

public class BinarySearch_Recursive {

    public static void main(String[] args) {
        int[] sortedArray = {2, 4, 6, 8, 10, 12, 13, 15, 16, 19, 23, 27};
        int numberToBeFound = 15;
        int result = binarySearch(sortedArray, 0, sortedArray.length - 1, numberToBeFound);

        if (result == -1) {
            System.out.println("Element not present in the array!!");
        } else {
            System.out.println("Number Present in the Array at index = " + result);
        }
    }


    private static int binarySearch(int[] array, int low, int high, int key) {

        if (high >= low) {
            int mid = low + (high - low) / 2;

            if (array[mid] == key) {
                return mid;
            }

            if (array[mid] > key) {
                return binarySearch(array, low, mid - 1, key);
            }

            return binarySearch(array, mid + 1, high, key);
        }

        return -1;
    }
}
