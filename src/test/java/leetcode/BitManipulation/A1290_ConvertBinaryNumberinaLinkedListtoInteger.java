package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/12/25
 * \* Time: 29:57 下午
 * \* Description:二进制链表转整数
 * \
 */
public class A1290_ConvertBinaryNumberinaLinkedListtoInteger {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result = (result << 1) | head.val;
            head = head.next;
        }
        return result;
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
        ListNode head = new ListNode(1);
        ListNode l1 = new ListNode(0);
        head.next = l1;
        ListNode l2 = new ListNode(1);
        l1.next = l2;
        System.out.println(getDecimalValue(head));
    }

}
