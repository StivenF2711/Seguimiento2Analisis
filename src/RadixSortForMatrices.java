import java.util.Arrays;

public class RadixSortForMatrices {

    public static void radixSort(int[][] matrix) {
        int max = getMaxValue(matrix);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByColumn(matrix, exp);
        }
    }

    public static int getMaxValue(int[][] matrix) {
        int max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

    public static void countingSortByColumn(int[][] matrix, int exp) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] output = new int[rows][cols];

        for (int col = 0; col < cols; col++) {
            int[] count = new int[10];

            for (int row = 0; row < rows; row++) {
                count[(matrix[row][col] / exp) % 10]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int row = rows - 1; row >= 0; row--) {
                int currentDigit = (matrix[row][col] / exp) % 10;
                output[count[currentDigit] - 1][col] = matrix[row][col];
                count[currentDigit]--;
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = output[row][col];
            }
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

        radixSort(matrix);

        // Imprime la matriz ordenada
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }
}
