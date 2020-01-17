package com.common.lib.demo.concurrent.thread;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/11/26
 * \* Time: 2:29 PM
 * \* Description:
 * \
 */
public class ThreadTest {


    /**
     * 一个GUI控件,缩略版本
     */
    @Data
    class ModelAndView {
        /**
         * 长度
         */
        private Integer length;
        /**
         * 宽度
         */
        private Integer width;
        /**
         * X位置
         */
        private Integer xPos;
        /**
         * Y位置
         */
        private Integer yPos;
        /**
         * 控件上绑定数据
         */
        private Map<String, Object> data;
    }


    /**
     * 初始化GUI线程
     */
    class GuiInitThread implements Runnable {
        ModelAndView modelAndView;

        public GuiInitThread(ModelAndView modelAndView) {
            this.modelAndView = modelAndView;
        }


        @Override
        public void run() {
            modelAndView.setLength(1);
            modelAndView.setWidth(1);
            modelAndView.setXPos(0);
            modelAndView.setYPos(0);
        }
    }

    /**
     * 对象移动
     */
    class MoveThread extends Thread {

        ModelAndView modelAndView;
        public Boolean isStop; //停止标记位置

        public MoveThread(ModelAndView modelAndView) {
            this.modelAndView = modelAndView;
            this.isStop = false;
        }

        @Override
        public void run() {
            while (true) {
                //注意：此处为正确停止线程方式
                if (!this.isInterrupted() && !isStop) {
                    modelAndView.setXPos(modelAndView.getXPos() + 1);
                    modelAndView.setYPos(modelAndView.getYPos() + 1);
                }
            }
        }
    }

    /**
     * 数据初始化线程
     */
    class DataThread implements Runnable {

        ModelAndView modelAndView;

        public DataThread(ModelAndView modelAndView) {
            this.modelAndView = modelAndView;
        }


        @Override
        public void run() {
            Map<String, Object> data = new HashMap<>();
            try {
                Thread.sleep(1000); //徒增耗时
            } catch (InterruptedException e) {
                //先这样
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                data.put(String.valueOf(i), i);
            }
            modelAndView.setData(data);
        }
    }


    String[] dialogs = {"hi", "hello", "how are u", "i'm fine,thank u", "and u?", "i'm ok!"};
    Boolean isChineseSpeak = true;
    final Object monitor = new Object();
    Integer index = 0;

    class ChinesePersonThread implements Runnable {

        @Override
        public void run() {
            while (index < 5) {
                synchronized (monitor) {
                    while (!isChineseSpeak) {
                        try {
                            //当条件不满足时候，在这里等待条件对方完成的通知
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    isChineseSpeak = false;
                    System.out.println("thread id : " + Thread.currentThread().getId() + ", say : " + dialogs[index]);
                    index++;
                }
            }
        }
    }


    class ForeignPersonThread implements Runnable {
        @Override
        public void run() {
            while (index < 5) {
                synchronized (monitor) {
                    if (!isChineseSpeak) {
                        System.out.println("thread id : " + Thread.currentThread().getId() + ", say : " + dialogs[index]);
                        index++;
                        isChineseSpeak = true;
                        //执行完成之后通知等待线程
                        monitor.notifyAll();

                    }
                }
            }
        }
    }

    @Test
    public void test() throws Exception {
        Thread chineseThread = new Thread(new ChinesePersonThread());
        Thread foreignThread = new Thread(new ForeignPersonThread());
        chineseThread.start();
        foreignThread.start();
        Thread.sleep(1000);
    }


    final Object lock = new Object();
    boolean waiting = true;

    class WaitThread extends Thread {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("current time : " + System.currentTimeMillis() + " ; wait thread hold lock : " + Thread.holdsLock(lock));
                while (waiting) {
                    try {
                        System.out.println("begin wait ...." + "current time : " + System.currentTimeMillis());
                        lock.wait();
                        System.out.println("current time : " + System.currentTimeMillis() + " ; wait thread hold lock : " + Thread.holdsLock(lock));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class NotifyThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                System.out.println("current time : " + System.currentTimeMillis() + " ;  notify thread hold lock : " + Thread.holdsLock(lock));
                if (waiting) {
                    waiting = false;
                    lock.notify();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("current time : " + System.currentTimeMillis() + " ;  notify thread hold lock : " + Thread.holdsLock(lock));
            }

        }
    }

    @Test
    public void testWaitNotifyLock() throws Exception {
        new WaitThread().start();
        new Thread(new NotifyThread()).start();
        Thread.sleep(3000);
    }


    final Object sleepLock = new Object();

    @Test
    public void testSleepLock() throws Exception {
        Runnable r1 = () -> {
            synchronized (sleepLock) {
                System.out.println("r1 begin current time : " + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("r1 end  current time : " + System.currentTimeMillis());
            }
        };
        Runnable r2 = () -> {
            //让r1先获取到锁
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (sleepLock) {
                System.out.println("r2 current time : " + System.currentTimeMillis());
            }
        };
        new Thread(r1).start();
        new Thread(r2).start();
        Thread.sleep(3000);
    }


    @Test
    public void testYieldLock() throws Exception {
        Runnable r1 = () -> {
            synchronized (sleepLock) {
                System.out.println("r1 begin current time : " + System.currentTimeMillis());
                Thread.yield();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("r1 end  current time : " + System.currentTimeMillis());
            }
        };
        Runnable r2 = () -> {
            //让r1先获取到锁
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (sleepLock) {
                System.out.println("r2 current time : " + System.currentTimeMillis());
            }
        };
        new Thread(r1).start();
        new Thread(r2).start();
        Thread.sleep(2000);
    }
}
