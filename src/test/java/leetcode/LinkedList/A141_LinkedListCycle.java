package leetcode.LinkedList;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/24
 * \* Time: 28:55 PM
 * \* Description:环形链表
 * \
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 要求：你能用 O(1)（即，常量）内存解决此问题吗
 */
public class A141_LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        //注意题目要求空间复杂度为常量
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p2 != null) {
            if (p1.next == null || p2.next == null || p2.next.next == null) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        //head = [3,2,0,-4], pos = 1
      /*  ListNode n0 = new ListNode(3);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(-4);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;*/
        ListNode n0 = new ListNode(1);
        System.out.println(hasCycle(n0));
    }
}
