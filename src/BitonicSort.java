public class BitonicSort {
    public static void bitonicSort(int[] arr, int low, int count, boolean up) {
        if (count > 1) {
            int k = count / 2;
            bitonicSort(arr, low, k, true);
            bitonicSort(arr, low + k, k, false);
            bitonicMerge(arr, low, count, up);
        }
    }

    public static void bitonicMerge(int[] arr, int low, int count, boolean up) {
        if (count > 1) {
            int k = greatestPowerOfTwoLessThan(count);
            for (int i = low; i < low + count - k; i++) {
                compareAndSwap(arr, i, i + k, up);
            }
            bitonicMerge(arr, low, k, up);
            bitonicMerge(arr, low + k, count - k, up);
        }
    }

    public static void compareAndSwap(int[] arr, int i, int j, boolean up) {
        if ((arr[i] > arr[j] && up) || (arr[i] < arr[j] && !up)) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static int greatestPowerOfTwoLessThan(int n) {
        int k = 1;
        while (k > 0 && k < n) {
            k = k << 1;
        }
        return k >> 1;
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

        for (int i = 0; i < matrix.length; i++) {
            arr = matrix[i];
            bitonicSort(arr, 0, arr.length, true);
            printArray(arr);
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
    }
}
