package companies.miro;

import java.util.ArrayList;
import java.util.List;

public class Result {

    //[2,3,1,1,4] -> output 2
    //[2,1,1,1,4] -> output 3
    public static int travelToRome(List<Integer> cities) {
        if (cities == null || cities.size() < 1) {
            return 0;
        }
        int[] input = cities.stream().mapToInt(Integer::intValue).toArray();
        int days = 0;
        int previousMaxPosition = 0;
        int currentMaxPosition = 0;
        for (int i = 0; i < input.length - 1; i++) {
            currentMaxPosition = Math.max(currentMaxPosition, i + input[i]);
            if (i == previousMaxPosition) {
                days++;
                previousMaxPosition = currentMaxPosition;
            }
        }
        return days;
    }

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(2);
        input.add(2);
        input.add(1);
        input.add(0);
        input.add(4);
        System.out.println(travelToRome(input));
    }
}
