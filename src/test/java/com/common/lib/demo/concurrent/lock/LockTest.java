package com.common.lib.demo.concurrent.lock;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/31
 * \* Time: 4:05 PM
 * \* Description:
 * \
 */
public class LockTest {

    /**
     * 正确加锁解锁的方式
     */
    @Test
    public void formateUseLockTest() {
        Lock lock = new ReentrantLock();
        lock.lock();//注意：不要将获取锁也放置在try中
        try {
            /**此处放置加锁后的代码**/
        } finally {
            lock.unlock();
        }
    }

    /**
     * 公平锁非公平锁
     */
    @Test
    public void FairLockAndNotFairLockTest() throws Exception {
        ReentrantLock2 fairLock = new ReentrantLock2(true);
        ReentrantLock2 unFairLock = new ReentrantLock2(false);

        testLock("公平锁",fairLock);

        testLock("非公平锁", unFairLock);

    }

    class Job extends Thread {
        private Lock lock;

        Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println("current thread name : "
                            + Thread.currentThread().getName()
                            + "  ; 同步队列中的线程 ："
                            + ((ReentrantLock2) lock).getQueuedThreads());
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private void testLock(String type, Lock lock) throws InterruptedException {
        System.out.println(type);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Job(lock)) {
                public String toString() {
                    return getName();
                }
            };
            thread.setName("" + i);
            thread.start();
        }
        Thread.sleep(11000);
    }


    public class ReentrantLock2 extends ReentrantLock {
        ReentrantLock2(boolean fair) {
            super(fair);
        }
        /**
         * 获取等待锁线程列表
         *
         * @return
         */
        public Collection<Thread> getQueuedThreads() {
            List<Thread> threads = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(threads);
            return threads;
        }

    }


}
