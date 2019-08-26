package leetcode.BinaryTree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/26
 * \* Time: 27:05 PM
 * \* Description: 从前序与中序遍历序列构造二叉树
 * \
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A105_ConstructBinaryTreefromPreorderandInorderTraversal {

    Map<Integer, Integer> map = new HashMap<>();
    int psize = 0;

    /**
     * @param preorder 前序遍历(中左右) preorder = [3,9,20,15,7]
     * @param inorder  中序遍历（左中右） inorder = [9,3,15,20,7]
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1);
    }

    /**
     * @param pre preorder 前序遍历(中左右)
     * @param inl
     * @param inr
     * @return
     */
    private TreeNode build(int[] pre, int inl, int inr) {
        if (inl > inr) return null;
        TreeNode node = new TreeNode(pre[psize++]); //根节点
        int index = map.get(node.val);
        node.left = build(pre, inl, index - 1);
        node.right = build(pre, index + 1, inr);
        return node;
    }

    @Test
    public void test() {
        int[] preorder = {3, 9, 20, 15, 7}; //前序遍历 : 中左右
        int[] inorder = {9, 3, 15, 20, 7}; //中序遍历  ：左中右
        TreeNode n = buildTree(preorder, inorder);
        System.out.println(n);
    }
}
