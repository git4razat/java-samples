package sample2026.neetcode;

// time complexity - o(n) and space - o(h)- h is height of tree
public class BinaryTreeMaxPathSum {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // Keep track of the global maximum path sum found so far
    private int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateMaxGain(root);
        return maxPathSum;
    }

    private int calculateMaxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 1. Recursively get the maximum path sum from left and right subtrees.
        // If a subtree returns a negative sum, we drop it (replace with 0).
        int leftGain = Math.max(0, calculateMaxGain(node.left));
        int rightGain = Math.max(0, calculateMaxGain(node.right));

        // 2. Calculate the price of the path turning at the current node.
        int currentPathSum = node.val + leftGain + rightGain;

        // 3. Update our global maximum if the current path is better.
        maxPathSum = Math.max(maxPathSum, currentPathSum);

        // 4. For the parent call, return the maximum single-branch contribution
        // this node can provide (node value + max of its left or right child paths).
        return node.val + Math.max(leftGain, rightGain);
    }
}
