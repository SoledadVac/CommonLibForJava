package leetcode.Dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class A872_LeafSimilarTrees {

    List<Integer> data1 = new LinkedList<>();
    List<Integer> data2 = new LinkedList<>();

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        dfs(root1, 1);
        dfs(root2, 2);
        if (data1.size() != data2.size()) {
            return false;
        }
        for (int i = 0; i < data1.size(); i++) {
            if (!data1.get(i).equals(data2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void dfs(TreeNode root, int flag) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (flag == 1) {
                data1.add(root.val);
            } else {
                data2.add(root.val);
            }
            return;
        }
        dfs(root.left, flag);
        dfs(root.right, flag);
    }
}
