package leetcode.BinarySearchTree;

public class A530_MinimumAbsoluteDifferenceInBST {

    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        int val = root.val;
        if (prev == null) {
            prev = val;
        } else {
            int diff = Math.abs(val - prev);
            min = Math.min(min, diff);
            prev = val;
        }
        dfs(root.right);
    }


}
