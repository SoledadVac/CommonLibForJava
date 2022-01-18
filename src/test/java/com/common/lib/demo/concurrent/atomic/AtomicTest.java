package com.common.lib.demo.concurrent.atomic;

import com.alibaba.fastjson.JSONObject;
import com.common.lib.demo.concurrent.Person;
import com.google.common.util.concurrent.*;
import lombok.SneakyThrows;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/29
 * \* Time: 3:25 PM
 * \* Description: atomic class test
 * \
 */
public class AtomicTest {

    private Person person = new Person("lhc", 12);
    private AtomicReference<Person> personReference = new AtomicReference<>(person);

    int iMax = 100;
    CyclicBarrier barrier = new CyclicBarrier(iMax);
    int shareValue = 0;
    Lock lock = new ReentrantLock();

    @Test
    public void test() throws Exception {
        for (int i = 0; i < iMax; i++) {
            SomeProcessValue t = new SomeProcessValue();
            t.start();
        }
        barrier.await();
        System.out.println(shareValue);
    }

    public class SomeProcessValue extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(10));
                lock.lock();
                shareValue++;
                lock.unlock();
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    int value = 0;

    @Test
    public void test4() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch counter = new CountDownLatch(10);
        Semaphore semaphore = new Semaphore(1); //信号量控制只有一个线程
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter.countDown();
                value++;
                System.out.println(value);
                semaphore.release();
            });
        }
        counter.await();
    }

    @Test
    public void test1() {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            try {
                System.out.println(exchanger.exchange("t1888888"));
                ;
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        executorService.execute(() -> {
            try {
                System.out.println("t2---" + exchanger.exchange("t2"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }


    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> f1 = executorService.submit(() -> "r1");
        Future<String> f2 = executorService.submit(() -> "r2");
        while (true) {
            if (f1.isDone() & f2.isDone()) {
                System.out.println(f1.get() + "|" + f2.get());
                break;
            }
        }
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<String> f1 = executorService.submit(() -> "r1");
        Futures.addCallback(f1, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("result:" + result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        ListenableFuture<String> f2 = executorService.submit(() -> "r2");
        Futures.addCallback(f2, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("result:" + result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Test
    public void test6() throws Exception {
        AtomicInteger value = new AtomicInteger(0);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println(value.getAndIncrement());
                    cyclicBarrier.await();
                }
            });
        }
        cyclicBarrier.await();
        System.out.println("value" + value);
    }

    @Test
    public void test7() throws Exception {
        int[] arr = {0, 1, 2};
        AtomicIntegerArray valueArr = new AtomicIntegerArray(arr);
        CyclicBarrier barrier = new CyclicBarrier(100);
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @SneakyThrows
                @Override
                public void run() {
                    valueArr.getAndAdd(0, 1);
                    barrier.await();
                }
            }.start();
        }
        barrier.await();
        System.out.println(JSONObject.toJSONString(valueArr.get(0)));
    }


    @Test
    public void test8() throws Exception {
        Person person = new Person("lhc", 0);
        AtomicReference<Person> personAtomicReference = new AtomicReference<>(person);
        CyclicBarrier barrier = new CyclicBarrier(100);
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println(personAtomicReference.compareAndSet(person, new Person("lhc", 11)));
                    barrier.await();
                }
            }.start();
        }
        barrier.await();
        System.out.println(JSONObject.toJSONString(personAtomicReference.get().getAge()));
    }

    @Test
    public void test9() throws Exception {
        Person person = new Person("lhc", 0);
        AtomicIntegerFieldUpdater<Person> updater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        CyclicBarrier barrier = new CyclicBarrier(100);
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @SneakyThrows
                @Override
                public void run() {
                    updater.addAndGet(person, 1);
                    barrier.await();
                }
            }.start();
        }
        barrier.await();
        System.out.println(JSONObject.toJSONString(updater.get(person)));

    }

}
