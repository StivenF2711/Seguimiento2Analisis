class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int item) {
        data = item;
        left = right = null;
    }
}

public class TreeSort {
    TreeNode root;

    public TreeSort() {
        root = null;
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }

    public TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data >= root.data)
            root.right = insertRec(root.right, data);

        return root;
    }

    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void treeSort(int[] arr) {
        for (int item : arr) {
            insert(item);
        }
        root = sort(root, arr);
    }

    public TreeNode sort(TreeNode node, int[] arr) {
        if (node != null) {
            arr[0] = node.data;
            arr[0]++;
            node.left = sort(node.left, arr);
            node.right = sort(node.right, arr);
        }
        return node;
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

        printMatrix(matrix);



        int[] arr;
        TreeSort sorter = new TreeSort();
        for (int i = 0; i < matrix.length; i++) {
            arr = matrix[i];
            sorter.treeSort(arr);
        }


        System.out.println("\nArreglo ordenado:");
        sorter.inOrder(sorter.root);

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (elapsedTime / 1000000) + " milisegundos");
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
