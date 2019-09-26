package leetcode.Recursion;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/24
 * \* Time: 35:11 AM
 * \* Description: 最长同值路径
 * \
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 */
public class A687_LongestUnivaluePath {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getMaxLeng(root);
        return max;
    }

    int getMaxLeng(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getMaxLeng(root.left);
        int right = getMaxLeng(root.right);
        left = (root.left != null && root.left.val == root.val) ? left + 1 : 0;
        right = (root.right != null && root.right.val == root.val) ? right + 1 : 0;
        max = Math.max(max, left + right);
        return Math.max(left, right);
    }


    @Test
    public void test() {
       /* TreeNode root = new TreeNode(5);
        TreeNode r1 = new TreeNode(4);
        TreeNode r2 = new TreeNode(5);
        TreeNode r3 = new TreeNode(1);
        TreeNode r4 = new TreeNode(1);
        TreeNode r5 = new TreeNode(5);
        root.left = r1;
        root.right = r2;
        r1.left = r3;
        r1.right = r4;
        r2.right = r5;*/

        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(4);
        TreeNode r2 = new TreeNode(5);
        TreeNode r3 = new TreeNode(4);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        root.left = r1;
        root.right = r2;
        r1.left = r3;
        r1.right = r4;
        r2.right = r5;
        //[1,null,1,1,1,1,1,1]

        System.out.println(longestUnivaluePath(root));
    }


}
