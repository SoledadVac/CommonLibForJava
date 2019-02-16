package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/2/15
 * \* Time: 4:42 PM
 * \* Description:
 * <p>
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * \
 */
public class A2_TwoSumLinkNode {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Deprecated
    /**
     * 相加分割的方式链表长了数据会有溢出
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> l1Value = new ArrayList<>();
        List<Integer> l2Value = new ArrayList<>();
        while (l1 != null) {
            l1Value.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            l2Value.add(l2.val);
            l2 = l2.next;
        }
        long l1Int = 0;
        long l2Int = 0;
        for (int i = l1Value.size() - 1; i >= 0; i--) {
            l1Int += l1Value.get(i) * Math.pow(10, i);
        }
        for (int i = l2Value.size() - 1; i >= 0; i--) {
            l2Int += l2Value.get(i) * Math.pow(10, i);
        }
        long result = l1Int + l2Int;
        String strResult = String.valueOf(result);
        if (strResult.indexOf("-") > -1) {
            strResult = strResult.substring(1);
        }
        char[] characters = strResult.toCharArray();
        ListNode resultNode = null;
        for (int i = 0; i < characters.length; i++) {
            if (resultNode == null) {
                resultNode = new ListNode(Integer.valueOf(String.valueOf(characters[i])));
                continue;
            }
            ListNode node = new ListNode(Integer.valueOf(String.valueOf(characters[i])));
            node.next = resultNode;
            resultNode = node;
        }
        return resultNode;
    }

    /**
     * 因为转换之后相加的方式会产生溢出，所以，采用逐位相加再存储的方式
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode resultNode = null;
        Map<Integer, Integer> l1Value = new HashMap<>();
        Map<Integer, Integer> l2Value = new HashMap<>();
        List<Integer> resultValue = new ArrayList<>();
        int i1 = 0;
        int i2 = 0;
        while (l1 != null) {
            l1Value.put(i1, l1.val);
            l1 = l1.next;
            i1++;
        }
        while (l2 != null) {
            l2Value.put(i2, l2.val);
            l2 = l2.next;
            i2++;
        }

        int addNumber = 0;
        int length = l1Value.keySet().size() > l2Value.keySet().size() ? l1Value.keySet().size() : l2Value.keySet().size();
        for (int i = 0; i < length; i++) {
            int l1Temp = l1Value.get(i) == null ? 0 : l1Value.get(i);
            int l2Temp = l2Value.get(i) == null ? 0 : l2Value.get(i);
            int addResultTemp = l1Temp + l2Temp + addNumber;
            if (addResultTemp > 9) {
                addNumber = addResultTemp / 10;
                resultValue.add(addResultTemp % 10);
            } else {
                resultValue.add(addResultTemp);
                addNumber = 0;
            }
        }
        if (addNumber != 0) {
            resultValue.add(addNumber);
        }
        for (int i = resultValue.size() - 1; i >= 0; i--) {
            if (i == resultValue.size() - 1) {
                resultNode = new ListNode(resultValue.get(i));
                continue;
            }
            ListNode temp = new ListNode(resultValue.get(i));
            temp.next = resultNode;
            resultNode = temp;
        }
        return resultNode;
    }

    /**
     * 用于优化以上方法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode resultNode = new ListNode(0);
        ListNode currentNode = resultNode;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int l1TempValue = l1 != null ? l1.val : 0;
            int l2TempValue = l2 != null ? l2.val : 0;
            int tempAddResult = l1TempValue + l2TempValue + carry;
            carry = tempAddResult / 10;
            ListNode temp = new ListNode(tempAddResult % 10);
            currentNode.next = temp;
            currentNode = temp;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            ListNode last = new ListNode(carry);
            currentNode.next = last;
        }
        return resultNode.next;
    }

    @Test
    public void test() {
        /*ListNode one1 = new ListNode(9);


        ListNode two1 = new ListNode(1);
        ListNode two2 = new ListNode(9);
        two1.next = two2;
        ListNode two3 = new ListNode(9);
        two2.next = two3;
        ListNode two4 = new ListNode(9);
        two3.next = two4;
        ListNode two5 = new ListNode(9);
        two4.next = two5;
        ListNode two6 = new ListNode(9);
        two5.next = two6;
        ListNode two7 = new ListNode(9);
        two6.next = two7;
        ListNode two8 = new ListNode(9);
        two7.next = two8;
        ListNode two9 = new ListNode(9);
        two8.next = two9;
        ListNode two10 = new ListNode(9);
        two9.next = two10;
*/


        ListNode one1 = new ListNode(2);
        ListNode one2 = new ListNode(4);
        one1.next = one2;
        ListNode one3 = new ListNode(3);
        one2.next = one3;

        ListNode two1 = new ListNode(5);
        ListNode two2 = new ListNode(6);
        two1.next = two2;
        ListNode two3 = new ListNode(4);
        two2.next = two3;


        /*ListNode one1 = new ListNode(1);


        ListNode two1 = new ListNode(9);
        ListNode two2 = new ListNode(9);
        two1.next = two2;
        ListNode two3 = new ListNode(9);
        two2.next = two3;
        ListNode two4 = new ListNode(9);
        two3.next = two4;
        ListNode two5 = new ListNode(9);
        two4.next = two5;
        ListNode two6 = new ListNode(9);
        two5.next = two6;
        ListNode two7 = new ListNode(9);
        two6.next = two7;
        ListNode two8 = new ListNode(9);
        two7.next = two8;
        ListNode two9 = new ListNode(9);
        two8.next = two9;
        ListNode two10 = new ListNode(9);
        two9.next = two10;
        ListNode two11 = new ListNode(9);
        two10.next = two11;
        ListNode two12 = new ListNode(9);
        two11.next = two12;
        ListNode two13 = new ListNode(9);
        two12.next = two13;
        ListNode two14 = new ListNode(9);
        two13.next = two14;
        ListNode two15 = new ListNode(9);
        two14.next = two15;
        ListNode two16 = new ListNode(9);
        two15.next = two16;
        ListNode two17 = new ListNode(9);
        two16.next = two17;
        ListNode two18 = new ListNode(9);
        two17.next = two18;
        ListNode two19 = new ListNode(9);
        two18.next = two19;
        ListNode two20 = new ListNode(9);
        two19.next = two20;
        ListNode two21 = new ListNode(9);
        two20.next = two21;
        ListNode two22 = new ListNode(9);
        two21.next = two22;
        ListNode two23 = new ListNode(9);
        two22.next = two23;
        ListNode two24 = new ListNode(9);
        two23.next = two24;
        ListNode two25 = new ListNode(9);
        two24.next = two25;
        ListNode two26 = new ListNode(9);
        two25.next = two26;
        ListNode two27 = new ListNode(9);
        two26.next = two27;
        ListNode two28 = new ListNode(9);
        two27.next = two28;
        ListNode two29 = new ListNode(9);
        two28.next = two29;
        ListNode two30 = new ListNode(9);
        two29.next = two30;
        ListNode two31 = new ListNode(9);
        two30.next = two31;
        ListNode two32 = new ListNode(9);
        two31.next = two32;
        ListNode two33 = new ListNode(9);
        two32.next = two33;
        ListNode two34 = new ListNode(9);
        two33.next = two34;
        ListNode two35 = new ListNode(9);
        two34.next = two35;
        ListNode two36 = new ListNode(9);
        two35.next = two36;*/

        ListNode result = addTwoNumbers2(one1, two1);
        System.out.println(JSONObject.toJSONString(result));
    }


}
