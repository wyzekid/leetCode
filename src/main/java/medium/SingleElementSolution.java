package medium;

/**
 * Solution for https://leetcode.com/discuss/interview-question/346626/Google-or-Phone-Screen-or-Single-Element
 */
public class SingleElementSolution {

    public int getSingleElement(int... nums) {
        int current_index = 0;
        int max_index = nums.length - 1;

        while (current_index <= max_index - 1) {
            int first_elem = nums[current_index];
            int second_elem = nums[current_index + 1];
            if ((first_elem ^ second_elem) != 0) {
                return first_elem;
            }
            current_index = current_index + 2;

        }
        return 0;
    }
}
