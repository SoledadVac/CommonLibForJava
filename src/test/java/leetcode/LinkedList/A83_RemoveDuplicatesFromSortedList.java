package leetcode.LinkedList;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/26
 * \* Time: 25:36 下午
 * \* Description: 删除排序链表中的重复元素
 * \
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * <p>
 * 返回同样按升序排列的结果链表。
 */
public class A83_RemoveDuplicatesFromSortedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode begin = head; //区域开始
        ListNode end = head.next; //区域结束
        while (end != null) {
            if (begin.val != end.val) {
                begin = end;
                end = begin.next;
                continue;
            }
            //相等情况
            while (end.next != null && end.next.val == begin.val) {
                end = end.next;
            }
            begin.next = end.next;
            begin = end.next;
            end = begin == null ? null : begin.next;
        }
        return head;
    }
}
