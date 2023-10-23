import java.util.Arrays;
import java.util.Comparator;

public class TimSortForMatrices {

    private static final int MIN_MERGE = 32;

    public static void timSort(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Convertir la matriz en un arreglo unidimensional
        int[] flatArray = new int[rows * cols];
        int flatIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                flatArray[flatIndex] = matrix[row][col];
                flatIndex++;
            }
        }

        // Aplicar TimSort al arreglo unidimensional
        timSort(flatArray);

        // Convertir el arreglo ordenado de nuevo en una matriz
        flatIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = flatArray[flatIndex];
                flatIndex++;
            }
        }
    }

    public static void timSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i += MIN_MERGE) {
            int left = i;
            int right = Math.min(i + MIN_MERGE - 1, n - 1);
            insertionSort(arr, left, right);
        }

        for (int size = MIN_MERGE; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    public static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        System.arraycopy(arr, left, leftArr, 0, leftSize);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < leftSize) {
            arr[k++] = leftArr[i++];
        }

        while (j < rightSize) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int rows = 1000;
        int columns = 1000;
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }

        System.out.println("Matriz original:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        timSort(matrix);

        // Imprime la matriz ordenada
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }
}
