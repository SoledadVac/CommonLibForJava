package leetcode.BinaryTree;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/21
 * \* Time: 28:08 PM
 * \* Description: 二叉树的后序遍历 : 后序遍历是先遍历左子树，然后遍历右子树，最后访问树的根节点
 * \
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 */
public class A145_BinaryTreePostorderTraversal {

    /**
     * 使用递归
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        generate(root, result);
        return result;
    }

    /**
     * 递归方法
     *
     * @param root
     * @param result
     */
    private void generate(TreeNode root, List<Integer> result) {
        //后序遍历是先遍历左子树，然后遍历右子树，最后访问树的根节点
        if (root == null) {
            return;
        }
        if (root.left != null) {
            generate(root.left, result);
        }
        if (root.right != null) {
            generate(root.right, result);
        }
        result.add(root.val);
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }


    @Test
    public void test() {
       /* TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.right = n2;
        TreeNode n3 = new TreeNode(3);
        n2.left = n3;
        System.out.println("result = " + JSONObject.toJSONString(postorderTraversal(root)));*/

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        n5.right = n8;
        n3.right = n6;
        n6.left = n9;
        System.out.println("result = " + JSONObject.toJSONString(postorderTraversal1(n1)));

    }
}
