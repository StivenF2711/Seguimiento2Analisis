public class MatrixSelectionSort {

    public static void main(String[] args) {
        int[][] matrix = {
                {64, 34, 25, 12},
                {22, 11, 90, 5},
                {78, 45, 9, 60}
        };

        int columnIndexToSortBy = 0; // Índice de la columna a ordenar (0 para la primera columna)

        System.out.println("Matriz original:");
        printMatrix(matrix);

        selectionSortMatrix(matrix, columnIndexToSortBy);

        System.out.println("\nMatriz ordenada por la columna " + columnIndexToSortBy + ":");
        printMatrix(matrix);
    }

    public static void selectionSortMatrix(int[][] matrix, int columnIndex) {
        for (int i = 0; i < matrix.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[j][columnIndex] < matrix[minIndex][columnIndex]) {
                    minIndex = j;
                }
            }
            // Intercambiar la fila actual con la fila con el elemento mínimo
            int[] temp = matrix[i];
            matrix[i] = matrix[minIndex];
            matrix[minIndex] = temp;
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
