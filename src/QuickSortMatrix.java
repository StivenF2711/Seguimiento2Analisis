import java.util.Arrays;

public class QuickSortMatrix {
    public static void quickSortRows(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            Arrays.sort(matrix[i]);
        }
    }

    public static void transpose(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int[][] transposed = new int[numCols][numRows];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        for (int i = 0; i < numRows; i++) {
            System.arraycopy(transposed[i], 0, matrix[i], 0, numCols);
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
        printMatrix(matrix);

        quickSortRows(matrix);
        transpose(matrix);
        quickSortRows(matrix);
        transpose(matrix);

        System.out.println("\nMatriz ordenada por columnas:");
        printMatrix(matrix);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
