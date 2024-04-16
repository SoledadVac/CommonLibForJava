package leetcode.Dfs;


public class A543_DiameterOfBinaryTree {


    int max = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        maxNode(root);
        return max - 1;
    }


    public int maxNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxNode(root.left);
        int right = maxNode(root.right);
        max = Math.max(max, left + right + 1);
        return Math.max(left,right) + 1;
    }


}
