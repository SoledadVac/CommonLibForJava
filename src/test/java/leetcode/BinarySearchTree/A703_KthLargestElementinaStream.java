package leetcode.BinarySearchTree;


import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/29
 * \* Time: 35:22 AM
 * \* Description: 数据流中的第K大元素
 * \
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A703_KthLargestElementinaStream {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        int cnt; //以此节点为根的子树中有多少个节点

        TreeNode(int x) {
            this.val = x;
        }

        TreeNode(int x, int cnt) {
            this.val = x;
            this.cnt = cnt;
        }
    }

    //对于二叉搜索树的每个节点来说，它的左子树上所有结点的值均小于它的根结点的值，右子树上所有结点的值均大于它的根结点的值。
    //换言之，对于二叉搜索树的每个节点来说，若其左子树共有m个节点，那么该节点是组成二叉搜索树的有序数组中第m + 1个值。
    class KthLargest {

        TreeNode root;
        final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            buildTree(nums);
        }

        public int add(int val) {
            insertTree(this.root, val);
            int times = k;
            if (times > this.root.cnt) {
                return -1;
            }
            return searchK(this.root, k);
        }

        void buildTree(int[] nums) {
            for (int n : nums) {
                if (this.root == null) {
                    this.root = new TreeNode(n, 1);
                    continue;
                }
                insertTree(root, n);
            }
        }

        /**
         * 搜索第K个
         *
         * @return
         */
        int searchK(TreeNode root, int k) {
            //没有右侧节点，刚好是当前根节点，完美
            if (root.right == null && k == 1) {
                return root.val;
            }
            //右侧搜索
            if (root.right == null) {
                //右侧树为空的，那就只能搜索左侧了
                return searchK(root.left, k - 1); //减掉中间节点
            }
            //右侧树不为空
            if (k == root.right.cnt + 1) {
                //刚好为当前节点
                return root.val;
            } else if (k < root.right.cnt + 1) {
                //继续搜索右侧数
                return searchK(root.right, k);
            } else {
                //搜索左侧树
                int n = k - root.right.cnt - 1;
                return searchK(root.left, n);
            }
        }

        /**
         * 插入值时候构建BST
         *
         * @param root
         * @param val
         */
        void insertTree(TreeNode root, int val) {
            if (root == null) {
                this.root = new TreeNode(val, 1);
                return;
            }
            if (root == this.root) {
                root.cnt = root.cnt + 1;
            }
            if (root.val >= val) {
                if (root.left == null) {
                    root.left = new TreeNode(val, 1);
                } else {
                    root.left.cnt = root.left.cnt + 1;
                    insertTree(root.left, val);
                }
            }
            if (root.val < val) {
                if (root.right == null) {
                    root.right = new TreeNode(val, 1);
                } else {
                    root.right.cnt = root.right.cnt + 1;
                    insertTree(root.right, val);
                }
            }
        }
    }


    @Test
    public void test() {
       /* int k = 3;
        int[] arr = {4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(3, arr);
        int n1 = kthLargest.add(3);// returns 4
        System.out.println("n1 = " + n1);
        int n2 = kthLargest.add(5);// returns 5
        System.out.println("n2 = " + n2);
        int n3 = kthLargest.add(10);// returns 5
        System.out.println("n3 = " + n3);
        int n4 = kthLargest.add(9);// returns 8
        System.out.println("n4 = " + n4);
        int n5 = kthLargest.add(4);// returns 8
        System.out.println("n5 = " + n5);
        System.out.println("====");*/

        // ["KthLargest","add","add","add","add","add"]
        //[[1,[-2]],[-3],[0],[2],[-1],[4]]
       /* KthLargest kthLargest = new KthLargest(1, new int[]{});
        int n1 = kthLargest.add(-3);
        System.out.println("n1 = " + n1);
        int n2 = kthLargest.add(0);
        System.out.println("n2 = " + n2);
        int n3 = kthLargest.add(2);
        System.out.println("n3 = " + n3);
        int n4 = kthLargest.add(-1);
        System.out.println("n4 = " + n4);
        int n5 = kthLargest.add(4);
        System.out.println("n5 = " + n5);*/

        //["KthLargest","add","add","add","add","add","add","add","add"]
        //[[3,[1,1]],[1],[1],[3],[3],[3],[4],[4],[4]]
       /* KthLargest kthLargest = new KthLargest(3, new int[]{1, 1});
        int n1 = kthLargest.add(1);
        System.out.println("n1 = " + n1);
        int n2 = kthLargest.add(1);
        System.out.println("n2 = " + n2);
        int n3 = kthLargest.add(3);
        System.out.println("n3 = " + n3);
        int n4 = kthLargest.add(3);
        System.out.println("n4 = " + n4);
        int n5 = kthLargest.add(3);
        System.out.println("n5 = " + n5);
        int n6 = kthLargest.add(4);
        System.out.println("n6 = " + n6);
        int n7 = kthLargest.add(4);
        System.out.println("n7 = " + n7);
        int n8 = kthLargest.add(4);
        System.out.println("n8 = " + n8);*/

        //["KthLargest","add","add","add","add","add"]
        //[[3,[5,-1]],[2],[1],[-1],[3],[4]]
        //[null,-1,1,1,2,3]
        KthLargest kthLargest = new KthLargest(3, new int[]{5, -1});
        int n1 = kthLargest.add(2);
        System.out.println("n1 = " + n1);
        int n2 = kthLargest.add(1);
        System.out.println("n2 = " + n2);
        int n3 = kthLargest.add(-1);
        System.out.println("n3 = " + n3);
        int n4 = kthLargest.add(3);
        System.out.println("n4 = " + n4);
        int n5 = kthLargest.add(4);
        System.out.println("n5 = " + n5);


    }
}
