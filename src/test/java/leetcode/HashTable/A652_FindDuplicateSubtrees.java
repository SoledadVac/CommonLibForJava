package leetcode.HashTable;

import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/20
 * \* Time: 26:46 PM
 * \* Description: 寻找重复的子树
 * \
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * <p>
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * <p>
 * 示例 1：
 * <p>
 * 1
 * / \
 * 2   3
 * /   / \
 * 4   2   4
 * /
 * 4
 * 下面是两个重复的子树：
 * <p>
 * 2
 * /
 * 4
 * 和
 * <p>
 * 4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 * <p>
 */
public class A652_FindDuplicateSubtrees {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<String, TreeNode> result = new HashMap<>();
        Map<String, TreeNode> data = new HashMap();
        getData(root, data, result);
        return new ArrayList<>(result.values());
    }

    private String getData(TreeNode node, Map<String, TreeNode> data, Map<String, TreeNode> result) {
        String exp;
        if (node.left == null && node.right == null) {
            exp = String.valueOf(node.val);

        } else if (node.left == null) {
            exp = String.valueOf(node.val) + "(," + getData(node.right, data, result) + ")";
        } else if (node.right == null) {
            exp = String.valueOf(node.val) + "(" + getData(node.left, data, result) + ",)";
        } else {
            exp = String.valueOf(node.val) + "(" + getData(node.left, data, result) + "," + getData(node.right, data, result) + ")";
        }
        if (data.containsKey(exp)) {
            if (!result.containsKey(exp)) {
                result.put(exp, node);
            }
        } else {
            data.put(exp, node);
        }
        return exp;
    }

    private String getExp(TreeNode node) {
        if (node.left == null && node.right == null) {
            return String.valueOf(node.val);
        }
        if (node.left == null) {
            return String.valueOf(node.val) + "(" + getExp(node.right) + ")";
        }
        if (node.right == null) {
            return String.valueOf(node.val) + "(" + getExp(node.left) + ")";
        }
        return String.valueOf(node.val) + "(" + getExp(node.right) + "," + getExp(node.left) + ")";
    }


    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        root.left = n1;
        root.right = n2;
        TreeNode n3 = new TreeNode(4);
        n1.left = n3;
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(4);
        n2.left = n4;
        n2.right = n5;
        TreeNode n6 = new TreeNode(4);
        n4.left = n6;
        List<TreeNode> result = findDuplicateSubtrees(root);
        System.out.println(result);
    }

}
