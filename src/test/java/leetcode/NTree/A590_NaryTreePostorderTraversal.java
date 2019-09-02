package leetcode.NTree;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/30
 * \* Time: 31:26 PM
 * \* Description: N叉树的后序遍历
 * \
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 返回其后序遍历: [5,6,3,2,4,1].
 */
public class A590_NaryTreePostorderTraversal {

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

    /**
     * 递归方法
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        postorder(root, res);
        return res;
    }

    void postorder(Node root, List<Integer> res) {
        if (root.children == null) {
            res.add(root.val);
            return;
        }
        for (Node n : root.children) {
            postorder(n, res);
        }
        res.add(root.val);
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public List<Integer> postorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<Node> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            Node node = list.pollLast();
            res.add(node.val);
            if(node.children==null){
                continue;
            }
            list.addAll(node.children);
        }
        Collections.reverse(res);
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
        System.out.println("res = " + JSONObject.toJSONString(postorder1(n1)));
    }

}
