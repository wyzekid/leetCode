package companies.tinkoff;

public class Solution {
    public int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        int MAX_INDEX = A.length - 1;
        if (A.length == 0) {
            return result;
        }
        if (A[0] >= 0) {
            for (int i = 0; i <= MAX_INDEX; i++) {
                result[i] = powSqr(A[i]);
            }
            return result;
        }
        if (A[MAX_INDEX] <= 0) {
            for (int i = MAX_INDEX; i >= 0; i--) {
                result[MAX_INDEX - i] = powSqr(A[i]);
            }
            return result;
        }

        int middleIndex = 0;
        for (int i = 0; i <= MAX_INDEX - 1; i++) {
            if (A[i] == 0) {
                middleIndex = i;
                break;
            }
            if (A[i] < 0 && A[i + 1] > 0) {
                if (Math.abs(A[i]) <= Math.abs(A[i + 1])) {
                    middleIndex = i;
                } else {
                    middleIndex = i + 1;
                }
                break;
            }
        }
        int leftIndex = middleIndex;
        int rightIndex = middleIndex;
        int resultIndex = 0;
        while ((leftIndex >= 0 || rightIndex <= MAX_INDEX) && resultIndex <= MAX_INDEX) {
            if (resultIndex == 0) {
                result[resultIndex] = powSqr(A[middleIndex]);
                resultIndex++;
                continue;
            }
            leftIndex--;
            rightIndex++;
            boolean leftIndexInBound = leftIndex >= 0;
            boolean rightIndexInBound = rightIndex <= MAX_INDEX;
            if (!leftIndexInBound) {
                result[resultIndex] = powSqr(A[rightIndex]);
                resultIndex++;
                continue;
            } else if (!rightIndexInBound) {
                result[resultIndex] = powSqr(A[leftIndex]);
                resultIndex++;
                continue;
            } else {
                if (Math.abs(A[leftIndex]) <= Math.abs(A[rightIndex])) {
                    result[resultIndex] = powSqr(A[leftIndex]);
                    result[++resultIndex] = powSqr(A[rightIndex]);
                } else if (Math.abs(A[leftIndex]) > Math.abs(A[rightIndex])) {
                    result[resultIndex] = powSqr(A[rightIndex]);
                    result[++resultIndex] = powSqr(A[leftIndex]);
                }
            }
            resultIndex++;
        }
        return result;
    }

    private int powSqr(int number) {
        return number * number;
    }
}
