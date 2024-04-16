package leetcode.Dfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class A1022_SumOfRootToLeafBinaryNumbers {

    List<String> pathList = new LinkedList<>();

    public int sumRootToLeaf(TreeNode root) {
        int sum = 0;
        dfs(root, new StringBuilder());
        for (String item : pathList) {
            sum += changeStringToValue(item);
        }
        return sum;
    }

    int changeStringToValue(String path) {
        //100
        int sum = 0;
        int move = 0;
        for (int i = path.length() - 1; i >= 0; i--) {
            int b = path.charAt(i) - '0';
            int value = b * (1 << move);
            sum += value;
            move++;
        }
        return sum;
    }


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

    @Test
    public void test() {
        System.out.println(changeStringToValue("101"));
    }
}
