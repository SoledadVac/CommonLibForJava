package leetcode.HashTable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/14
 * \* Time: 30:18 PM
 * \* Description: 设计哈希集合
 * \
 * 不使用任何内建的哈希表库设计一个哈希集合
 * <p>
 * 具体地说，你的设计应该包含以下的功能
 * <p>
 * add(value)：向哈希集合中插入一个值。
 * contains(value) ：返回哈希集合中是否存在这个值。
 * remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * <p>
 * 示例:
 * <p>
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);        
 * hashSet.add(2);        
 * hashSet.contains(1);    // 返回 true
 * hashSet.contains(3);    // 返回 false (未找到)
 * hashSet.add(2);          
 * hashSet.contains(2);    // 返回 true
 * hashSet.remove(2);          
 * hashSet.contains(2);    // 返回  false (已经被删除)
 * <p>
 * 注意：
 * <p>
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希集合库。
 * <p>
 */
public class A705_DesignHashSet {
    class MyHashSet {

        private final Integer bucketNum = 10; //0 - 9
        private transient Map<Integer, List<Integer>> buckets = new HashMap<>();

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {

        }

        public void add(int key) {
            int bNum = key % bucketNum;
            List<Integer> bucket = buckets.get(bNum);
            if (bucket == null) {
                bucket = new ArrayList<>();
                buckets.put(bNum, bucket);
            }
            bucket.add(key);
        }

        public void remove(int key) {
            int bNum = key % bucketNum;
            List<Integer> bucket = buckets.get(bNum);
            if (bucket == null) {
                return;
            }
            if (bucket.contains(key)) {
                bucket.removeIf(v -> v == key);
            }
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            int bNum = key % bucketNum;
            List<Integer> bucket = buckets.get(bNum);
            if (bucket == null) {
                return false;
            }
            return bucket.contains(key);
        }
    }

    @Test
    public void test() {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        boolean r1 = hashSet.contains(1);// 返回 true
        boolean r2 = hashSet.contains(3);// 返回 false (未找到)
        hashSet.add(2);
        boolean r3 = hashSet.contains(2);// 返回 true
        hashSet.remove(2);
        boolean r4 = hashSet.contains(2);// 返回  false (已经被删除)
        System.out.println("");
    }
}
