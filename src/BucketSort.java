import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void bucketSort(int[][] matrix, int bucketCount) {
        if (matrix.length == 0 || bucketCount <= 0) {
            return; // La matriz está vacía o el número de cubos es inválido.
        }

        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int max = matrix[0][0];
        int min = matrix[0][0];

        // Encontrar el valor mínimo y máximo en la matriz
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                } else if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        // Crear "buckets" y distribuir elementos en ellos
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int bucketIndex = (int) ((double) (matrix[i][j] - min) / (max - min) * (bucketCount - 1));
                buckets.get(bucketIndex).add(matrix[i][j]);
            }
        }

        // Ordenar cada cubo individualmente (usando otro algoritmo de ordenamiento)
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Concatenar los cubos ordenados
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (Integer value : bucket) {
                matrix[index / numCols][index % numCols] = value;
                index++;
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
                matrix[i][j] = (int) (Math.random() * 10000);
            }
        }
        int bucketCount = 2;

        System.out.println("Matriz original:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        bucketSort(matrix, bucketCount);

        System.out.println("\nMatriz ordenada:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }

}
