package leetcode.Dfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class A1302_DeepestLeavesSum {

    int sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int tempSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tempSum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            sum = tempSum;
        }
        return sum;
    }


}
