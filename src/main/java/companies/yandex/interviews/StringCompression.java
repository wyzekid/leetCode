package companies.yandex.interviews;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringCompression {

    public static String compress(String input) {
        if (input == null || input.length() < 1) {
            return "";
        }
        if (input.length() == 1) {
            return input;
        }
        StringBuilder builder = new StringBuilder();
        int currentLength = 1;
        char previousChar = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            if (previousChar == input.charAt(i)) {
                currentLength++;
            } else {
                builder.append(previousChar);
                if (currentLength != 1) {
                    builder.append(currentLength);
                }
                currentLength = 1;
                previousChar = input.charAt(i);
            }
        }
        builder.append(previousChar);
        if (currentLength != 1) {
            builder.append(currentLength);
        }
        return builder.toString();
    }

    public static boolean hasSumSubArray(List<Integer> input, int inputSum) {
        Set<Integer> prefixSums = new HashSet<>();
        int currentSum = 0;
        prefixSums.add(currentSum);
        for (Integer number : input) {
            currentSum+=number;
            prefixSums.add(currentSum);
        }
        for (Integer prefixSum: prefixSums) {
            if (prefixSums.contains(prefixSum - inputSum)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(compress("aff"));
        System.out.println(hasSumSubArray(Arrays.asList(1,3,3,-6,8), 0));
        List<Integer> sourceList = Arrays.asList(0, 1, 2, 3, 4, 5);
    }
}
