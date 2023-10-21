public class MatrixSelectionSort {

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

        selectionSort(matrix);

        System.out.println("Matriz ordenada:");
        printMatrix(matrix);

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }

    public static void selectionSort(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return; // Matriz vacía, no se puede ordenar
        }

        int cols = matrix[0].length;
        for (int col = 0; col < cols; col++) {
            for (int i = 0; i < rows - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < rows; j++) {
                    if (matrix[j][col] < matrix[minIndex][col]) {
                        minIndex = j;
                    }
                }
                // Intercambiar elementos en la matriz
                int temp = matrix[i][col];
                matrix[i][col] = matrix[minIndex][col];
                matrix[minIndex][col] = temp;
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


