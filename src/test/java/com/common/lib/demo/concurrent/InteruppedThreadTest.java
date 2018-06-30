package com.common.lib.demo.concurrent;

import com.common.lib.demo.redis.utils.RedisUtil;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/30
 * \* Time: 下午4:40
 * \* Description: 线程中断测试
 * \
 */
public class InteruppedThreadTest {
    @Test
    public void interupt() throws InterruptedException {
        Thread sleepThread=new Thread(new SleepRunner(),"SleepRunner");
        sleepThread.setDaemon(true);
        Thread busyThread=new Thread(new BusyRunner(),"BusyRunner");
        sleepThread.start();
        busyThread.start();
        Thread.sleep(2000);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepRunner:"+sleepThread.isInterrupted());
        System.out.println("BusyRunner:"+busyThread.isInterrupted());
        Thread.sleep(6000);
    }

    class BusyRunner implements Runnable{

        @Override
        public void run() {
            while (true){

            }
        }
    }

    class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
