package tut_3;

class BinarySearchTree {
    TreeNode root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        // Insert a new key in the tree
        root = insertRec(root, null, key);
    }

    TreeNode insertRec(TreeNode root, TreeNode parent, int key) {
        // Recursive function to insert a new key in the tree
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

    }

    TreeNode treeSuccessor(TreeNode x) {
    }

    boolean isPrime(int n) {

    }

    TreeNode findPrime(TreeNode T) {

    }
}
