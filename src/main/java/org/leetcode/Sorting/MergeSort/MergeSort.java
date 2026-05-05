package org.leetcode.Sorting.MergeSort;

import java.util.Arrays;

public class MergeSort {

    static void main() {

        int[] arr = {5, 7, 4, 8, 1, 2, 3};

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array = " + Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;


        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {

        int leftArrayLength = mid - left + 1;
        int rightArrayLength = right - mid;

        int[] leftArray = new int[leftArrayLength];
        int[] rightArray = new int[rightArrayLength];

        int k = left;

        for (int i = 0; i < leftArrayLength; i++) {
            leftArray[i] = arr[k];
            k++;
        }

        for (int i = 0; i < rightArrayLength; i++) {
            rightArray[i] = arr[k];
            k++;
        }

        int i = 0;
        int j = 0;
        k = left;

        while (i < leftArrayLength && j < rightArrayLength) {
            if (leftArray[i] < rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
                k++;
            } else {
                arr[k] = rightArray[j];
                j++;
                k++;
            }
        }

        while(i < leftArrayLength) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while(j < rightArrayLength) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
