package leetcode.BinarySearchTree;

import leetcode.BinaryTree.TreeNode;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/28
 * \* Time: 29:17 PM
 * \* Description: Search in a Binary Search Tree
 * \
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * <p>
 * 例如，
 * <p>
 * 给定二叉搜索树:
 * <p>
 * 4
 * / \
 * 2   7
 * / \
 * 1   3
 * <p>
 * 和值: 2
 * 你应该返回如下子树:
 * <p>
 * 2
 * / \
 * 1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 */
public class A700_SearchinaBinarySearchTree {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val && root.left != null) {
            return searchBST(root.left, val);
        }
        if (root.val < val && root.right != null) {
            return searchBST(root.right, val);
        }
        return null;
    }

    @Test
    public void test() {
        //这个太简单了，不写测试用例了。直接拿上去跑了下通过了

    }
}
