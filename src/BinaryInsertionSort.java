public class BinaryInsertionSort {
    public static void binaryInsertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int left = 0;
            int right = i - 1;

            // Realiza una búsqueda binaria para encontrar la posición correcta
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (key < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // Mueve los elementos mayores a la derecha
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }

            // Inserta el elemento en la posición correcta
            arr[left] = key;
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
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
        int[] arr;

        System.out.println("Arreglo original:");


        for (int[] ints : matrix) {
            arr = ints;
            binaryInsertionSort(arr);
            printArray(arr);
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }
}
