package leetcode.BinarySearchTree;

import java.util.LinkedList;
import java.util.List;

public class A637_AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> nodeList = new LinkedList<>();
        nodeList.add(root);
        while (!nodeList.isEmpty()) {
            int size = nodeList.size();
            double total = 0;
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
                total += v;
            }
            result.add(total / size);
            nodeList = newLayerList;
        }
        return result;
    }


}
