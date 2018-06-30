package com.common.lib.demo.concurrent;


import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/30
 * \* Time: 下午2:50
 * \* Description:线程状态检测
 * \
 */
public class ThreadStateTest {

    public static void main(String[] args) {
        new Thread(new TimeWaitingClass(),"TimeWaitingClass").start();
        new Thread(new WaitingClass(),"WaitingClass").start();
        new Thread(new BlockedClass(),"BlockedClass-1").start();
        new Thread(new BlockedClass(),"BlockedClass-2").start();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
    }


   static class TimeWaitingClass implements Runnable{

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

    static class BlockedClass implements Runnable{
        @Override
        public void run() {
            synchronized (BlockedClass.class){
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

    static class WaitingClass implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (WaitingClass.class){
                    try {
                        WaitingClass.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
