package leetcode.BinarySearchTree;

import java.util.LinkedList;
import java.util.List;

public class A993_CousinsInBinaryTree {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        boolean find = false;
        List<TreeNode> nodeList = new LinkedList<>();
        nodeList.add(root);
        while (!nodeList.isEmpty()) {
            int size = nodeList.size();
            List<TreeNode> newLayerList = new LinkedList<>();
            List<Integer> newLayerDataList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode item = nodeList.get(i);
                if (item.left != null) {
                    newLayerList.add(item.left);
                }
                if (item.right != null) {
                    newLayerList.add(item.right);
                }
                if (item.left != null && item.right != null) {
                    if ((item.left.val == x && item.right.val == y) || (item.left.val == y && item.right.val == x)) {
                        return false;
                    }
                }
                int v = item.val;
                newLayerDataList.add(v);
            }
            if (newLayerDataList.contains(x) && newLayerDataList.contains(y)) {
                return true;
            }
            nodeList = newLayerList;
        }
        return find;
    }
}
