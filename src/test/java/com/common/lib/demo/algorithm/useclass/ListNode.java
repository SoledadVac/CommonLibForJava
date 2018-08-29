package com.common.lib.demo.algorithm.useclass;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/7/5
 * \* Time: 下午9:15
 * \* Description:单向链表
 * \
 */
public class ListNode {

    ListNode next;
    int val;

    /**
     * construction
     * @param val
     */
    public ListNode(int val) {
        this.next = null;
        this.val = val;
    }


    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        node1.next=node2;
        node2.next=node3;
        ListNode  reversedNode=reverse2(node1);
        List<ListNode> result=getAllNodes(reversedNode);
        for(ListNode item :result){
            System.out.println("val="+item.val);
        }
    }

    /**
     * 顺序获取所有链表结点
     * @param head
     * @return
     */
    static List<ListNode> getAllNodes(ListNode head){
        List<ListNode> result=new ArrayList<>();
        ListNode tmp=head;
        while (tmp.next!=null){
            result.add(tmp);
            tmp=tmp.next;
        }
        if(tmp.next==null){
            result.add(tmp);
        }
        return result;
    }


    static ListNode reverse2(ListNode head){
        ListNode prev=null;
        while (head!=null){
            ListNode tmp=head.next;
            head.next=prev;
            prev=head;
            head=tmp;
        }
        return prev;
    }

    /**
     * 翻转链表
     * @param node
     * @return
     */
    static ListNode reverse(ListNode node){
        ListNode prev=null;
        while(node!=null){
            ListNode tmp= node.next;
            node.next=prev;
            prev = node;
            node = tmp;
        }
        return prev;
    }

    /**
     * 翻转链表--from head
     * @param head
     * @return
     */
    static ListNode reverse1(ListNode head){
        if(head.next==null){
            return head;
        }
        ListNode reverseNode=reverse1(head.next);
        head.next.next=head;
        head.next=null;
        return reverseNode;
    }








}
