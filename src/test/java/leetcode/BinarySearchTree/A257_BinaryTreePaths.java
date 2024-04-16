package leetcode.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class A257_BinaryTreePaths {

    List<String> result = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return result;
    }

    void dfs(TreeNode root, String item) {
        if (root == null) {
            return;
        }
        if (item.length() == 0) {
            item = root.val + "";
        } else {
            item = item + "->" + root.val;
        }
        if (root.left == null && root.right == null) {
            result.add(item);
            return;
        }
        dfs(root.left, item);
        dfs(root.right, item);
    }

}
