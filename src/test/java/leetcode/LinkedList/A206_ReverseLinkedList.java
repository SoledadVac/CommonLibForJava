package leetcode.LinkedList;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/1
 * \* Time: 4:52 PM
 * \* Description: 反转链表
 * \
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * <p>
 * <p>
 */
public class A206_ReverseLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = head;
        ListNode p = head;
        while (p.next != null) {
            ListNode temp = p.next;
            if (temp.next != null) {
                p.next = temp.next;
            } else {
                p.next = null;
            }
            temp.next = newHead;
            newHead = temp;
        }
        return newHead;
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
        ListNode result = reverseList(n1);
        System.out.println(result.val);
        while (result.next != null) {
            result = result.next;
            System.out.println(result.val);
        }
    }
}
