package leetcode.QueueAndStack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/30
 * \* Time: 30:33 PM
 * \* Description: 设计循环队列
 * \
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * <p>
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，
 * 我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * 你的实现应该支持如下操作：
 * <p>
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 */
public class A622_DesignCircularQueue {


    class MyCircularQueue {

        private Integer[] data;
        private Integer size;
        private Integer head = -1;
        private Integer tail = -1;

        /**
         * Initialize your data structure here. Set the size of the queue to be k.
         */
        public MyCircularQueue(int k) {
            this.data = new Integer[k];
            this.size = k;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is successful.
         */
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            if (head == -1) {
                head++;
            }
            tail++;
            data[tail % size] = value;
            return true;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is successful.
         */
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }

            if (head == tail % size) {
                head = -1;
                tail = -1;
            } else {
                head++;
            }
            return true;
        }

        /**
         * Get the front item from the queue.
         */
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return data[head];
        }

        /**
         * Get the last item from the queue.
         */
        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return data[tail % size];
        }

        /**
         * Checks whether the circular queue is empty or not.
         */
        public boolean isEmpty() {
            return tail == -1 && head == -1;
        }

        /**
         * Checks whether the circular queue is full or not.
         */
        public boolean isFull() {
            return (tail % size - head) == size - 1 || (head - tail % size) == 1;
        }
    }

    @Test
    public void test() {
     /*   MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

        boolean r1 = circularQueue.enQueue(1);  // 返回 true

        boolean r2 = circularQueue.enQueue(2);  // 返回 true

        boolean r3 = circularQueue.enQueue(3);  // 返回 true

        boolean r4 = circularQueue.enQueue(4);  // 返回 false，队列已满

        int rt1 = circularQueue.Rear();  // 返回 3

        boolean r5 = circularQueue.isFull();  // 返回 true

        boolean r6 = circularQueue.deQueue();  // 返回 true

        boolean r7 = circularQueue.enQueue(4);  // 返回 true

        int rt2 = circularQueue.Rear();  // 返回 4

        System.out.println(circularQueue);*/


        //["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"]
        // [[6],[6],[],[],[],[5],[],[],[],[],[],[]]


        MyCircularQueue circularQueue = new MyCircularQueue(6);
        boolean e1 = circularQueue.enQueue(6);
        int r1 = circularQueue.Rear();
        int r2 = circularQueue.Rear();
        boolean d1 = circularQueue.deQueue();
        boolean e2 = circularQueue.enQueue(5);
        int r3 = circularQueue.Rear();
        boolean d2 = circularQueue.deQueue();
        int f1 = circularQueue.Front();
        boolean d3 = circularQueue.deQueue();
        boolean d4 = circularQueue.deQueue();
        boolean d5 = circularQueue.deQueue();
        System.out.println(circularQueue);
    }

}
