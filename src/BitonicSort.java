public class BitonicSort {
    public static void bitonicSort(int[] arr) {
        int n = arr.length-1;
        for (int size = 2; size <= n; size = size * 2) {
            for (int stride = size / 2; stride > 0; stride = stride / 2) {
                for (int i = 0; i < n; i++) {
                    boolean ascending = (i / size) % 2 == 0;
                    boolean shouldSwap = (ascending && (i % size < stride)) || (!ascending && (i % size >= stride));
                    if (shouldSwap) {
                        swap(arr, i, i + stride);
                    }
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {68, 42, 27, 3, 15, 62, 74, 99, 5};

        System.out.println("Arreglo original:");
        printArray(arr);

        bitonicSort(arr);

        System.out.println("\nArreglo ordenado:");
        printArray(arr);
    }
}
