package leetcode.BinarySearchTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class A99_RecoverBinarySearchTree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode n1;
    TreeNode n2;
    TreeNode prev;

    public void dfs(TreeNode root){
        if (root == null) {
            return;
        }
        if (n1 != null && n2 != null) {
            return;
        }
        dfs(root.left);
        if (prev != null && root.val < prev.val) {
            n1 = prev;
        }
        if (prev != null && n1 != null && root.val < prev.val) {
            n2 = root;
        }
        prev = root;
        dfs(root.right);
    }
    // 1,6,3,4,5,2
    public void recoverTree(TreeNode root) {
        dfs(root);
        int temp=n1.val;
        n1.val=n2.val;
        n2.val=temp;
    }

    @Test
    public void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n3;
        n3.right = n2;
        recoverTree(n1);
        System.out.println("---");
    }


}
