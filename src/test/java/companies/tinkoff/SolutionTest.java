package companies.tinkoff;

import org.junit.Test;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void sortedSquaresTest() {
        Solution solution = new Solution();
        int[] input_1 = {};
        int[] input_2 = {3};
        int[] input_3 = {-3, -2, -1};
        int[] input_4 = {0, 2, 5, 10};
        int[] input_5 = {-7, -6, -2, 0, 1, 3};
        int[] input_6 = {-5, -3, -2, 5, 8};
        int[] input_7 = {-10, -3, 1, 3, 10};

        int[] expected_2 = {9};
        int[] expected_3 = {1, 4, 9};
        int[] expected_4 = {0, 4, 25, 100};
        int[] expected_5 = {0, 1, 4, 9, 36, 49};
        int[] expected_6 = {4, 9, 25, 25, 64};
        int[] expected_7 = {1, 9, 9, 100, 100};

        assertArrayEquals(input_1, solution.sortedSquares(input_1));
        assertArrayEquals(expected_2, solution.sortedSquares(input_2));
        assertArrayEquals(expected_3, solution.sortedSquares(input_3));
        assertArrayEquals(expected_4, solution.sortedSquares(input_4));
        assertArrayEquals(expected_5, solution.sortedSquares(input_5));
        assertArrayEquals(expected_6, solution.sortedSquares(input_6));
        assertArrayEquals(expected_7, solution.sortedSquares(input_7));
    }
}