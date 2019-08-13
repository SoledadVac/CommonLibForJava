package leetcode.LinkedList;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/12
 * \* Time: 31:45 PM
 * \* Description: 双向链表
 * <p>
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。
 * val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。
 * 假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，
 * 则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * \
 */
public class A707_DesignLinkedList2 {

    class MyLinkedList {

        Node head; //头部
        int size; //长度

        /**
         * Initialize your data structure here.
         */
        public MyLinkedList() {

        }

        /**
         * Get the value of the index-th node in the linked list.
         * If the index is invalid, return -1.
         */
        public int get(int index) {
            Node node = getNode(index);
            if (node == null) {
                return -1;
            }
            return node.val;
        }

        private Node getNode(int index) {
            if (index < 0 || index >= size) {
                return null;
            }
            Node cur = head;
            int idx = 0;
            if (index == 0) {
                return head;
            }
            while (cur.next != null) {
                idx++;
                cur = cur.next;
                if (idx == index) {
                    return cur;
                }
            }
            return null;
        }

        /**
         * Add a node of value val before the first element of the linked list.
         * After the insertion,
         * the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            Node newHead = new Node(val, null, head);
            if (head != null) {
                head.prev = newHead;
            }
            head = newHead;
            size++;

        }

        /**
         * Append a node of value val to the last element of the linked list.
         */
        public void addAtTail(int val) {
            if (size == 0) {
                addAtHead(val);
                return;
            }
            Node oldTail = getNode(size - 1);
            Node newTail = new Node(val, oldTail, null);
            oldTail.next = newTail;
            size++;
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list,
         * the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
         */
        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0 || index == 0) {
                addAtHead(val);
                return;
            }
            if (index == size) {
                addAtTail(val);
                return;
            }
            Node prev = getNode(index - 1);
            Node next = getNode(index);
            Node newNode = new Node(val, prev, next);
            prev.next = newNode;
            next.prev = newNode;
            size++;
        }

        /**
         * Delete the index-th node in the linked list, if the index is valid.
         */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            Node delNode = getNode(index);
            if (delNode == null) {
                return;
            }
            Node prevNode = delNode.prev;
            Node nextNode = delNode.next;
            if (prevNode != null) {
                prevNode.next = nextNode;
            }
            if (nextNode != null) {
                nextNode.prev = prevNode;
            }
            if (index == 0) {
                head = nextNode;
            }
            delNode.prev = null;
            delNode.next = null;
            size--;
        }
    }

    class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node prev, Node next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }


    @Test
    public void test() {

        //["MyLinkedList","addAtIndex","get","deleteAtIndex"]
        //[[],[-1,0],[0],[-1]]
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtIndex(-1, 0);
        int a = linkedList.get(0);
        System.out.println("a = " + a);
        linkedList.deleteAtIndex(-1);


        //["MyLinkedList[]","addAtHead[1]","addAtTail[3]"
        // ,"addAtIndex[1,2]","get[1]","deleteAtIndex[0]","get[0]"]
       /* MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);
        int a = linkedList.get(1);
        System.out.println("a = " + a);
        linkedList.deleteAtIndex(0);
        int b = linkedList.get(0);
        System.out.println("b = " + b);*/


        //["MyLinkedList",
        // "addAtHead[0]","addAtIndex[1,9]","addAtIndex[1,5]"
        // ,"addAtTail[7]","addAtHead[1]","addAtIndex[5,8]",
        // "addAtIndex[5,2]","addAtIndex[3,0]","addAtTail[1]",
        // "addAtTail[0]","deleteAtIndex[6]"]
       /* MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(0);
        linkedList.addAtIndex(1, 9);
        linkedList.addAtIndex(1, 5);
        linkedList.addAtTail(7);
        linkedList.addAtHead(1);
        linkedList.addAtIndex(5, 8);
        linkedList.addAtIndex(5, 2);
        linkedList.addAtIndex(3, 0);
        linkedList.addAtTail(1);
        linkedList.addAtTail(0);
        linkedList.deleteAtIndex(6);
        System.out.println("");*/

         /*MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);   //链表变为1-> 2-> 3
        int a = linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        int b = linkedList.get(1);            //返回3
        System.out.println("");*/

       /* MyLinkedList linkedList = new MyLinkedList();
        int a = linkedList.get(0);
        System.out.println("a = " + a);
        linkedList.addAtIndex(1, 2);
        int b = linkedList.get(0);
        System.out.println("b = " + b);
        int c = linkedList.get(1);
        System.out.println("c = " + c);
        linkedList.addAtIndex(0, 1);
        int d = linkedList.get(0);
        System.out.println("d = " + d);
        int e = linkedList.get(1);
        System.out.println("e = " + e);*/

    }
}
