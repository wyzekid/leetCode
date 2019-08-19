package companies.tinkoff;

public class Solution {
    public int[] sortedSquares(int[] A) {
        int middle = 0;
        boolean middleZero;
        int[] result = new int[0];
        for (int i = 0; i< A.length-1; i ++ ) {
            if (A[i] == 0) {
                middle = i;
                break;
            }
            if (A[i] < 0 && A[i+1]> 0) {
                if (Math.abs(A[i]) <= Math.abs(A[i+1])){
                    middle = i;
                } else {
                    middle = i+1;
                }
                break;
            }
        }
        middleZero = A[middle] == 0;
        int left = middle;
        int right = middle;
        int resultIndex = 0;
        while (right <= A.length-1 || left >=0) {
            if (middleZero) {
                result[resultIndex] = 0;
                resultIndex++;
                continue;
            }
            right++;
            left--;
            if (Math.abs(left) < Math.abs(right) && !middleZero) {
                result[resultIndex] = A[left] * A[left];
            } else if (Math.abs(left) > Math.abs(right) && !middleZero) {
                result[resultIndex] = A[right] * A[right];
            }
            resultIndex++;
        }
        return result;
    }
}
