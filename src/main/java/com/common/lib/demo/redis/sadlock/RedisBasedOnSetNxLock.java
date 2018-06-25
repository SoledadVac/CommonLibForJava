package com.common.lib.demo.redis.sadlock;

import com.common.lib.demo.redis.AbstractLock;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/5
 * \* Time: 下午4:09
 * \* Description:基于Redis的SETNX操作实现的分布式锁基于Redis的SETNX操作实现的分布式锁
 *          获取锁时最好用lock(long time, TimeUnit unit), 以免网路问题而导致线程一直阻塞
 *
 *       SETNX命令（SET if Not eXists）
             语法：
             SETNX key value
             功能：
             当且仅当 key 不存在，将 key 的值设为 value ，并返回1；若给定的 key 已经存在，则 SETNX 不做任何动作，并返回0。

         GETSET命令（这是一个原子命令！）
             语法：
             GETSET key value
             功能：
             将给定 key 的值设为 value ，并返回 key 的旧值 (old value)，当 key 存在但不是字符串类型时，返回一个错误，当key不存在时，返回nil。
 * \
 */
public class RedisBasedOnSetNxLock extends AbstractLock{


    private Jedis jedis;

    // 锁的名字
    protected String lockKey;

    // 锁的有效时长(毫秒)
    protected long lockExpires;

    public RedisBasedOnSetNxLock(Jedis jedis, String lockKey, long lockExpires) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.lockExpires = lockExpires;
    }

    @Override
    protected void unlock0() {

    }

    @Override
    protected boolean lock(boolean useTimeout, long time, TimeUnit unit, boolean interrupt) throws InterruptedException {
        if(interrupt){
            checkInterruption();
        }
        long start = System.currentTimeMillis();
        long timeout = unit.toMillis(time);
        while (useTimeout ? isTimeout(start, timeout) : true) {
            if (interrupt) {
                checkInterruption();
            }
            long lockExpireTime = System.currentTimeMillis() + lockExpires + 1;// 锁超时时间
            String stringOfLockExpireTime = String.valueOf(lockExpireTime);
            if (jedis.setnx(lockKey, stringOfLockExpireTime) == 1) { // 获取到锁
                //成功获取到锁, 设置相关标识
                locked = true;
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            String value = jedis.get(lockKey);
            if (value != null && isTimeExpired(value)) { // lock is expired
                // 假设多个线程(非单jvm)同时走到这里
                String oldValue = jedis.getSet(lockKey, stringOfLockExpireTime); //原子操作
                /**
                 * 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
                 * 假如拿到的oldValue依然是expired的，那么就说明拿到锁了
                 */
                if (oldValue != null && isTimeExpired(oldValue)) {
                    //成功获取到锁, 设置相关标识
                    locked = true;
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else {
                // TODO lock is not expired, enter next loop retrying
            }
        }
        return false;
    }

    public boolean tryLock() {
        long lockExpireTime = System.currentTimeMillis() + lockExpires + 1;// 锁超时时间
        String stringOfLockExpireTime = String.valueOf(lockExpireTime);

        if (jedis.setnx(lockKey, stringOfLockExpireTime) == 1) { // 获取到锁
            // 成功获取到锁, 设置相关标识
            locked = true;
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }

        String value = jedis.get(lockKey);
        if (value != null && isTimeExpired(value)) { // lock is expired
            // 假设多个线程(非单jvm)同时走到这里
            String oldValue = jedis.getSet(lockKey, stringOfLockExpireTime); //原子操作
            // 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
            // 假如拿到的oldValue依然是expired的，那么就说明拿到锁了
            if (oldValue != null && isTimeExpired(oldValue)) {
                //成功获取到锁, 设置相关标识
                locked = true;
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
        } else {
            // TODO lock is not expired, enter next loop retrying
        }
        return false;
    }



    private void checkInterruption() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
    }

    private boolean isTimeExpired(String value) {
        return Long.parseLong(value) < System.currentTimeMillis();
    }

    /**
     * judge whether timeout or not
     * @param start
     * @param timeout
     * @return if timeout return true; else false
     */
    private boolean isTimeout(long start, long timeout) {
        return start + timeout > System.currentTimeMillis();
    }

    private void doUnlock() {
        jedis.del(lockKey);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
