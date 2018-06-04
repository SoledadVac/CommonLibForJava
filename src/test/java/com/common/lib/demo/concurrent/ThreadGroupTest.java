package com.common.lib.demo.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/4
 * \* Time: 下午4:01
 * \* Description:
 * \
 */
public class ThreadGroupTest {

    /**
     * 复制线程组
     * @throws Exception
     */
    @Test
    public void simpleUseTest() throws Exception{
        ThreadGroup group1=new ThreadGroup("group1");
        Runnable r1=(()->{
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t1=new Thread(group1,r1,"t1");
        t1.start();
        //复制线程组
        Thread[] threads=new Thread[group1.activeCount()];
        group1.enumerate(threads);
        System.out.println(threads.length);
    }

    /**
     * 线程组统一异常处理
     */
    @Test
    public void threadGroupExceptionSet() throws Exception{
        CountDownLatch seaphere=new CountDownLatch(1);
        ThreadGroup group1=new ThreadGroup("group1"){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程名称："+t.getName());
                seaphere.countDown();
            }
        };
        Runnable r1=()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a=1/0;
            seaphere.countDown();
        };
        Thread t1=new Thread(group1,r1,"t1");
        t1.start();
        seaphere.await();
    }

}
