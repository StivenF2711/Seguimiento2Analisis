class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int item) {
        key = item;
        left = right = null;
    }
}

public class MatrixTreeSort {

    TreeNode root;

    public MatrixTreeSort() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    void treeSortColumn(int[] columnToSort) {
        for (int i = 0; i < columnToSort.length; i++) {
            insert(columnToSort[i]);
        }
    }

    void treeToSortedArray(TreeNode root, int[] columnToSort, int index) {
        if (root != null) {
            treeToSortedArray(root.left, columnToSort, index);
            columnToSort[index] = root.key;
            index++;
            treeToSortedArray(root.right, columnToSort, index);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {64, 34, 25, 12},
                {22, 11, 90, 5},
                {78, 45, 9, 60}
        };

        int columnIndexToSortBy = 0; // Índice de la columna a ordenar

        System.out.println("Matriz original:");
        printMatrix(matrix);

        int[] columnToSort = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            columnToSort[i] = matrix[i][columnIndexToSortBy];
        }

        MatrixTreeSort treeSort = new MatrixTreeSort();
        treeSort.treeSortColumn(columnToSort);

        treeSort.treeToSortedArray(treeSort.root, columnToSort, 0);

        for (int i = 0; i < matrix.length; i++) {
            matrix[i][columnIndexToSortBy] = columnToSort[i];
        }

        System.out.println("\nMatriz ordenada por la columna " + columnIndexToSortBy + ":");
        printMatrix(matrix);
    }

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
