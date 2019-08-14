package leetcode.LinkedList;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/13
 * \* Time: 29:47 PM
 * \* Description: 旋转链表
 * \
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * <p>
 */
public class A61_RotateList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        if (k < 1) {
            return head;
        }
        ListNode headTemp = head; //动态头结点
        ListNode tailTemp = null; //动态尾结点
        ListNode p = head;
        int sumNum = 1;
        while (p.next != null) {
            p = p.next;
            sumNum++;
        }
        tailTemp = p;
        k = k % sumNum;
        while (k > 0) {
            //先找到此时结点的末尾结点的的前一个结点
            ListNode temp = headTemp;
            ListNode prev = null;
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            //每次循环，末尾结点往头结点移动一个
            tailTemp.next = headTemp;
            headTemp = tailTemp;
            prev.next = null;
            tailTemp = prev;
            k--;
        }
        return headTemp;
    }

    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
      /*  ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);*/
        n1.next = n2;
        n2.next = n3;
        // n3.next = n4;
        // n4.next = n5;
        ListNode result = rotateRight(n1, 2000000000);
        System.out.println("finish , result = " + result.val);
    }

}
