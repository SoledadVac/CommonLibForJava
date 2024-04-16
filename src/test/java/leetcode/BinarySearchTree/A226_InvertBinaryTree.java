package leetcode.BinarySearchTree;

public class A226_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }

    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode temp;
        TreeNode left = node.left;
        TreeNode right = node.right;

        if (left == null && right == null) {
            return;
        }

        temp = left;
        left = right;
        right = temp;
        node.left = left;
        node.right = right;

        dfs(left);
        dfs(right);

    }
}
