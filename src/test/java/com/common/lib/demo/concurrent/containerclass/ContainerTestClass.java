package com.common.lib.demo.concurrent.containerclass;

import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/16
 * \* Time: 3:23 PM
 * \* Description: concurrent container test
 * \
 */
public class ContainerTestClass {

    @Test
    public void concurrentHashMapTest() throws Exception {
        // HashMap<String, String> map = new HashMap<>();  //有错误
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        int threadNum = 5000;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString(), "");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }

    class CountTask extends RecursiveTask<Integer> {
        final int threadHold = 2;
        int start;
        int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            boolean canCompute = (end - start) <= threadHold;
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
            //分割任务
            int mid = (start + end) / 2;
            CountTask left = new CountTask(start, mid);
            CountTask right = new CountTask(mid + 1, end);
            left.fork();
            right.fork();
            int leftSum = left.join();
            int rightSum = right.join();
            return leftSum + rightSum;
        }
    }

    @Test
    public void forkJoinTest() throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 9);
        Future<Integer> future = forkJoinPool.submit(countTask);
        System.out.println("result = " + future.get());
    }

}
