package leetcode.BinarySearchTree;

public class A114_FlattenBinaryTreeToLinkedList {


    TreeNode prev;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        //右->左->中
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }


}
