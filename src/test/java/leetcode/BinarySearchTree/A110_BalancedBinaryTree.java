package leetcode.BinarySearchTree;

import leetcode.BinaryTree.TreeNode;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/29
 * \* Time: 30:09 PM
 * \* Description: 判断平衡二叉树
 * \
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * 返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A110_BalancedBinaryTree {

    Boolean res = true;

    public boolean isBalanced(TreeNode root) {
        singleBalanced(root);
        return res;
    }

    int singleBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = singleBalanced(root.left) + 1;
        int right = singleBalanced(root.right) + 1;
        if (Math.abs(left - right) > 1) {
            res = false;
        }
        return Math.max(left, right);
    }

    @Test
    public void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n21 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n31 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n41 = new TreeNode(4);
        n1.left = n2;
        n1.right = n21;
        n2.left = n3;
        n2.right = n31;
        n3.left = n4;
        n3.right = n41;
        Boolean result = isBalanced(n1);
        System.out.println("result = " + result);

    }

}
