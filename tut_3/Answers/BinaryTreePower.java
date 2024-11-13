import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTreePower {
    public static void main(String[] args) {
        // Setting up the tree structure
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(2);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(6);

        // Finding the most powerful path using DFS
        Result result = new Result();
        findMostPowerfulPath(root, new ArrayList<>(), 0, result);

        // Output the most powerful path and its power sum
        System.out.println("Path with the highest power: " + result.path);
        System.out.println("Power Sum: " + result.maxSum);

        // Performing BFS traversal
        System.out.print("BFS Traversal: ");
        bfsTraversal(root);
        System.out.println();

        // Bonus Challenge: Finding the subtree with the maximum power
        MaxSubtreeResult maxSubtreeResult = new MaxSubtreeResult();
        findMaxPowerSubtree(root, maxSubtreeResult);
        System.out.println("Maximum Power Subtree Sum: " + maxSubtreeResult.maxSum);
        System.out.println("Subtree rooted at node with value: " + maxSubtreeResult.maxNode.value);
    }

    // Class to store the result of the most powerful path
    static class Result {
        int maxSum = Integer.MIN_VALUE;
        List<Integer> path = new ArrayList<>();
    }

    // DFS method to find the most powerful path from root to leaf
    static void findMostPowerfulPath(TreeNode node, List<Integer> currentPath, int currentSum, Result result) {
        if (node == null) {
            return;
        }

        // Include the current node in the path
        currentPath.add(node.value);
        currentSum += node.value;

        // If it's a leaf node, check if the current path has the maximum sum
        if (node.left == null && node.right == null) {
            if (currentSum > result.maxSum) {
                result.maxSum = currentSum;
                result.path = new ArrayList<>(currentPath);
            }
        } else {
            // Continue to traverse the tree
            findMostPowerfulPath(node.left, currentPath, currentSum, result);
            findMostPowerfulPath(node.right, currentPath, currentSum, result);
        }

        // Backtrack to explore other paths
        currentPath.remove(currentPath.size() - 1);
    }

    // BFS traversal method
    static void bfsTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.value + " ");

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    // Bonus Challenge: Class to store the result of the maximum power subtree
    static class MaxSubtreeResult {
        int maxSum = Integer.MIN_VALUE;
        TreeNode maxNode = null;
    }

    // Method to find the subtree with the maximum power
    static int findMaxPowerSubtree(TreeNode node, MaxSubtreeResult result) {
        if (node == null) {
            return 0;
        }

        // Calculate the sum of the left and right subtrees
        int leftSum = findMaxPowerSubtree(node.left, result);
        int rightSum = findMaxPowerSubtree(node.right, result);

        // Total sum including the current node
        int totalSum = node.value + leftSum + rightSum;

        // Update the result if this subtree has a higher sum
        if (totalSum > result.maxSum) {
            result.maxSum = totalSum;
            result.maxNode = node;
        }

        return totalSum;
    }
}
