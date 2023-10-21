import java.util.Arrays;

public class HeapSortMatrix {
    public static void heapSortColumns(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int col = 0; col < numCols; col++) {
            int[] column = new int[numRows];

            // Copia la columna actual en un arreglo temporal
            for (int row = 0; row < numRows; row++) {
                column[row] = matrix[row][col];
            }

            // Aplica Heap Sort a la columna
            heapSort(column);

            // Copia la columna ordenada de regreso a la matriz
            for (int row = 0; row < numRows; row++) {
                matrix[row][col] = column[row];
            }
        }
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int rows = 1000;
        int columns = 1000;
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (int) (Math.random() * 10000);
            }
        }

        System.out.println("Matriz original:");
        printMatrix(matrix);

        heapSortColumns(matrix);

        System.out.println("\nMatriz ordenada por columnas:");
        printMatrix(matrix);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }
}
