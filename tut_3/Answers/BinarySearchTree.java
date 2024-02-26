package tut_3.Answers;

class BinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        // Insert some values into the BST
        bst.insert(10);
        bst.insert(15);
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(17);

        // Demonstrate the findPrime functionality
        bst.demonstrateFindPrime();
    }

    // Method to demonstrate the findPrime function
    void demonstrateFindPrime() {
        TreeNode primeNode = findPrime(root);
        if (primeNode != null) {
            System.out.println("First prime key found: " + primeNode.key);
        } else {
            System.out.println("No prime key found.");
        }
    }

    TreeNode root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, null, key);
    }

    TreeNode insertRec(TreeNode root, TreeNode parent, int key) {
        if (root == null) {
            TreeNode node = new TreeNode(key);
            node.parent = parent;
            return node;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, root, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, root, key);
        }

        return root;
    }

    TreeNode treeMinimum(TreeNode x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    TreeNode treeSuccessor(TreeNode x) {
        if (x.right != null) {
            return treeMinimum(x.right);
        }
        TreeNode y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    TreeNode findPrime(TreeNode T) {
        TreeNode x = treeMinimum(T);
        while (x != null) {
            if (isPrime(x.key)) {
                return x;
            }
            x = treeSuccessor(x);
        }
        return null; // In case there's no prime number.
    }
}
