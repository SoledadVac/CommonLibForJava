package leetcode.BinarySearchTree;

import com.kenai.jaffl.annotations.In;

import java.util.ArrayDeque;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/10/6
 * \* Time: 3:10 下午
 * \* Description:二叉搜索子树的最大键值和
 * \
 * <p>
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 * <p>
 * 二叉搜索树的定义如下：
 * <p>
 * 任意节点的左子树中的键值都 小于 此节点的键值。
 * 任意节点的右子树中的键值都 大于 此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1373_MaximumSumBSTInBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxSumBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        sum = isBST(root, new ArrayDeque<Integer>());
        return sum;
    }

    int isBST(TreeNode root, ArrayDeque<Integer> data) {
        if (root.left != null) {
            int v = isBST(root.left, data);
            if (v < 0) {
                return -1;
            }
        }
        if (!data.isEmpty() && data.getLast() > root.val) {
            return -1;
        }
        data.add(root.val);
        if (root.right != null) {
            int v = isBST(root.right, data);
            if (v < 0) {
                return -1;
            }
        }
        return data.stream().mapToInt(Integer::intValue).sum();
    }
}
