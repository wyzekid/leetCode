package easy;

import java.util.Arrays;

/**
 * Solution for https://leetcode.com/problems/maximum-product-of-three-numbers/
 * Ищет максимальное произведение 3-х элементов массива
 */
public class Solution_628 {

    public int getPeriod(int[] nums) {

        int MAX_INDEX = nums.length - 1;

        if (nums.length == 3) {
            return multiply(nums);
        }

        Arrays.sort(nums);

        if (nums[MAX_INDEX] == 0) {
            return 0;
        }

        if (nums[0] >= 0) {
            return getIndexMult(nums, MAX_INDEX, MAX_INDEX - 2, MAX_INDEX - 1);
        }

        if (nums[1] < 0) {
            if (nums[MAX_INDEX] > 0) {
                return Integer.max(getIndexMult(nums, MAX_INDEX, 0, 1),
                        getIndexMult(nums, MAX_INDEX, MAX_INDEX-1, MAX_INDEX-2));
            } else {
                return getIndexMult(nums, MAX_INDEX, MAX_INDEX - 2, MAX_INDEX - 1);
            }
        }


        return getIndexMult(nums, MAX_INDEX, MAX_INDEX - 2, MAX_INDEX - 1);
    }

    private int getIndexMult(int[] input, int MAX_INDEX, int i, int i2) {
        return input[i] * input[i2] * input[MAX_INDEX];
    }


    private int multiply(int[] inputArray) {
        int result = 1;
        for (int i = 0; i < inputArray.length; i++) {
            result *= inputArray[i];
        }
        return result;
    }

}
