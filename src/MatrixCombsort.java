public class MatrixCombsort {

    public static void main(String[] args) {
        int[][] matrix = {
                {64, 34, 25, 12},
                {22, 11, 90, 5},
                {78, 45, 9, 60}
        };

        System.out.println("Matriz original:");
        printMatrix(matrix);

        // Ordenar filas
        sortMatrixRows(matrix);
        System.out.println("\nMatriz ordenada por filas:");
        printMatrix(matrix);

        // Restaurar la matriz original
        matrix = new int[][]{
                {64, 34, 25, 12},
                {22, 11, 90, 5},
                {78, 45, 9, 60}
        };

        // Ordenar columnas
        sortMatrixColumns(matrix);
        System.out.println("\nMatriz ordenada por columnas:");
        printMatrix(matrix);
    }

    public static void sortMatrixRows(int[][] matrix) {
        for (int[] row : matrix) {
            combsort(row);
        }
    }

    public static void sortMatrixColumns(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int col = 0; col < numCols; col++) {
            int[] column = new int[numRows];
            for (int row = 0; row < numRows; row++) {
                column[row] = matrix[row][col];
            }
            combsort(column);
            for (int row = 0; row < numRows; row++) {
                matrix[row][col] = column[row];
            }
        }
    }

    public static void combsort(int[] arr) {
        int gap = arr.length;
        double shrinkFactor = 1.3;
        boolean sorted = false;

        while (!sorted) {
            gap = (int) (gap / shrinkFactor);
            if (gap > 1) {
                sorted = false;
            } else {
                gap = 1;
                sorted = true;
            }

            int i = 0;
            while (i + gap < arr.length) {
                if (arr[i] > arr[i + gap]) {
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    sorted = false;
                }
                i++;
            }
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
}