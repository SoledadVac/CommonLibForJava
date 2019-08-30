package leetcode.NTree;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/30
 * \* Time: 30:26 PM
 * \* Description:   N-ary Tree Preorder Traversal
 * \
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 *  
 * <p>
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * <p>
 *  
 * <p>
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A589_NaryTreePreorderTraversal {

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

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        if (root.children == null || root.children.size() < 1) {
            return res;
        }
        for (Node n : root.children) {
            List<Integer> items = preorder(n);
            res.addAll(items);
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
        System.out.println("res = " + JSONObject.toJSONString(preorder(n1)));
    }
}
