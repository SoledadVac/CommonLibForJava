package leetcode.LinkedList;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/31
 * \* Time: 4:05 PM
 * \* Description: 相交链表
 * \
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 */
public class A160_IntersectionOfTwoLinkedLists {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        if (headA == headB) {
            return headA;
        }
        if (p1.next == p2) {
            return p2;
        }
        if (p1 == p2.next) {
            return p1;
        }
        while (p1.next != null || p2.next != null) {
            if (p1.next == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2.next == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
            if (p1 == p2) {
                return p1;
            }
        }
        return null;
    }

    @Test
    public void test() {
       /* ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);
        a1.next = a2;
        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(0);
        b1.next = b2;
        ListNode b3 = new ListNode(1);
        b2.next = b3;
        ListNode c1 = new ListNode(8);
        a2.next = c1;
        b3.next = c1;
        ListNode c2 = new ListNode(4);
        c1.next = c2;
        ListNode c3 = new ListNode(5);
        c2.next = c3;
        ListNode result = getIntersectionNode(a1, b1);*/
       /* ListNode a1 = new ListNode(1);
        ListNode b1 = new ListNode(1);
        a1.next = b1;
        ListNode result = getIntersectionNode(a1, b1);*/
        ListNode a1 = new ListNode(10);
        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        b1.next = b2;
        ListNode b3 = new ListNode(5);
        b2.next = b3;
        ListNode b4 = new ListNode(7);
        b3.next = b4;
        ListNode b5 = new ListNode(9);
        b4.next = b5;
        ListNode b6 = new ListNode(10);
        a1.next = b6;
        b5.next = b6;
        ListNode b7 = new ListNode(11);
        b6.next = b7;
        ListNode result = getIntersectionNode(a1, b1);
        System.out.println(result == null ? "--!!!!--" : result.val);
    }
}
