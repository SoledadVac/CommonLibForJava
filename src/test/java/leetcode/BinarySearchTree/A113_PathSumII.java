package leetcode.BinarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class A113_PathSumII {

    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, new ArrayList<>());
        return result;
    }

    public void dfs(TreeNode root, int targetSum, List<Integer> data) {
        if (root == null) {
            return;
        }
        data.add(root.val);
        targetSum = targetSum - root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            result.add(data);
            return;
        }
        dfs(root.left, targetSum, new ArrayList<>(data));
        dfs(root.right, targetSum, new ArrayList<>(data));
    }
}
