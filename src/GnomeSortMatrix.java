public class GnomeSortMatrix {
    public static void gnomeSortMatrixColumns(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int col = 0; col < numCols; col++) {
            int[] column = new int[numRows];

            // Copia la columna actual en un arreglo temporal
            for (int row = 0; row < numRows; row++) {
                column[row] = matrix[row][col];
            }

            gnomeSort(column);

            // Copia la columna ordenada de regreso a la matriz
            for (int row = 0; row < numRows; row++) {
                matrix[row][col] = column[row];
            }
        }
    }

    public static void gnomeSort(int[] arr) {
        int n = arr.length;
        int index = 0;

        while (index < n) {
            if (index == 0) {
                index++;
            }

            if (arr[index] >= arr[index - 1]) {
                index++;
            } else {
                swap(arr, index, index - 1);
                index--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }

        System.out.println("Matriz original:");
        printMatrix(matrix);

        gnomeSortMatrixColumns(matrix);

        System.out.println("\nMatriz ordenada por columnas:");
        printMatrix(matrix);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }
}
