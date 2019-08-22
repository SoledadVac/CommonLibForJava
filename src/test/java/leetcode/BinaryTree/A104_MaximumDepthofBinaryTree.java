package leetcode.BinaryTree;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/22
 * \* Time: 35:25 AM
 * \* Description:  二叉树的最大深度
 * \
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * /  \
 * 9   20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A104_MaximumDepthofBinaryTree {


    int answer = 0;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 1;
        depth(root, depth);
        return answer;
    }

    private void depth(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            answer = Math.max(answer, depth);
        }
        if (root.left != null) {
            depth(root.left, depth + 1);
        }
        if (root.right != null) {
            depth(root.right, depth + 1);
        }
    }



    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);
        root.left = n9;
        root.right = n20;
        n20.left = n15;
        n20.right = n7;
        System.out.println("result = " + maxDepth(root));
    }
}
