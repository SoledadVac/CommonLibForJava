package leetcode.BinarySearchTree;

import leetcode.BinaryTree.TreeNode;
import org.junit.Test;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/28
 * \* Time: 29:57 PM
 * \* Description: Delete Node in a BST
 * \
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 */
public class A450_DeleteNodeinaBST {
    //1,用前驱结点（左子树中最大结点）代替被删除结点
    //2,用后继结点（右子树中最小结点）代替被删除结点

    /**
     * 1. 如果目标节点没有子节点，我们可以直接移除该目标节点。
     * 2. 如果目标节只有一个子节点，我们可以用其子节点作为替换。
     * 3. 如果目标节点有两个子节点，我们需要用其中序后继节点或者前驱节点来替换，再删除该目标节点。
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                root = root.right;
                return root;
            }
            if (root.right == null) {
                root = root.left;
                return root;
            }
            root = getLeftChildMaxNode(root);
            return root;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        return root;
    }

    /**
     * 转变右子树最小结点
     *
     * @param root
     * @return
     */
    private TreeNode getRightChildMinNode(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (right.left == null) {
            right.left = left;
            return right;
        }
        //root的left直接挪到root.left的最小结点上
        TreeNode temp = right.left;
        while (temp.left != null) {
            temp = temp.left;
        }
        temp.left = left;
        return right;
    }

    /**
     * 转变左子树最大结点
     *
     * @return
     */
    private TreeNode getLeftChildMaxNode(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left.right == null) {
            left.right = right;
            return left;
        }
        TreeNode temp = left.right;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = right;
        return left;
    }


    @Test
    public void test() {
        TreeNode n5 = new TreeNode(5);
        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        n5.left = n3;
        n5.right = n6;
        n3.left = n2;
        n3.right = n4;
        n6.right = n7;
        TreeNode n = deleteNode(n5, 3);
        System.out.println("root = " + n.val);
    }
}
