package leetcode.BinarySearchTree;

public class A617_MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1==null){
            return root2;
        }
        dfs(root1, null, root2, null);
        return root1;
    }

    public void dfs(TreeNode root1, TreeNode root1Parent, TreeNode root2, TreeNode root2Parent) {
        if (root1 == null && root2 == null) {
            return;
        }
        //都叠加到1上
        if (root1 == null & root2 != null) {
            if (root2Parent.left == root2) {
                root1Parent.left = root2;
            } else {
                root1Parent.right = root2;
            }
            return;
        }
        if (root1 != null & root2 != null) {
            root1.val += root2.val;
            dfs(root1.left, root1, root2.left, root2);
            dfs(root1.right, root1, root2.right, root2);
        }
    }
}
