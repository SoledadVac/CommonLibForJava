package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import com.kenai.jaffl.annotations.In;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/7/3
 * \* Time: 28:35 下午
 * \* Description: LRU缓存机制
 * \
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A146_LRUCache {

    class Node {
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 此方法时间复杂度过高，不符合题目要求
     */
    @Deprecated
    class LRUCache0 {
        int size;
        LinkedList<Node> data = new LinkedList<>();

        public LRUCache0(int capacity) {
            size = capacity;
        }

        public int get(int key) {
            if (data.size() == 0) {
                return -1;
            }
            Node findNode = null;
            int findIndex = -1;
            for (int index = 0; index < data.size(); index++) {
                Node node = data.get(index);
                if (node.key == key) {
                    findIndex = index;
                    findNode = node;
                    break;
                }
            }
            if (findNode == null) {
                return -1;
            }
            data.remove(findIndex); //移除当前位置
            data.add(0, findNode);//加入链表首位
            return findNode.value;
        }

        public void put(int key, int value) {
            Node findNode = null;
            int findIndex = -1;
            for (int index = 0; index < data.size(); index++) {
                Node node = data.get(index);
                if (node.key == key) {
                    findIndex = index;
                    findNode = node;
                    break;
                }
            }
            if (findNode != null) {
                //之前存在此key
                data.remove(findIndex);
                findNode.value = value;
                data.add(0, findNode);
                return;
            }
            //之前不存在
            //先判断大小
            findNode = new Node(key, value);
            if (data.size() < size) {
                data.add(0, findNode);
                return;
            }
            //如果超长了，则替换掉链表尾部的值
            data.remove(data.size() - 1);
            data.add(0, findNode);
            return;
        }
    }


    /**
     * 内部使用LinkedHashMap 保存数据，时间复杂度为1
     */
    class LRUCache1 {
        int size;
        LinkedHashMap<Integer, Integer> data = new LinkedHashMap<>();

        public LRUCache1(int capacity) {
            this.size = capacity;
        }

        public int get(int key) {
            if (!data.containsKey(key)) {
                return -1;
            }
            int val = data.get(key);
            data.remove(key);
            data.put(key, val); //最新的数据放在最后
            return val;

        }

        public void put(int key, int value) {
            if (data.containsKey(key)) {
                data.remove(key);
                data.put(key, value); //最新的数据放在最后
                return;
            }
            //不存在此key
            if (data.keySet().size() < size) {
                //未超过容量
                data.put(key, value); //最新的数据放在最后
                return;
            }
            //超过容量，移除第一个
            Integer k = data.keySet().iterator().next();
            data.remove(k);
            data.put(key, value); //最新的数据放在最后
        }
    }

    /**
     * 也可以简单继承LinkedHashMap ，通过removeEldestEntry方法实现容量满了的时候，移除最久未使用的
     */
    class LRUCache2 extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache2(int capacity) {
            super(capacity, 0.75F, true); //accessOrder : 保证超过容量时候进行LRU
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }


    /**
     * 下面通过使用hashmap和双向链表来实现
     */
    class LRUCache {

        /**
         * 双向链表单结点
         */
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        int capacity; //最大容量
        int size;   //当前容量
        Map<Integer, DLinkedNode> keyNodeMap = new HashMap<>();
        DLinkedNode head; //头部放最新的
        DLinkedNode tail; //尾部是老数据

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            DLinkedNode cache = keyNodeMap.get(key);
            if (cache == null) {
                return -1;
            }
            //存在则移动到头部
            moveToFirst(cache);
            return keyNodeMap.get(key).value;
        }

        public void put(int key, int value) {
            if (head == null) {
                //当前为空时候
                DLinkedNode node = new DLinkedNode(key, value);
                head = node;
                tail = node;
                size++;
                keyNodeMap.put(key, node);
                return;
            }
            DLinkedNode cache = keyNodeMap.get(key);
            if (cache != null) {
                //如果之前有的话，替换下值
                cache.value = value;
                moveToFirst(cache); //移到首部
                return;
            }
            //之前没有的话，需要重新插入了
            //检验容量
            if (size >= capacity) {
                //超过容量了，删一个
                deleteTail();
            }
            cache = new DLinkedNode(key, value);
            addToFirst(cache);
            size++;
            keyNodeMap.put(key, cache);
        }


        /**
         * 将结点移动到头部
         *
         * @param curr
         */
        void moveToFirst(DLinkedNode curr) {
            if (curr == head) {
                //本来就是头部结点无需任何移动
                return;
            }
            //将当前结点从之前的位置中断裂出来
            DLinkedNode prev = curr.prev;
            DLinkedNode next = curr.next;
            prev.next = next;
            if (next != null) {
                next.prev = prev;
            } else {
                tail = prev;
            }
            head.prev = curr;
            curr.next = head;
            curr.prev = null;
            head = curr;
        }

        /**
         * 删除尾部结点
         */
        void deleteTail() {
            if (head == tail) {
                keyNodeMap.remove(tail.key);
                head = null;
                tail = null;
                return;
            }
            DLinkedNode curTail = tail;
            keyNodeMap.remove(tail.key); //先从map里面删除此尾部结点
            DLinkedNode prev = curTail.prev; // 尾部结点的前一个结点
            prev.next = null; //将前一个结点的next置空
            curTail.prev = null; //将目前tail的前置结点置空，断开联系
            tail = prev; //移动尾部的指针到倒数第二个结点
        }

        /**
         * 添加新结点到首部
         *
         * @param curr
         */
        void addToFirst(DLinkedNode curr) {
            if (head == tail && head == null) {
                head = curr;
                tail = curr;
                return;
            }
            curr.next = head; //当前结点的下一个结点置为当前的头部
            head.prev = curr; //当前头部的前置结点置为curr
            head = curr; //将头部设置为当前结点
        }
    }

    @Test
    public void test() {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        int v1 = cache.get(1);       // 返回  1
        System.out.println("v1 = " + v1);
        //System.out.println(JSONObject.toJSONString(cache.data));
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        int v2 = cache.get(2);       // 返回 -1 (未找到)
        System.out.println("v2 = " + v2);
        // System.out.println(JSONObject.toJSONString(cache.data));
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        int v3 = cache.get(1);       // 返回 -1 (未找到)
        System.out.println("v3 = " + v3);
        //System.out.println(JSONObject.toJSONString(cache.data));
        int v4 = cache.get(3);       // 返回  3
        System.out.println("v4 = " + v4);
        // System.out.println(JSONObject.toJSONString(cache.data));
        int v5 = cache.get(4);       // 返回  4
        System.out.println("v5 = " + v5);
        // System.out.println(JSONObject.toJSONString(cache.data));

    }

    @Test
    public void test1() {
        //["LRUCache","put","get","put","get","get"]
        //[[1],[2,1],[2],[3,2],[2],[3]]
        LRUCache cache = new LRUCache(1);
        cache.put(2, 1);
        int v1 = cache.get(2);
        System.out.println("v1 = " + v1);
        cache.put(3, 2);
        int v2 = cache.get(2);
        System.out.println("v2 = " + v2);
        int v3 = cache.get(3);
        System.out.println(v3);

    }
}
