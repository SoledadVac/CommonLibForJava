package leetcode.BinarySearchTree;

import leetcode.BinaryTree.TreeNode;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/29
 * \* Time: 32:32 PM
 * \* Description: 将有序数组转换为二叉搜索树
 * \
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class A108_ConvertSortedArraytoBinarySearchTree {

    //取数组的中间元素作为根结点， 将数组分成左右两部分，对数组的两部分用递归的方法分别构建左右子树
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return build(nums, 0, nums.length - 1);
    }

    TreeNode build(int[] nums, int left, int right) {
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (left + 1 == right) {
            root.right = new TreeNode(nums[right]);
            return root;
        }
        if (left + 2 == right) {
            root.left = new TreeNode(nums[left]);
            root.right = new TreeNode(nums[right]);
            return root;
        }
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }


    @Test
    public void test() {
        int[] nums = {0, 1, 2, 3, 4, 5};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println(root);
    }

}
