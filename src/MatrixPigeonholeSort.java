public class MatrixPigeonholeSort {

    public static void pigeonholeSort(int[][] matrix, int columnIndex) {
        int numRows = matrix.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Encuentra el valor mínimo y máximo en la columna
        for (int i = 0; i < numRows; i++) {
            int current = matrix[i][columnIndex];
            if (current < min) {
                min = current;
            }
            if (current > max) {
                max = current;
            }
        }

        int range = max - min + 1;
        int[] pigeonholes = new int[range];

        // Contar la frecuencia de cada elemento en la columna
        for (int i = 0; i < numRows; i++) {
            pigeonholes[matrix[i][columnIndex] - min]++;
        }

        int rowIndex = 0;

        // Reorganizar la matriz con los elementos ordenados
        for (int i = 0; i < range; i++) {
            while (pigeonholes[i] > 0) {
                matrix[rowIndex][columnIndex] = i + min;
                rowIndex++;
                pigeonholes[i]--;
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

    public static void main(String[] args) {
        int rows = 1000;
        int columns = 1000;
        int[][] matrix = new int[rows][columns];
        long startTime = System.nanoTime();

        // Llenar la matriz con valores (por ejemplo, números enteros aleatorios)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Puedes llenar la matriz con los valores que desees
                matrix[i][j] = (int) (Math.random() * 10000);
            }
        }

        int columnIndexToSortBy = 0; // Índice de la columna a ordenar

        System.out.println("Matriz original:");
        printMatrix(matrix);

        for (int i = 0; i < matrix[0].length; i++) {
            pigeonholeSort(matrix, columnIndexToSortBy);
            columnIndexToSortBy++;
        }

        System.out.println("\nMatriz ordenada por la columna " + columnIndexToSortBy + ":");
        printMatrix(matrix);

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }
}
