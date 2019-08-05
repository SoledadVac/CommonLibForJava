package leetcode.BinarySearch;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/16
 * \* Time: 31:57 PM
 * \* Description:二叉树的中序遍历
 * \
 * 给定一个二叉树，返回它的中序 遍历。
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
 * 输出: [1,3,2]
 * <p>
 */
public class A94_BinaryTreeInorderTraversal {


    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    /**
     * 递归方式求解
     *
     * @param root
     * @param result
     */
    void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            inorderTraversal(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, result);
        }
    }

    /**
     * 基于栈的方式求解
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;

    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.right = node2;
        TreeNode node3 = new TreeNode(3);
        node2.left = node3;
        System.out.println(JSONObject.toJSONString(inorderTraversal1(root)));
    }
}
