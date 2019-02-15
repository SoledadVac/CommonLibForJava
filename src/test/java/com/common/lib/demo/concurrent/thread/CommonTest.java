package com.common.lib.demo.concurrent.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/11/2
 * \* Time: 4:20 PM
 * \* Description:
 * \
 */
public class CommonTest {


    @Test
    public void test() throws Exception {

        AtomicInt value = new AtomicInt(0);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            int temp = i;
            new Thread(() -> {
                try {
                    int num = (int) (Math.random() * 100 + 1);
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("$$$$temp=" + temp);
                System.out.println("----before value=" + value.get());
                value.set(temp);
                System.out.println("++++after value=" + value.get());
                System.out.println("");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();


    }

    class AtomicInt {

        private volatile int value;

        AtomicInt(int value) {
            this.value = value;
        }

        public int get() {
            return this.value;
        }

        public void set(int value) {
            this.value = value;
        }


    }
}
