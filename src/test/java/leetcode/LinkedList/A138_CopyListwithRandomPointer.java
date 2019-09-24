package leetcode.LinkedList;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/13
 * \* Time: 29:45 PM
 * \* Description:复制带随机指针的链表
 * \
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的深拷贝。
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 * <p>
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *  
 * <p>
 * 提示：
 * <p>
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 */
public class A138_CopyListwithRandomPointer {
    // Definition for a Node.
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }


    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // todo : 复制带随机指针的链表


        return null;

    }

    @Test
    public void test() {
        Node n1 = new Node();
        n1.val = 1;
        Node n2 = new Node();
        n2.val = 1;
        n1.next = n2;
        n1.random = n2;
        n2.random = n2;

    }

}
