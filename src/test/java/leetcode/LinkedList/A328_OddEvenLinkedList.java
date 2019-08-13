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
        if (head == null) {
            return null;
        }
        ListNode oldTail = head; //固定在原链表表尾
        while (oldTail.next != null) {
            oldTail = oldTail.next;
        }
        ListNode newTail = oldTail;//移动的队尾
        ListNode p = head; //移动的指针，用于遍历固定队尾之前的结点
        ListNode prev = null;
        int index = 1;
        Boolean isResetOldTaile = false;
        while (p != oldTail) {
            if (index % 2 == 0) {
                //偶数，放到队尾后
                if (oldTail.next == null &&
                        !isResetOldTaile) {
                    isResetOldTaile = true;
                    oldTail = p;
                }
                ListNode next = p.next;
                p.next = null;
                newTail.next = p;
                newTail = p;
                if (prev != null) {
                    prev.next = next;
                }
                p = next;
                index++;
                continue;
            }
            index++;
            prev = p;
            p = p.next;
        }
        return head;
    }

    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        ListNode result = oddEvenList(n1);
        System.out.println("finish  -------------- result.val = " + result.val);
    }

}
