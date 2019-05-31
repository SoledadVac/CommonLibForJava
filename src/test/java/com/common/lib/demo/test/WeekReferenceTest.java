package com.common.lib.demo.test;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Maps;
import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.stream.Collectors;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/12/24
 * \* Time: 4:50 PM
 * \* Description:
 * \
 */
public class WeekReferenceTest {

    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

    @Test
    public void test() {
        int size = 3;
        LinkedList<WeakReference<VeryBig>> weakList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            weakList.add(new VeryBigWeakReference(new VeryBig("Weak " + i), rq));
            System.out.println("Just created weak: " + weakList.getLast());

        }
        System.gc();
        try { // 下面休息几分钟，让上面的垃圾回收线程运行完成
            Thread.currentThread().sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkQueue();

    }

    public static void checkQueue() {
        Reference<? extends VeryBig> ref = null;
        while ((ref = rq.poll()) != null) {
            if (ref != null) {
                System.out.println("In queue: " + ((VeryBigWeakReference) (ref)).id);
            }
        }
    }


    class VeryBig {
        public String id;
        // 占用空间,让线程进行回收
        byte[] b = new byte[2 * 1024];

        public VeryBig(String id) {
            this.id = id;
        }

        protected void finalize() {
            System.out.println("Finalizing VeryBig " + id);
        }
    }

    class VeryBigWeakReference extends WeakReference<VeryBig> {
        public String id;

        public VeryBigWeakReference(VeryBig big, ReferenceQueue<VeryBig> rq) {
            super(big, rq);
            this.id = big.id;
        }

        protected void finalize() {
            System.out.println("Finalizing VeryBigWeakReference " + id);
        }
    }

    @Test
    public void test1() {
        List<Map<String, Student>> list = new ArrayList<>();
        Map<String, Student> map1 = Maps.newHashMap("a", new Student("1", "lhc"));
        Map<String, Student> map2 = Maps.newHashMap("a", new Student("2", "jay"));
        Map<String, Student> map3 = Maps.newHashMap("a", new Student("2", "bbb"));
        Map<String, Student> map4 = Maps.newHashMap("a", new Student("1", "ccc"));
        Map<String, Student> map5 = Maps.newHashMap("a", new Student("2", "fff"));
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map4);
        list.add(map5);
        Map<String, List<Student>> result = list.stream()
                .map(map -> map.values())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Student::getId));
        System.out.println(JSONObject.toJSONString(result));
    }

    @Data
    @AllArgsConstructor
    class Student {
        private String id;
        private String name;
    }

}
