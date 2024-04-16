package leetcode.BinarySearchTree;

public class A124_BinaryTreeMaximumPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        findMax(root);
        return max;
    }

    public int findMax(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int left = Math.max(findMax(root.left), 0);
        int right = Math.max(findMax(root.right), 0);
        int current = root.val + left + right;
        max = Math.max(max, current);
        return root.val+Math.max(left,right);
    }
}
