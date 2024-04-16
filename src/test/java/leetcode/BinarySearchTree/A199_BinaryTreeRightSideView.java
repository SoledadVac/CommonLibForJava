package leetcode.BinarySearchTree;

import java.util.*;

public class A199_BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> data = new LinkedList<>();
        if (root == null) {
            return data;
        }
        List<TreeNode> temp = new LinkedList<>();
        temp.add(root);
        while (!temp.isEmpty()) {
            int size = temp.size();
            List<TreeNode> newLine = new LinkedList<>();
            for (int i = 0; i <= size - 1; i++) {
                TreeNode treeNode = temp.get(i);
                if (treeNode.left != null) {
                    newLine.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    newLine.add(treeNode.right);
                }
            }
            data.add(temp.get(size - 1).val);
            temp = newLine;
        }

        return data;
    }

    public void dfs(TreeNode root, List<Integer> data) {
        if (root == null) {
            return;
        }
        data.add(root.val);
        if (root.right == null && root.left == null) {
            return;
        }
        if (root.right == null) {
            dfs(root.left, data);
            return;
        }
        dfs(root.right, data);
    }

}
