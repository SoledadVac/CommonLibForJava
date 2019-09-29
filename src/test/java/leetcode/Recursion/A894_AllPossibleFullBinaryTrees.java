package leetcode.Recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/27
 * \* Time: 28:14 PM
 * \* Description: 所有可能的满二叉树
 * \
 * 满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。
 * <p>
 * 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。
 * <p>
 * 答案中每个树的每个结点都必须有 node.val=0。
 * <p>
 * 你可以按任何顺序返回树的最终列表。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-possible-full-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A894_AllPossibleFullBinaryTrees {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N % 2 == 0) {
            return res;
        }
        if (N == 1) {
            TreeNode root = new TreeNode(0);
            res.add(root);
            return res;
        }
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - 1 - i);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(0);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }


    @Test
    public void test() {
        int N = 7;
        System.out.println(allPossibleFBT(7));
    }

}
