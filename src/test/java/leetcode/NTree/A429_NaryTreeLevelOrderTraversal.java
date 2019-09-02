package leetcode.NTree;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/2
 * \* Time: 34:39 AM
 * \* Description: N叉树的层序遍历
 * \
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * <p>
 * 例如，给定一个 3叉树 :
 * 返回其层序遍历:
 * <p>
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 * 说明:
 * <p>
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。z
 */
public class A429_NaryTreeLevelOrderTraversal {

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

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> data = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                data.add(node.val);
                if (node.children != null && node.children.size() > 0) {
                    queue.addAll(node.children);
                }
            }
            res.add(data);
        }
        return res;
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
        System.out.println("result = " + JSONObject.toJSONString(levelOrder(n1)));
    }
}
