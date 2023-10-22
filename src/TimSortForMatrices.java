import java.util.Arrays;
import java.util.Comparator;

public class TimSortForMatrices {

    private static final int MIN_MERGE = 32;

    public static void timSort(int[][] matrix, Comparator<int[]> comparator) {
        int n = matrix.length;
        for (int i = 0; i < n; i += MIN_MERGE) {
            int left = i;
            int right = Math.min(i + MIN_MERGE - 1, n - 1);
            insertionSort(matrix, left, right, comparator);
        }

        for (int size = MIN_MERGE; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(matrix, left, mid, right, comparator);
                }
            }
        }
    }

    public static void insertionSort(int[][] matrix, int left, int right, Comparator<int[]> comparator) {
        for (int i = left + 1; i <= right; i++) {
            int[] current = matrix[i];
            int j = i - 1;
            while (j >= left && comparator.compare(matrix[j], current) > 0) {
                matrix[j + 1] = matrix[j];
                j--;
            }
            matrix[j + 1] = current;
        }
    }

    public static void merge(int[][] matrix, int left, int mid, int right, Comparator<int[]> comparator) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[][] leftArr = new int[leftSize][];
        int[][] rightArr = new int[rightSize][];

        System.arraycopy(matrix, left, leftArr, 0, leftSize);
        System.arraycopy(matrix, mid + 1, rightArr, 0, rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                matrix[k++] = leftArr[i++];
            } else {
                matrix[k++] = rightArr[j++];
            }
        }

        while (i < leftSize) {
            matrix[k++] = leftArr[i++];
        }

        while (j < rightSize) {
            matrix[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {170, 45, 75},
                {90, 802, 24},
                {2, 66, 8},
                {123, 456, 789}
        };

        Comparator<int[]> comparator = (a, b) -> {
            for (int i = 0; i < a.length; i++) {
                int cmp = Integer.compare(a[i], b[i]);
                if (cmp != 0) {
                    return cmp;
                }
            }
            return 0;
        };

        timSort(matrix, comparator);

        // Imprime la matriz ordenada
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

