package leetcode.LinkedList;

import org.junit.Test;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/6
 * \* Time: 11:18 AM
 * \* Description: 回文链表
 * \
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class A234_PalindromeLinkedList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        ListNode fast = head;   //走到链表next=null,回到慢指针前面的一个指针，此时两指针一起移动，判断
        ListNode slow = head; //在快指针未走到末尾之前，都进行反转走过的结点
        ListNode slowNext = slow.next;
        Integer times = 0;
        while (fast.next != null) {
            times++;
            fast = fast.next.next; //块指针的移动两步
            ListNode prev = slow; //记录下慢指针原位置
            if (times == 1) {
                prev.next = null;
            }
            if (fast == null) {
                fast = slowNext;
                break;
            }
            if (fast.next == null) {
                fast = slowNext.next;
                break;
            }
            slow = slowNext; //慢指针移动一位
            slowNext = slowNext.next;//记录慢指针下一次的位置
            slow.next = prev;

        }
        //两指针一起移动
        while (slow != null || fast != null) {
            if (slow == null && fast != null) {
                return false;
            }
            if (slow != null && fast == null) {
                return false;
            }
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    @Test
    public void test() {
        /*ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;*/

      /*  ListNode n1 = new ListNode(0);
        ListNode n2 = new ListNode(0);
        n1.next = n2;*/

       /* ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(0);
        n1.next = n2;
        n2.next = n3;*/

       /* ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(-1);
        ListNode n4 = new ListNode(-1);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;*/

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(0);
        n1.next = n2;
        n2.next = n3;
        System.out.println("result = " + isPalindrome(n1));
    }


}
