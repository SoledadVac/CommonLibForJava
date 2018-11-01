package com.common.lib.demo.concurrent.thread;

import org.junit.Test;

import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/9/29
 * \* Time: 上午11:50
 * \* Description:
 * \
 */
public class FutureTest {
    @Test
    public void futureTest() throws Exception {
        long start = System.currentTimeMillis();
        Callable<String> c1 = () -> {
            Thread.sleep(1000);
            return "c1";
        };
        Callable<String> c2 = () -> {
            Thread.sleep(2000);
            return "c2";
        };
        FutureTask<String> f1 = new FutureTask<>(c1);
        FutureTask<String> f2 = new FutureTask<>(c2);
        new Thread(f1).start();
        new Thread(f2).start();
        System.out.println(f2.get());
        System.out.println(f1.get());
        long end = System.currentTimeMillis();
        System.out.println("间隔：" + (end - start));
    }

}
