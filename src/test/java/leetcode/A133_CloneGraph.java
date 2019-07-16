package leetcode;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/12
 * \* Time: 27:48 PM
 * \* Description:克隆图
 * \
 * 给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 * <p>
 * 解释：
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 *  
 * <p>
 * 提示：
 * <p>
 * 节点数介于 1 到 100 之间。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 必须将给定节点的拷贝作为对克隆图的引用返回。
 */
public class A133_CloneGraph {
    public Node cloneGraph(Node node) {
        Map<Integer, Node> copyMap = new HashMap<>();
        Stack<Node> stack = new Stack();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node item = stack.pop();
            Node copy = copyMap.getOrDefault(item.val, new Node(item.val, null));
            copy.val = item.val;
            copyMap.put(copy.val, copy);
            if (item.neighbors == null || item.neighbors.size() < 1) {
                continue;
            }
            for (Node n : item.neighbors) {
                Node temp = copyMap.getOrDefault(n.val, new Node(n.val, null));
                temp.val = n.val;
                if (copy.neighbors == null) {
                    copy.neighbors = new ArrayList<>();
                }
                if (copy.neighbors.contains(temp)) {
                    continue;
                }
                copy.neighbors.add(temp);
                if (temp.neighbors == null) {
                    temp.neighbors = new ArrayList<>();
                }
                temp.neighbors.add(copy);
                stack.push(n);
                copyMap.put(temp.val, temp);
            }
        }
        return copyMap.get(node.val);
    }

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    @Test
    public void test() {
       /* Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();
        n1.val = 1;
        n2.val = 2;
        n3.val = 3;
        n4.val = 4;
        n1.neighbors = Lists.newArrayList(n2, n4);
        n2.neighbors = Lists.newArrayList(n3, n1);
        n3.neighbors = Lists.newArrayList(n2, n4);
        n4.neighbors = Lists.newArrayList(n1, n3);*/
        Node n1 = new Node();
        n1.val = 1;
        System.out.println(JSONObject.toJSONString(cloneGraph(n1)));
    }
}
