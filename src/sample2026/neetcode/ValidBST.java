package sample2026.neetcode;

import java.util.LinkedList;
import java.util.Queue;

public class ValidBST {

    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        public TreeNode(int data) {
            this.data = data;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return validateBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validateBSTUtil(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if ((min != null && node.data <= min) || (max != null && node.data >= max)) {
            return false;
        }

        return validateBSTUtil(node.left, min, node.data) && validateBSTUtil(node.right, node.data, max);
    }

    // 3. Main execution block
    public static void main(String[] args) {
        ValidBST solution = new ValidBST();

        // Test Case 1: Valid BST
        //      5
        //     / \
        //    1   7
        Integer[] validTreeInput = {5, 1, 7};
        TreeNode validRoot = buildTree(validTreeInput);
        System.out.println("Test 1 - Is Valid BST? " + solution.isValidBST(validRoot)); // Output: true

        // Test Case 2: Invalid BST
        // (4 is in the right subtree of 5, which violates the BST property)
        //      5
        //     / \
        //    1   6
        //       / \
        //      4   7
        Integer[] invalidTreeInput = {5, 1, 6, null, null, 4, 7};
        TreeNode invalidRoot = buildTree(invalidTreeInput);
        System.out.println("Test 2 - Is Valid BST? " + solution.isValidBST(invalidRoot)); // Output: false
    }

    // Utility Method: Level-order array parser to generate actual Tree structures
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            // Handle left child node conversion
            if (arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.add(current.left);
            }
            i++;

            // Handle right child node conversion
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
}
