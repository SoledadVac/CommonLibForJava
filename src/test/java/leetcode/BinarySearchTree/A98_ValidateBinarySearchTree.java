package leetcode.BinarySearchTree;

import leetcode.BinaryTree.TreeNode;
import org.junit.Test;

import java.util.LinkedList;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/28
 * \* Time: 26:18 PM
 * \* Description: 验证二叉搜索树
 * \
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A98_ValidateBinarySearchTree {

    LinkedList<Integer> data = new LinkedList<>();

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && !isValidBST(root.left)) {
            return false;
        }
        //左中右遍历，让值依次增大即可
        if (!data.isEmpty()) {
            Integer n = data.peekLast();
            if (n >= root.val) {
                return false;
            }
        }
        data.add(root.val);
        if (root.right != null && !isValidBST(root.right)) {
            return false;
        }
        return true;
    }


    @Test
    public void test() {
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        n2.left = n1;
        n2.right = n3;
        System.out.println("result = " + isValidBST(n2));

       /* TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(15);
        TreeNode n3 = new TreeNode(15);
        TreeNode n4 = new TreeNode(6);
        TreeNode n5 = new TreeNode(20);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        System.out.println("result = " + isValidBST(n1));*/
    }
}
