package leetcode.BinarySearchTree;

import java.util.LinkedList;
import java.util.List;

public class A653_TwoSumIV_InputIsABST {
    public boolean findTarget(TreeNode root, int k) {

        List<Integer> data = new LinkedList<>();
        if (root == null) {
            return false;
        }
        boolean find = false;
        List<TreeNode> nodeList = new LinkedList<>();
        nodeList.add(root);
        while (!nodeList.isEmpty()) {
            int size = nodeList.size();
            List<TreeNode> newLayerList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode item = nodeList.get(i);
                if (item.left != null) {
                    newLayerList.add(item.left);
                }
                if (item.right != null) {
                    newLayerList.add(item.right);
                }
                int v = item.val;
                int other = k - v;
                if (data.contains(other)) {
                    return true;
                } else {
                    data.add(v);
                }
            }
            nodeList = newLayerList;
        }
        return find;
    }
}
