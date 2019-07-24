package leetcode;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/22
 * \* Time: 28:48 PM
 * \* Description: 设计链表
 * \
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，
 * next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，
 * 则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * <p>
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 */
public class A707_DesignLinkedList {

    class MyLinkedList {

        Node head; //头部
        int size; //长度

        /**
         * Initialize your data structure here.
         */
        public MyLinkedList() {

        }

        /**
         * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
         */
        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            if (index == 0) {
                return head.val;
            }
            Node cur = head;
            while (index > 0) {
                index--;
                cur = cur.next;
            }
            return cur.val;
        }

        /**
         * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            Node newHead = new Node(val);
            if (size == 0) {
                head = newHead;
            } else {
                newHead.next = head;
                this.head = newHead;
            }
            size++;
        }

        /**
         * Append a node of value val to the last element of the linked list.
         */
        public void addAtTail(int val) {
            if (size == 0) {
                return;
            }
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node(val);
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
            Node cur = head;
            if (index <= 0) {
                addAtHead(val);
                return;
            }
            if (index == size) {
                addAtTail(val);
                return;
            }
            while (index > 1) {
                index--;
                cur = cur.next;
            }
            Node newNode = new Node(val, cur.next);
            cur.next = newNode;
            size++;
        }

        /**
         * Delete the index-th node in the linked list, if the index is valid.
         */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            if (index == 0) {
                head = head.next;
                size--;
                return;
            }
            Node cur = head;
            while (index > 1) {
                index--;
                cur = cur.next;
            }
            if (cur.next.next == null) {
                cur.next = null;
            }
            if (cur.next != null && cur.next.next != null) {
                cur.next = cur.next.next;
            }
            size--;
        }

        public String consoleAll() {
            StringBuilder result = new StringBuilder();
            Node n = head;
            result.append(n.val + ",");
            while (n.next != null) {
                n = n.next;
                result.append(n.val + ",");
            }
            return result.toString();
        }
    }

    class Node {

        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */
    @Test
    public void test() {
        //["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
        //[[],[1],[3],[1,2],[1],[1],[1]]
       /* MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.addAtTail(2);
        obj.addAtIndex(1, 3);
        int v1 = obj.get(1);
        obj.deleteAtIndex(1);
        int v2 = obj.get(1);
        System.out.println(obj);*/

        //["MyLinkedList","addAtHead","deleteAtIndex"]
        //[[],[1],[0]]
        /*MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.deleteAtIndex(0);*/

        //["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
        //[[],[1],[3],[1,2],[1],[0],[0]]
       /* MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.addAtTail(3);
        obj.addAtIndex(1, 2);
        int v1 = obj.get(1);
        obj.deleteAtIndex(0);
        int v2 = obj.get(0);
        System.out.println(obj);
*/

        //["MyLinkedList","addAtIndex","get","deleteAtIndex"]
        //[[],[-1,0],[0],[-1]]
        //[null,null,0,null]
       /* MyLinkedList obj = new MyLinkedList();
        obj.addAtIndex(-1, 0);
        int v1 = obj.get(0);
        obj.deleteAtIndex(-1);*/


        //["MyLinkedList","addAtHead[99]","addAtHead[11]","deleteAtIndex[2]","addAtTail[93]",
        //[null,null,null,null,null,
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(99);
        obj.addAtHead(11);
        obj.deleteAtIndex(2);
        obj.addAtTail(93);
        System.out.println(obj.consoleAll());
        // "addAtIndex[1,51]","addAtTail[25]","addAtHead[82]","addAtTail[97]","addAtTail[45]",
        //null,null,null,null,null,
        obj.addAtIndex(1, 51);
        obj.addAtTail(25);
        obj.addAtHead(82);
        obj.addAtTail(97);
        obj.addAtTail(45);
        System.out.println(obj.consoleAll());
        // "addAtTail[43]","addAtHead[66]","addAtHead[0]","deleteAtIndex[9]","addAtHead[93]",
        // null,null,null,null,null,
        obj.addAtTail(43);
        obj.addAtHead(66);
        obj.addAtHead(0);
        obj.deleteAtIndex(9);
        obj.addAtHead(93);
        System.out.println(obj.consoleAll());
        // "get[3]","addAtHead[1]","addAtTail[33]","addAtIndex[3,82]","addAtIndex[4,36]",
        // 82,null,null,null,null,
        int v1 = obj.get(3);
        obj.addAtHead(1);
        obj.addAtTail(33);
        obj.addAtIndex(3, 82);
        obj.addAtIndex(5, 97);
        System.out.println(obj.consoleAll());
        // "get[8]","addAtHead[42]","addAtHead[16]","addAtHead[67]","addAtIndex[5,97]",
        // 51,null,null,null,null,
        int v2 = obj.get(8);
        obj.addAtHead(42);
        obj.addAtHead(16);
        obj.addAtHead(67);
        obj.addAtIndex(5, 97);
        System.out.println(obj.consoleAll());
        // "addAtTail[9]","get[19]","deleteAtIndex[12]","addAtTail[79]","addAtTail[43]",
        // null,9,null,null,null,
        obj.addAtTail(9);
        int v3 = obj.get(19);
        obj.deleteAtIndex(12);
        obj.addAtTail(79);
        obj.addAtTail(43);
        System.out.println(obj.consoleAll());
        // "addAtTail[96]","addAtTail[52]","get[3]","get[4]","addAtHead[46]",
        // null,null,1,93,null,
        obj.addAtTail(96);
        obj.addAtTail(52);
        int v4 = obj.get(3);
        int v5 = obj.get(4);
        obj.addAtHead(46);
        System.out.println(obj.consoleAll());
        // "addAtIndex[13,16]","addAtTail[32]","addAtTail[79]","addAtTail[28]","deleteAtIndex[20]",
        // null,null,null,null,null,
        obj.addAtIndex(13, 16);
        obj.addAtTail(32);
        obj.addAtTail(79);
        obj.addAtTail(28);
        obj.deleteAtIndex(20);
        System.out.println(obj.consoleAll());
        // "addAtIndex[10,70]","deleteAtIndex[27]","deleteAtIndex[14]","addAtHead[13]","addAtTail[81]",
        // null,null,null,null,null,
        obj.addAtIndex(10, 70);
        obj.deleteAtIndex(27); //todo :这里有问题啊。。。
        obj.deleteAtIndex(14);
        obj.addAtHead(13);
        obj.addAtTail(81);
        System.out.println(obj.consoleAll());
        // "addAtTail[69]","addAtTail[26]","deleteAtIndex[10]","addAtTail[57]","deleteAtIndex[6]",
        // null,null,null,null,null,
        obj.addAtTail(69);
        obj.addAtTail(26);
        obj.deleteAtIndex(10);
        obj.addAtTail(57);
        obj.deleteAtIndex(6);
        System.out.println(obj.consoleAll());
        // "addAtIndex[24,30]","addAtTail[89]","addAtTail[40]","addAtTail[14]","addAtIndex[23,92]",
        // null,null,null,null,null,
        obj.addAtIndex(24, 30);
        obj.addAtTail(89);
        obj.addAtTail(40);
        obj.addAtTail(14);
        obj.addAtIndex(23, 92);
        System.out.println(obj.consoleAll());
        // "deleteAtIndex[19]","addAtTail[14]","addAtHead[32]","addAtTail[19]","addAtTail[91]",
        // null,null,null,null,null,
        obj.deleteAtIndex(19);
        obj.addAtTail(14);
        System.out.println(obj.consoleAll());
        obj.addAtHead(32);
        obj.addAtTail(19);
        obj.addAtTail(91);
        System.out.println(obj.consoleAll());
        // "addAtTail[8]","get[4]","addAtTail[57]","get[23]","get[36]",
        // null,16,null,92,91,
        obj.addAtTail(8);
        int v6 = obj.get(4);
        obj.addAtTail(57);
        int v7 = obj.get(23);
        System.out.println(obj.consoleAll());
        int v8 = obj.get(36);
        System.out.println(obj.consoleAll());
        // "deleteAtIndex[24]","addAtTail[61]","addAtTail[62]","addAtHead[56]","addAtIndex[18,28]",
        // null,null,null,null,null,
        obj.deleteAtIndex(24);
        obj.addAtTail(61);
        obj.addAtTail(62);
        obj.addAtHead(56);
        obj.addAtIndex(18, 28);
        System.out.println(obj.consoleAll());
        // "deleteAtIndex[17]","addAtHead[15]","addAtTail[91]","addAtTail[54]","addAtHead[87]",
        // null,null,null,null,null,
        obj.deleteAtIndex(17);
        obj.addAtHead(15);
        obj.addAtTail(91);
        obj.addAtTail(54);
        obj.addAtHead(87);
        System.out.println(obj.consoleAll());
        // "addAtHead[70]","addAtIndex[14,66]","addAtIndex[36,60]","addAtHead[97]","addAtTail[12]",
        // null,null,null,null,null,
        obj.addAtHead(70);
        obj.addAtIndex(14, 66);
        obj.addAtIndex(36, 60);
        obj.addAtHead(97);
        obj.addAtTail(12);
        System.out.println(obj.consoleAll());
        // "addAtTail[13]","addAtTail[51]","addAtHead[45]","addAtHead[4]","addAtHead[98]",
        // null,null,null,null,null,
        obj.addAtTail(13);
        obj.addAtTail(51);
        obj.addAtHead(45);
        obj.addAtHead(4);
        obj.addAtHead(98);
        System.out.println(obj.consoleAll());
        // "addAtTail[57]","addAtIndex[1,12]","addAtTail[89]","addAtIndex[49,41]","get[17]",
        // null,null,null,null,0,
        obj.addAtTail(57);
        obj.addAtIndex(1, 12);
        obj.addAtTail(89);
        obj.addAtIndex(49, 41);
        int v9 = obj.get(17);
        System.out.println(obj.consoleAll());
        // "get[27]","addAtTail[0]","addAtIndex[17,6]","addAtHead[12]","addAtHead[87]",
        // 97,null,null,null,null,
        int v10 = obj.get(27);
        obj.addAtTail(0);
        obj.addAtIndex(17, 6);
        obj.addAtHead(12);
        obj.addAtHead(87);
        System.out.println(obj.consoleAll());
        // "addAtHead[78]","deleteAtIndex[5]","addAtHead[80]","addAtTail[34]","addAtHead[45]",
        // null,null,null,null,null,
        obj.addAtHead(78);
        obj.deleteAtIndex(5);
        obj.addAtHead(80);
        obj.addAtTail(34);
        obj.addAtHead(45);
        System.out.println(obj.consoleAll());
        // "addAtHead[7]","deleteAtIndex[58]"]
        // null,null]
        obj.addAtHead(7);
        obj.deleteAtIndex(58);
        System.out.println(obj.consoleAll());
        System.out.println(obj);

    }


}
