package leetcode.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/10/6
 * \* Time: 1:46 下午
 * \* Description:将二叉搜索树变平衡
 * \
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * <p>
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * <p>
 * 如果有多种构造方法，请你返回任意一种。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balance-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1382_BalanceABinarySearchTree {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 先得到值，之后重建
     *
     * @param root
     * @return
     */
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> data = new ArrayList<>();
        inOrderTree(root, data);
        return buildBalancedBST(data, 0, data.size() - 1);
    }

    public void inOrderTree(TreeNode root, List<Integer> data) {
        if (root.left != null) {
            inOrderTree(root.left, data);
        }
        data.add(root.val);
        if (root.right != null) {
            inOrderTree(root.right, data);
        }
    }

    TreeNode buildBalancedBST(List<Integer> data, int left, int right) {
        if (left == right) {
            return new TreeNode(data.get(left));
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(data.get(mid));
        if (left + 1 == right) {
            root.right = new TreeNode(data.get(right));
            return root;
        }
        if (left + 2 == right) {
            root.left = new TreeNode(data.get(left));
            root.right = new TreeNode(data.get(right));
            return root;
        }
        root.left = buildBalancedBST(data, left, mid - 1);
        root.right = buildBalancedBST(data, mid + 1, right);
        return root;
    }


}
