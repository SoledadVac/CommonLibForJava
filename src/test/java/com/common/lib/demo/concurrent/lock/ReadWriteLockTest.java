package com.common.lib.demo.concurrent.lock;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/31
 * \* Time: 6:16 PM
 * \* Description: ReentrantReadWriteLock 读写锁测试
 * \
 */
public class ReadWriteLockTest {


    @Test
    public void test() {


    }

    /**
     * 线程安全的local cache
     */
    class LocalCache {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private Lock writeLock = lock.writeLock();
        private Lock readLock = lock.readLock();

        private Map<String, String> data = new HashMap<>();

        public String get(String key) {
            readLock.lock();
            try {
                if (data.containsKey(key)) {
                    return data.get(key);
                }
                return "";
            } finally {
                readLock.unlock();
            }
        }

        public void set(String key, String value) {
            writeLock.lock();
            try {
                data.put(key, value);
            } finally {
                writeLock.unlock();
            }
        }

        public void clear() {
            writeLock.lock();
            try {
                data.clear();
            } finally {
                writeLock.unlock();
            }
        }
    }


}
