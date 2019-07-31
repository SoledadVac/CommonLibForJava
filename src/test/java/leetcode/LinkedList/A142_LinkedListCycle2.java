package leetcode.LinkedList;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/28
 * \* Time: 12:54 PM
 * \* Description: 环形链表 II
 * \
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * <p>
 * <p>
 */
public class A142_LinkedListCycle2 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        //注意题目要求空间复杂度为常量
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode firstMeet = null;
        while (p1 != null && p2 != null) {
            if (p1.next == null || p2.next == null || p2.next.next == null) {
                return null;
            }
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                firstMeet = p1;
                break;
            }
        }
        if (firstMeet == null) {
            return null;
        }
        p1 = head;
        p2 = firstMeet;
        if (head == firstMeet) {
            return p1;
        }
        while (p1.next != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
            if (p1 == p2) {
                return p1;
            }
        }
        return null;
    }


    @Test
    public void test() {
        //head = [3,2,0,-4], pos = 1
       /* ListNode n0 = new ListNode(3);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(-4);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;*/
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        n2.next = n1;
        System.out.println(detectCycle(n1).val);
    }

}
