package com.common.lib.demo.concurrent.thread;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

    /**线程池方式**/
    @Test
    public void returnTest() throws Exception {
        //原始数据
        List<ItemData> data1 =
                Lists.newArrayList(
                        new ItemData(1), new ItemData(2), new ItemData(3)
                );
        List<ItemData> data2 =
                Lists.newArrayList(
                        new ItemData(1), new ItemData(2), new ItemData(3)
                );
        List<ItemData> data3 =
                Lists.newArrayList(
                        new ItemData(1), new ItemData(2), new ItemData(3)
                );
        List<List<ItemData>> groupedData = Lists.newArrayList();//分组之后的数据
        groupedData.add(data1);
        groupedData.add(data2);
        groupedData.add(data3);
        //执行插入操作
        ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        List<Integer> result = new ArrayList<>(); //存放返回结果

        for (List<ItemData> item : groupedData) {
            Callable<Integer> c = new ExecuteClass(item);
            Future<Integer> f = exec.submit(c);
            result.add(f.get());
        }
        System.out.println(JSONObject.toJSONString(result));




    }

    class ExecuteClass implements Callable<Integer> {

        private List<ItemData> value; //value代表要操作的数据包

        public ExecuteClass(List<ItemData> value) {
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {
            //这里拿到value,执行插入操作
            return 2;
        }
    }

    //要插入的单个数据
    @Getter
    @Setter
    class ItemData {
        public ItemData(Integer value) {
            this.value = value;
        }

        private Integer value; //value代表要操作的数据包
    }



    /**下面是fork / join方式**/
    class ExeccuteProcess extends RecursiveTask<List<ItemData>>{

        private List<ItemData> value; //value代表要操作的数据包

        public ExeccuteProcess(List<ItemData> value) {
            this.value = value;
        }

        @Override
        protected List<ItemData> compute() {
            //这里执行你的批量插入操作
            return Lists.newArrayList(new ItemData(1));
        }
    }

    @Test
    public void testForkJoinMethodInsert() throws Exception{
        //原始数据
        List<ItemData> data1 =
                Lists.newArrayList(
                        new ItemData(1), new ItemData(2), new ItemData(3)
                );
        List<ItemData> data2 =
                Lists.newArrayList(
                        new ItemData(1), new ItemData(2), new ItemData(3)
                );
        List<ItemData> data3 =
                Lists.newArrayList(
                        new ItemData(1), new ItemData(2), new ItemData(3)
                );
        List<List<ItemData>> groupedData = Lists.newArrayList();//分组之后的数据
        groupedData.add(data1);
        groupedData.add(data2);
        groupedData.add(data3);
        List<List<ItemData>> result = new ArrayList<>(); //存放返回结果
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);
        for (List<ItemData> item : groupedData) {
            ExeccuteProcess p=new ExeccuteProcess(item);
            ForkJoinTask<List<ItemData>> reslut = forkJoinPool.submit(p);
            result.add(reslut.get());
        }
        System.out.println(JSONObject.toJSONString(result));

    }



}
