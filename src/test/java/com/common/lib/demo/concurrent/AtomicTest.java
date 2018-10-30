package com.common.lib.demo.concurrent;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    private AtomicInteger atomicInteger = new AtomicInteger(100);


    @Test
    public void WithOutAtomicReferenceTest() throws Exception {
        Thread t1 = new Thread(() -> {
            person.setName("aaa");
            for (int i = 0; i < 1000; i++) {
                person.setAge(person.getAge() + 1);
            }
        });
        Thread t2 = new Thread(() -> {
            person.setName("bbb");

            for (int i = 0; i < 1000; i++) {
                person.setAge(person.getAge() + 2);
            }

        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(JSONObject.toJSONString(person));
    }

    @Test
    public void AtomicReferenceTest() throws Exception {
        Thread t1 = new Thread(() -> {
            personReference.getAndSet(new Person("bbb", personReference.get().getAge() + 2));
        });
        Thread t2 = new Thread(() -> {
            personReference.getAndSet(new Person("bbb ", personReference.get().getAge() + 2));
        });
        Thread t3 = new Thread(() -> {
            personReference.getAndSet(new Person("bbb ", personReference.get().getAge() + 2));
        });
        Thread t4 = new Thread(() -> {
            personReference.getAndSet(new Person("bbb ", personReference.get().getAge() + 2));
        });
        Thread t5 = new Thread(() -> {
            personReference.getAndSet(new Person("bbb ", personReference.get().getAge() + 2));
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        System.out.println(JSONObject.toJSONString(personReference.get()));
    }


}
