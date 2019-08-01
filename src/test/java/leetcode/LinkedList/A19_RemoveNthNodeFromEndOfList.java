package leetcode.LinkedList;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/1
 * \* Time: 28:25 PM
 * \* Description: 删除链表的倒数第N个节点
 * \
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 */
public class A19_RemoveNthNodeFromEndOfList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> data = new HashMap<>();
        ListNode p = head;
        data.put(0, p);
        Integer index = 1;
        while (p.next != null) {
            p = p.next;
            data.put(index, p);
            index++;
        }
        if (n > index + 1) {
            return head;
        }
        Integer getIndex = index - n;
        ListNode removeNode = data.get(getIndex);
        if (getIndex == 0) {
            //首节点
            removeNode.next = null;
            return data.get(1);
        }
        if (n == 1) {
            data.get(getIndex - 1).next = null;
            return head;
        }
        ListNode pre = data.get(getIndex - 1);
        ListNode back = data.get(getIndex + 1);
        pre.next = back;
        return head;
    }

    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(3);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;
        ListNode n5 = new ListNode(5);
        n4.next = n5;
        ListNode result = removeNthFromEnd(n1, 1);
        System.out.println(result.val);
        while (result.next != null) {
            result = result.next;
            System.out.println(result.val);
        }
    }
}
