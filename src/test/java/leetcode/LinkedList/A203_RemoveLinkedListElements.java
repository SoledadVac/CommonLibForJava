package leetcode.LinkedList;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/1
 * \* Time: 5:21 PM
 * \* Description: 移除链表元素
 * \
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class A203_RemoveLinkedListElements {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        if (head.next == null && head.val == val) {
            return null;
        }
        while (p.next != null) {
            if (p.val == val) {
                head = p.next;
                p = p.next;
                continue;
            }
            if (p.next.val == val) {
                if (p.next.next != null) {
                    p.next = p.next.next;
                    continue;
                } else {
                    p.next = null;
                    break;
                }
            }
            p = p.next;
        }
        if (head != null && head.next == null && head.val == val) {
            return null;
        }
        return head;
    }


    @Test
    public void test() {
       /* ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(6);
        n2.next = n3;
        ListNode n4 = new ListNode(3);
        n3.next = n4;
        ListNode n5 = new ListNode(4);
        n4.next = n5;
        ListNode n6 = new ListNode(5);
        n5.next = n6;
        ListNode n7 = new ListNode(6);
        n6.next = n7;
        ListNode p = removeElements(n1, 6);*/

    /*    ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(2);
        n2.next = n3;
        ListNode n4 = new ListNode(1);
        n3.next = n4;
        ListNode p = removeElements(n1, 2);*/

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode p = removeElements(n1, 1);
        System.out.println(p.val);
        while (p.next != null) {
            System.out.println(p.next.val);
            p = p.next;
        }
    }
}
