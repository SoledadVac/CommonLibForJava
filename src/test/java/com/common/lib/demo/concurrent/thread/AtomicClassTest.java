package com.common.lib.demo.concurrent.thread;

import com.alibaba.fastjson.JSONObject;
import com.common.lib.demo.concurrent.Person;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/16
 * \* Time: 2:44 PM
 * \* Description:atomic class test
 * \
 */
public class AtomicClassTest {


    @Test
    public void atomicIntegerTest() throws Exception {
        AtomicInteger intA = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                intA.incrementAndGet();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("result = " + intA.get());
    }

    @Test
    public void atomicIntegerArrayTest() throws Exception {
        int[] arry = {1, 1, 1, 1, 1, 1, 1, 1};
        AtomicIntegerArray atoArry = new AtomicIntegerArray(arry);
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            int index = i % arry.length;
            new Thread(() -> {
                atoArry.addAndGet(index, 1);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("result = " + JSONObject.toJSONString(atoArry.get(0)));
    }

    @Test
    public void atomicReferenceTest() throws Exception {
        Person p = new Person("jay", 10);
        AtomicReference<Person> reference = new AtomicReference<>();
        reference.set(p);
        Person updateUser = new Person("jjj", 21);
        reference.compareAndSet(p, updateUser);
        System.out.println(reference.get().getName());
        System.out.println(reference.get().getAge());
    }

    @Test
    public void atomicFieldUpdateTest() throws Exception {
        AtomicIntegerFieldUpdater<Person> updater=AtomicIntegerFieldUpdater.newUpdater(Person.class,"age");
        Person p = new Person("jay", 10);
        updater.decrementAndGet(p);
    }



}
