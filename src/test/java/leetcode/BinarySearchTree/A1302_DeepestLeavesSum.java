package leetcode.BinarySearchTree;

import java.util.HashMap;
import java.util.Map;

public class A1302_DeepestLeavesSum {


    int maxLayer = 0;
    Map<Integer, Integer> data = new HashMap<>();

    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return data.getOrDefault(maxLayer, 0);
    }

    void dfs(TreeNode root, int layer) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            int v = data.getOrDefault(layer, 0);
            v += root.val;
            data.put(layer, v);
            maxLayer = Math.max(maxLayer, v);
            return;
        }
        layer += 1;
        dfs(root.left, layer);
        dfs(root.right, layer);
    }
}
