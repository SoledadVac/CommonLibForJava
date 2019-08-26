package leetcode.BinaryTree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/23
 * \* Time: 29:43 PM
 * \* Description: 从中序与后序遍历序列构造二叉树
 * \
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A106_ConstructBinaryTreefromInorderandPostorderTraversal {

    int pindex = 0;//后序的索引
    Map<Integer, Integer> data = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //中序遍历 ： 左中右 [9,3,15,20,7]
        //后序遍历 ：左右中 [9,15,7,20,3]
        for (int i = 0; i < inorder.length; i++) {
            data.put(inorder[i], i);
        }
        pindex = postorder.length - 1;
        return build(postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] postorder, int left, int right) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pindex--]);
        int index = data.get(root.val);
        root.right = build(postorder, index + 1, right);  //遍历右子树
        root.left = build(postorder, left, index - 1); //遍历左子树
        return root;
    }


    @Test
    public void test() {
        int[] inorder = {9, 3, 15, 20, 7}; //中序遍历 ： 左中右
        int[] postorder = {9, 15, 7, 20, 3}; //后序遍历 ：左右中
        TreeNode n = buildTree(inorder, postorder);
        System.out.println(n);
    }
}
