package leetcode.NTree;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/2
 * \* Time: 27:00 PM
 * \* Description: Maximum Depth of N-ary Tree
 * \
 * 给定一个 N 叉树，找到其最大深度。
 * <p>
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * <p>
 * 例如，给定一个 3叉树 :
 * 我们应返回其最大深度，3。
 * <p>
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A559_MaximumDepthofNaryTree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public int maxDepth(Node root) {
        return maxdep(root, 0);
    }

    private int maxdep(Node root, int depth) {
        if (root == null) {
            return 0;
        }
        if (root.children == null) {
            return depth + 1;
        }
        int max = depth + 1;
        for (Node n : root.children) {
            int dep = maxdep(n, depth + 1);
            max = Math.max(dep, max);
        }
        return max;
    }


    @Test
    public void test() {
        Node n1 = new Node();
        Node n3 = new Node();
        Node n2 = new Node();
        Node n4 = new Node();
        Node n5 = new Node();
        Node n6 = new Node();
        n1.val = 1;
        n3.val = 3;
        n2.val = 2;
        n4.val = 4;
        n5.val = 5;
        n6.val = 6;
        n1.children = Lists.newArrayList(n3, n2, n4);
        n3.children = Lists.newArrayList(n5, n6);
        System.out.println("res = " + maxDepth(n1));
    }
}
