package sample2026.neetcode;

public class SubTreeOfAnotherTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String subTreeString = preOrderTranversalString(subRoot);
        String treeString = preOrderTranversalString(root);
        return (treeString.contains(subTreeString));
    }

    private String preOrderTranversalString(TreeNode root) {
        if (root == null) return "null";
        StringBuffer sb = new StringBuffer();
        sb.append(root.val);
        sb.append(preOrderTranversalString(root.left));
        sb.append(preOrderTranversalString(root.right));
        return sb.toString();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        // Build subRoot tree: subRoot = [4, 1, 2]
        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        SubTreeOfAnotherTree solver = new SubTreeOfAnotherTree();
        boolean result = solver.isSubtree(root, subRoot);
        System.out.println("Is subRoot a subtree? " + result); // Output: true
    }


}
