package leetcode.Dfs;

public class A897_IncreasingOrderSearchTree {

    TreeNode prev = null;
    TreeNode newRoot = new TreeNode(-1);

    public TreeNode increasingBST(TreeNode root) {
        //左中右排列
        prev = newRoot;
        dfs(root);
        return newRoot.right;
    }

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        prev.right = root;
        root.left = null;
        prev = root;
        dfs(root.right);
    }

}
