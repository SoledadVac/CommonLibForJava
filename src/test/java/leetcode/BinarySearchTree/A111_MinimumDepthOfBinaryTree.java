package leetcode.BinarySearchTree;

public class A111_MinimumDepthOfBinaryTree {


    public int minDepth(TreeNode root) {
        return dfs(root,0);
    }

    public int dfs(TreeNode root, int dep) {
        if (root == null) {
            return dep;
        }
        if (root.left == null && root.right == null) {
            return dep + 1;
        }
        dep += 1;
        int left = dfs(root.right, dep);
        int right = dfs(root.left, dep);
        return Math.min(left, right);
    }
}
