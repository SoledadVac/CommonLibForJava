package leetcode.Dfs;

public class A563_BinaryTreeTilt {

    int sum = 0;

    public int findTilt(TreeNode root) {
        calculateValue(root);
        return sum;
    }


    public int calculateValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = calculateValue(root.left);
        int right = calculateValue(root.right);
        sum += Math.abs(left - right);
        return root.val + left + right;
    }


}
