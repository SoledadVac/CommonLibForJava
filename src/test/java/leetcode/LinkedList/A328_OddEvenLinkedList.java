package leetcode.LinkedList;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/1
 * \* Time: 31:39 PM
 * \* Description:奇偶链表
 * \
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class A328_OddEvenLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode oldTail = head; //固定在原链表表尾
        while (oldTail.next != null) {
            oldTail = oldTail.next;
        }
        ListNode newTail = oldTail;//移动的队尾
        ListNode p = head;
        while (p.val % 2 == 0) {
            head = p.next;
            newTail.next = p;
            p.next = null;
            p = head;
        }
        while (p != oldTail) {
            ListNode n = p.next;
            if (n.val % 2 == 0) {
                if (p == head) {
                    head = p.next;
                    head.next = p.next.next;
                }
                newTail.next = p;
            }
            p = p.next;
        }

        return head;
    }

    @Test
    public void test() {

    }

}
