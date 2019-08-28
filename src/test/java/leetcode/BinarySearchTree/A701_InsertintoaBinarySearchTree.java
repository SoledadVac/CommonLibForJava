package leetcode.BinarySearchTree;

import leetcode.BinaryTree.TreeNode;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/28
 * \* Time: 29:30 PM
 * \* Description: Insert into a Binary Search Tree
 * \
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 * <p>
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 * <p>
 * 例如,
 * <p>
 * 给定二叉搜索树:
 * <p>
 * 4
 * / \
 * 2   7
 * / \
 * 1   3
 * <p>
 * 和 插入的值: 5
 * 你可以返回这个二叉搜索树:
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   /
 * 1   3 5
 * 或者这个树也是有效的:
 * <p>
 * 5
 * /   \
 * 2     7
 * / \
 * 1   3
 * \
 * 4
 */
public class A701_InsertintoaBinarySearchTree {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        if (root.val > val && root.left == null) {
            root.left = new TreeNode(val);
            return root;
        }
        if (root.val < val && root.right == null) {
            root.right = new TreeNode(val);
            return root;
        }
        if (root.left != null && root.val > val) {
            insertIntoBST(root.left, val);
        }
        if (root.right != null && root.val < val) {
            insertIntoBST(root.right, val);
        }
        return root;
    }

    @Test
    public void test() {
       /* TreeNode n4 = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        TreeNode n7 = new TreeNode(7);
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        n4.left = n2;
        n4.right = n7;
        n2.left = n1;
        n2.right = n3;
        TreeNode n = insertIntoBST(n4, 5);
        System.out.println("root = " + n.val);*/

        TreeNode n40 = new TreeNode(40);
        TreeNode n20 = new TreeNode(20);
        TreeNode n60 = new TreeNode(60);
        TreeNode n10 = new TreeNode(10);
        TreeNode n30 = new TreeNode(30);
        TreeNode n50 = new TreeNode(50);
        TreeNode n70 = new TreeNode(70);
        n40.left = n20;
        n40.right = n60;
        n20.left = n10;
        n20.right = n30;
        n60.left = n50;
        n60.right = n70;
        TreeNode n = insertIntoBST(n40, 25);
        System.out.println("root = " + n.val);
    }
}
