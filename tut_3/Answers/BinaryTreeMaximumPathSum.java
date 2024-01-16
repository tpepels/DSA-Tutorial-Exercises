package tut_3.Answers;

public class BinaryTreeMaximumPathSum {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static int globalMaxSum;

    // Method to initiate the maximum path sum calculation
    public static int maxPathSum(TreeNode root) {
        globalMaxSum = Integer.MIN_VALUE; // Initialize with the smallest possible value
        maxGain(root);
        return globalMaxSum;
    }

    // Helper method to calculate the maximum gain at each node
    private static int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // Calculate the maximum path sum of the left and right subtrees
        // If the sum is negative, set it to 0 because we can choose not to include the
        // subtree
        int leftMax = Math.max(maxGain(node.left), 0);
        int rightMax = Math.max(maxGain(node.right), 0);

        // Calculate the price to start a new path where `node` is the highest point
        int priceNewPath = node.val + leftMax + rightMax;

        // Update the global maximum sum
        globalMaxSum = Math.max(globalMaxSum, priceNewPath);

        // Return the maximum gain if the node is included in the path
        return node.val + Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        // Constructing a binary tree
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(-25);
        root.right.right.left = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        // Test the maxPathSum method
        System.out.println("Maximum Path Sum: " + maxPathSum(root)); // The output may vary based on the tree structure
    }

}
