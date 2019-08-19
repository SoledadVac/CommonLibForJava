package leetcode.HashTable;

import org.junit.Test;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/14
 * \* Time: 30:41 PM
 * \* Description: 设计哈希映射
 * \
 * 不使用任何内建的哈希表库设计一个哈希映射
 * <p>
 * 具体地说，你的设计应该包含以下的功能
 * <p>
 * put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
 * get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
 * remove(key)：如果映射中存在这个键，删除这个数值对。
 * <p>
 * 示例：
 * <p>
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);          
 * hashMap.put(2, 2);        
 * hashMap.get(1);            // 返回 1
 * hashMap.get(3);            // 返回 -1 (未找到)
 * hashMap.put(2, 1);         // 更新已有的值
 * hashMap.get(2);            // 返回 1
 * hashMap.remove(2);         // 删除键为2的数据
 * hashMap.get(2);            // 返回 -1 (未找到)
 * <p>
 * 注意：
 * <p>
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希库。
 */
public class A706_DesignHashMap {

    /**
     * 类似 JDK 1.7 实现方法
     * 缩略版
     */
    class MyHashMap {

        private int size;//总容量

        private int initBucketLength = 1 << 10; //16，跟源码里面设置默认的一样

        private float loadFactor = 0.75F; //负载因子，这些都先用默认值了，不提供那些乱七八糟的初始化方法了

        private int threshold = (int) (initBucketLength * loadFactor); //当容纳的数据超过此值时候扩容

        private Node[] buckets;//桶数组

        /**
         * bucket 中存放一个链表头结点，数据一次加入链表
         */
        class Node {
            private int key;
            private int val;
            private Node next;

            Node() {

            }

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

            Node(int key, int val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        /**
         * 获取key的hash值，均匀分散数据
         *
         * @param key
         * @return
         */
        private int hash(Object key) {
            if (key == null) {
                return 0;
            }
            //将高16为与低16位进行异或，均匀分散数据
            int hashCode = key.hashCode();
            return hashCode ^ (hashCode >>> 16);
        }

        /**
         * 获取数据在桶的位置
         *
         * @param key
         * @return
         */
        private int getBucketPos(Object key) {
            if (key == null) {
                return 0;
            }
            return hash(key) & (initBucketLength - 1);
        }

        /**
         * 检查大桶是否需要扩容
         */
        private void checkBucketNeedMoreCap() {
            if (size < threshold) {
                return;
            }
            initBucketLength = initBucketLength << 1;
            threshold = (int) (initBucketLength * loadFactor); //重新计算负载容量
            //此时需要扩容
            Node[] newBuckets = new Node[initBucketLength];
            for (int i = 0; i < buckets.length; i++) {
                Node node = buckets[i];
                Node p = node;
                while (p != null) {
                    int newPos = getBucketPos(p.key); //新数组中位置
                    Node newNode = newBuckets[newPos];
                    if (newNode == null) {
                        newNode = new Node(p.key, p.val);
                        newBuckets[newPos] = newNode;
                    } else {
                        Node newHead = new Node(p.key, p.val);
                        newHead.next = newNode;
                        newBuckets[newPos] = newHead;
                    }
                    p = p.next;
                }
            }
            buckets = newBuckets;
        }


        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            buckets = new Node[initBucketLength];
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            checkBucketNeedMoreCap();
            size++;
            int pos = getBucketPos(key);
            Node p = buckets[pos];
            if (p == null) {
                buckets[pos] = new Node(key, value);
                return;
            }
            while (p != null) {
                if (p.key == key) {
                    p.val = value;
                    return;
                }
                p = p.next;
            }
            //头部插入新结点
            Node head = buckets[pos];
            Node node = new Node(key, value);
            node.next = head;
            buckets[pos] = node;
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            int pos = getBucketPos(key);
            Node node = buckets[pos];
            if (node == null) {
                return -1;
            }
            while (node != null) {
                if (node.key == key) {
                    return node.val;
                }
                node = node.next;
            }
            return -1;
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            int pos = getBucketPos(key);
            Node node = buckets[pos];
            if (node == null) {
                return;
            }
            Node prev = null;
            while (node != null) {
                if (node.key != key) {
                    prev = node;
                    node = node.next;
                    continue;
                }
                //key相等
                if (prev == null) {
                    buckets[pos] = node.next;
                    break;
                }
                prev.next = node.next;
                break;
            }
            size--;
        }
    }

    @Test
    public void test() {

       /* MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(4, 4);
        hashMap.put(5, 5);
        hashMap.put(6, 6);
        hashMap.put(7, 7);
        hashMap.put(8, 8);
        hashMap.put(9, 9);
        hashMap.put(10, 10);
        hashMap.put(11, 11);
        hashMap.put(12, 12);
        hashMap.put(13, 13);
        hashMap.put(14, 14);
        hashMap.put(15, 15);
        hashMap.put(16, 16);
        hashMap.put(17, 17);
        hashMap.put(18, 18);
        hashMap.put(19, 19);
        hashMap.put(20, 20);
        hashMap.remove(3);
        hashMap.remove(5);
        hashMap.put(1, 111111);
        int n1 = hashMap.get(1);
        int n2 = hashMap.get(3);
        int n3 = hashMap.get(5);
        hashMap.put(20, 11);
        int n4 = hashMap.get(20);*/

       /* MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        int a1 = hashMap.get(1);// 返回 1
        int a2 = hashMap.get(3); // 返回 -1 (未找到)
        hashMap.put(2, 1);  // 更新已有的值
        int a3 = hashMap.get(2); // 返回 1
        hashMap.remove(2);  // 删除键为2的数据
        int a4 = hashMap.get(2); // 返回 -1 (未找到)*/


        System.out.println("----");
    }

}
