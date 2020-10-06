package leetcode.BinarySearchTree;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/10/6
 * \* Time: 2:30 下午
 * \* Description:把二叉搜索树转换为累加树
 * \
 */
public class A1038_BinarySearchTreeToGreaterSumTree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //右-》中-》左遍历，奇葩
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertAddSum(root, 0);
        return root;
    }

    int convertAddSum(TreeNode root, int sum) {
        if (root.right != null) {
            sum = convertAddSum(root.right, sum);
        }
        sum += root.val;
        root.val = sum;
        if (root.left != null) {
            return convertAddSum(root.left, sum);
        }
        return sum;
    }


}
