package leetcode.Recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/26
 * \* Time: 35:25 AM
 * \* Description: 二叉搜索树结点最小距离
 * \
 * 给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
 * <p>
 * 示例：
 * <p>
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树结点对象(TreeNode object)，而不是数组。
 * <p>
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 * <p>
 * 4
 * /   \
 * 2      6
 * / \
 * 1   3
 * <p>
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 * 注意：
 * <p>
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A783_MinimumDistanceBetweenBSTNodes {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<TreeNode> data = new ArrayList<>();
    int min = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        diff(root, data);
        return min;
    }

    void diff(TreeNode root, List<TreeNode> data) {
        if (root.left != null) {
            diff(root.left, data);
        }
        data.add(root);
        if (data.size() >= 2) {
            int dif = data.get(data.size() - 1).val - data.get(data.size() - 2).val;
            min = Math.min(min, dif);
        }
        if (root.right != null) {
            diff(root.right, data);
        }
    }


    @Test
    public void test() {


    }


}
