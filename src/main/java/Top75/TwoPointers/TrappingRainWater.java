package Top75.TwoPointers;

/*
Write a function to calculate the total amount of water trapped between bars on an elevation map,
where each bar's width is 1. The input is given as an array of n non-negative integers height representing
the height of each bar.

Example:
height = [3, 4, 1, 2, 2, 5, 1, 0, 2]
Output:

10
* */
public class TrappingRainWater {

    public static void main() {
        int[] height = {3, 4, 1, 2, 2, 5, 1, 0, 2};

        System.out.println(trappingWater(height));
    }

    public static Integer trappingWater(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int rightMax = height[right];
        int leftMax = height[left];

        int water = 0;

        while (left < right) {

            if (leftMax < rightMax) {
                left++;
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water = water + leftMax - height[left];
                }
            } else {
                right--;
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    water = water + rightMax - height[right];
                }
            }
        }

        return water;
    }

    // Another Solution
    public int trappingWaterAnotherSolution(int[] height) {

        int maxHeightIndex = 0;
        int maxHeight = Integer.MIN_VALUE;
        int end = height.length - 1;

        for (int i=0; i <= end; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
                maxHeightIndex = i;
            }
        }

        int water = 0;

        /*
        Right Side
        Scan: from end - 1 till maxHeightIndex
        */
        int rightMax = height[end];
        for (int i = end - 1; i > maxHeightIndex; i--) {
            if (height[i] >= rightMax) {
                rightMax = height[i];
            } else {
                water = water + rightMax - height[i];
            }
        }


        /*
        Left Side
        Scan from start till maxHeightIndex - 1
        */
        int leftMax = height[0];
        for (int i = 1; i < maxHeightIndex; i++) {
            if (height[i] >= leftMax) {
                leftMax = height[i];
            } else {
                water = water + leftMax - height[i];
            }
        }

        return water;
    }
}

