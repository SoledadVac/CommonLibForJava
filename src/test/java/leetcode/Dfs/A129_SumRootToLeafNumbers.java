package leetcode.Dfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class A129_SumRootToLeafNumbers {

    List<String> pathList = new LinkedList<>();


    void dfs(TreeNode node, StringBuilder path) {
        if (node == null) {
            return;
        }
        path.append(node.val);
        if (node.left == null && node.right == null) {
            pathList.add(path.toString());
            return;
        }
        dfs(node.left, new StringBuilder(path));
        dfs(node.right, new StringBuilder(path));
    }


    public int sumNumbers(TreeNode root) {
        int sum = 0;
        dfs(root, new StringBuilder());
        for (String item : pathList) {
            sum += Integer.parseInt(item);
        }
        return sum;
    }



}
