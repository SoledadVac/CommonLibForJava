package leetcode.LinkedList;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.internal.cglib.proxy.$Factory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/25
 * \* Time: 28:45 下午
 * \* Description:  删除排序链表中的重复元素 II
 * \
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A82_RemoveDuplicatesFromSortedListII {

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
        ListNode beginPrev = null;
        while (end != null) {
            if (begin.val != end.val) {
                beginPrev = begin;
                begin = end;
                end = begin.next;
                continue;
            }
            //相等情况
            while (end.next != null && end.next.val == begin.val) {
                end = end.next;
            }
            //end 此时为重复的最后一个
            if (begin == head) {
                head = end.next;
            } else {
                beginPrev.next = end.next;
            }
            begin = end.next;
            end = begin == null ? null : begin.next;
        }
        return head;
    }

    @Test
    public void test() {
        //build list
        List<Integer> data = Arrays.asList(1, 2, 2);
        ListNode head = null;
        ListNode current = null;
        for (int i = 0; i < data.size(); i++) {
            if (head == null) {
                head = new ListNode(data.get(i));
                current = head;
                continue;
            }
            ListNode node = new ListNode(data.get(i));
            current.next = node;
            current = node;
        }

        //test
        ListNode result = deleteDuplicates(head);
        List<Integer> resultList = new ArrayList<>();
        if (result == null) {
            System.out.println("null");
            return;
        }
        resultList.add(result.val);
        while (result.next != null) {
            result = result.next;
            resultList.add(result.val);
        }
        System.out.println(JSONObject.toJSONString(resultList));
    }
}
