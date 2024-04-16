package leetcode.Dfs;

public class A572_SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (subRoot == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
        //值相等
        if (subRoot.left == null && subRoot.right == null) {
            if (root.left == null && root.right == null) {
                return true;
            } else {
                return isSubtree(root.left, subRoot) || isSubtree(root.left, subRoot);
            }
        }
        return isSubtree(root.left, subRoot.left) && isSubtree(root.right, subRoot.right);
    }


}
