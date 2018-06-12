package com.common.lib.demo.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/5
 * \* Time: 上午10:35
 * \* Description: redis 锁抽象类
 * \
 */
public abstract class AbstractLock implements Lock{

    protected volatile boolean locked;

    private Thread exclusiveOwnerThread;

    @Override
    public void lock() {
        try {
            lock(false, 0, null, false);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        lock(false, 0, null, true);
    }

    public boolean tryLockInterruptibly(long time, TimeUnit unit) throws InterruptedException {
        return lock(true, time, unit, true);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit)  {
        try {
            System.out.println("try--lock");
            return lock(true, time, unit, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("" + e);
        }
        return false;
    }

    @Override
    public void unlock() {
        // 检查当前线程是否持有锁
        if (Thread.currentThread() != getExclusiveOwnerThread()) {
            throw new IllegalMonitorStateException("current thread does not hold the lock");
        }
        unlock0();
        setExclusiveOwnerThread(null);
    }

    protected void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    protected abstract void unlock0();

    /**
     * 阻塞式获取锁的实现
     * @param useTimeout
     * @param time
     * @param unit
     * @param interrupt 是否响应中断
     * @return
     * @throws InterruptedException
     */
    protected abstract boolean lock(boolean useTimeout, long time, TimeUnit unit, boolean interrupt)
            throws InterruptedException;
}
