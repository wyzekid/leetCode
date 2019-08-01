package medium;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingleElementSolutionTest {

    @Test
    public void getSingleElement() {
        int[] array_1 = {1, 1, 2, 1};
        int[] array_2 = {2, 2, 3, 2, 2};
        int[] array_3 = {3, 3, 3, 3};
        SingleElementSolution solution = new SingleElementSolution();

        assertEquals(2, solution.getSingleElement(array_1));
        assertEquals(3, solution.getSingleElement(array_2));
        assertEquals(0, solution.getSingleElement(array_3));
    }
}