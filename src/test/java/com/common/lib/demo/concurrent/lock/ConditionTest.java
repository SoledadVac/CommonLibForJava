package com.common.lib.demo.concurrent.lock;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/11/1
 * \* Time: 11:05 AM
 * \* Description: Condition 等待通知
 * \
 */
public class ConditionTest {
    final Random random = new Random(System.currentTimeMillis());

    @Test
    public void test() {

        BoundedQueue boundedQueue = new BoundedQueue(5);

        for (int i = 0; i < 20; i++) {
            new Thread(new Producer(boundedQueue)).start();
        }

        for (int i = 0; i < 20; i++) {
            new Thread(new Comsumer(boundedQueue)).start();
        }

    }

    /**
     * 有界队列
     */
    public class BoundedQueue {
        private Integer items[];
        private Lock lock = new ReentrantLock();
        private Condition notEmpty = lock.newCondition();
        private Condition notFull = lock.newCondition();
        private int count;
        private int addIndex, removeIndex;

        public BoundedQueue(int size) {
            items = new Integer[size];
        }

        public void add(Integer item) throws InterruptedException {
            lock.lock();
            try {
                while (count == items.length) {
                    notFull.await();
                }
                items[addIndex] = item;
                if (++addIndex == items.length) {
                    addIndex = 0;
                }
                count++;
                System.out.println(Thread.currentThread().getName() + " -- add : " + item + "-- " + Arrays.toString(items));
                notEmpty.signal();
            } finally {
                lock.unlock();
            }

        }

        public Integer remove() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0) {
                    notEmpty.await();
                }
                Integer temp = items[removeIndex];
                items[removeIndex] = null;
                System.out.println(Thread.currentThread().getName() + "---remove : " + temp + "-- " + Arrays.toString(items));
                if (++removeIndex == items.length) {
                    removeIndex = 0;
                }
                count--;
                notFull.signal();
                return temp;
            } finally {
                lock.unlock();
            }
        }


    }

    public class Producer implements Runnable {
        private BoundedQueue boundedQueue;

        Producer(BoundedQueue boundedQueue) {
            this.boundedQueue = boundedQueue;
        }

        private void produce() throws InterruptedException {
            boundedQueue.add(random.nextInt());
        }

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Comsumer implements Runnable {
        private BoundedQueue boundedQueue;

        Comsumer(BoundedQueue boundedQueue) {
            this.boundedQueue = boundedQueue;
        }

        private Integer remove() throws InterruptedException {
            return boundedQueue.remove();
        }

        @Override
        public void run() {
            try {
                remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
