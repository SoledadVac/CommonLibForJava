package leetcode.BinarySearchTree;

import leetcode.BinaryTree.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/28
 * \* Time: 27:39 PM
 * \* Description: 二叉搜索树迭代器
 * \
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * <p>
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *  
 * <p>
 * 提示：
 * <p>
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A173_BinarySearchTreeIterator {

    /**
     * 直接是中序遍历完了的,emm
     */
    class BSTIterator {
        Queue<TreeNode> queue = new LinkedList();

        private void buildQueue(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                buildQueue(root.left);
            }
            queue.add(root);
            if (root.right != null) {
                buildQueue(root.right);
            }
        }

        public BSTIterator(TreeNode root) {
            buildQueue(root);
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            if (!hasNext()) {
                return -1;
            }
            TreeNode n = queue.poll();
            return n.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

    /**
     * 在next时候去加入stack,而非构造时候去初始化
     */
    class BSTIterator1 {

        Stack<TreeNode> stack = new Stack<>();
        private void addToStack(TreeNode root) {
            if (root == null) {
                return;
            }
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public BSTIterator1(TreeNode root) {
            addToStack(root);
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode n = stack.pop();
            if (stack.isEmpty()) {
                addToStack(n.right);
            }
            return n.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    @Test
    public void test() {
        TreeNode n7 = new TreeNode(7);
        TreeNode n3 = new TreeNode(3);
        TreeNode n15 = new TreeNode(15);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        n7.left = n3;
        n7.right = n15;
        n15.left = n9;
        n15.right = n20;
        BSTIterator1 iterator = new BSTIterator1(n7);
        int m1 = iterator.next();    // 返回 3
        System.out.println("m1=" + m1);
        int m2 = iterator.next();    // 返回 7
        System.out.println("m2=" + m2);
        iterator.hasNext(); // 返回 true
        int m3 = iterator.next();    // 返回 9
        System.out.println("m3=" + m3);
        iterator.hasNext(); // 返回 true
        int m4 = iterator.next();    // 返回 15
        System.out.println("m4=" + m4);
        iterator.hasNext(); // 返回 true
        int m5 = iterator.next();    // 返回 20
        System.out.println("m5=" + m5);
        iterator.hasNext(); // 返回 false

    }
}
