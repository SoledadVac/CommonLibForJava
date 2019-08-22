package leetcode.BinaryTree;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/21
 * \* Time: 27:39 PM
 * \* Description: 二叉树的前序遍历 : 前序遍历首先访问根节点，然后遍历左子树，最后遍历右子树
 * \
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 *  示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 */
public class A144_BinaryTreePreorderTraversal {




    //前序遍历首先访问根节点，然后遍历左子树，最后遍历右子树
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        generate(root, result);
        return result;
    }

    private void generate(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            generate(root.left, result);
        }
        if (root.right != null) {
            generate(root.right, result);
        }
    }

    public List<Integer> preorderTraversal1(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.add(node.val);
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
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.right = n2;
        TreeNode n3 = new TreeNode(3);
        n2.left = n3;
        System.out.println("result = " + JSONObject.toJSONString(preorderTraversal(root)));
    }


}
