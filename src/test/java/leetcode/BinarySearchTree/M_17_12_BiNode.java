package leetcode.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/10/6
 * \* Time: 12:06 下午
 * \* Description:BiNode
 * \
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * <p>
 * 返回转换后的单向链表的头节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binode-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 */
public class M_17_12_BiNode {


    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head;
        List<TreeNode> nodes = new ArrayList<>();
        inOrder(root, nodes);
        head = nodes.get(0);
        return head;
    }

    public void inOrder(TreeNode root, List<TreeNode> nodes) {
        if (root.left != null) {
            inOrder(root.left, nodes);
        }

        if (nodes.size() != 0) {
            TreeNode prev = nodes.get(nodes.size() - 1);
            prev.right = root;
        }
        root.left = null;
        nodes.add(root);
        if (root.right != null) {
            inOrder(root.right, nodes);
        }
    }
}
