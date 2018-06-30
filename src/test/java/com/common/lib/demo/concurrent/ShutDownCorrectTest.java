package com.common.lib.demo.concurrent;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/30
 * \* Time: 下午5:25
 * \* Description:中断线程
 * \
 */
public class ShutDownCorrectTest {
    @Test
    public void test() throws InterruptedException {
        Runner runner=new Runner();
        Thread countThread=new Thread(runner);
        countThread.start();
        Thread.sleep(1000);
        //runner.cancel();
        countThread.interrupt();
    }


    class Runner implements   Runnable{
        private long i;
        private volatile boolean on=true;

        @Override
        public void run() {
            while(on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("i="+i);
        }
        public void cancel(){
            on=false;
        }
    }
}
