package leetcode.LinkedList;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/13
 * \* Time: 29:06 PM
 * \* Description: 合并两个有序链表
 * \
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class A21_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode result = null;
        ListNode resultHead = null;
        ListNode pl1 = l1;
        ListNode pl2 = l2;
        while (pl1 != null || pl2 != null) {
            Integer v1 = pl1 == null ? Integer.MAX_VALUE : pl1.val;
            Integer v2 = pl2 == null ? Integer.MAX_VALUE : pl2.val;
            if (v1 <= v2) {
                pl1 = pl1.next;
            } else {
                pl2 = pl2.next;
            }
            ListNode temp = new ListNode(Math.min(v1, v2));
            if (result == null) {
                result = temp;
                resultHead = temp;
            } else {
                result.next = temp;
                result = result.next;
            }
        }
        return resultHead;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        ListNode result = mergeTwoLists(l1, n1);
        System.out.println("finish--- result = " + result.val);
    }
}
