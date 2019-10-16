package com.common.lib.demo.concurrent.thread;

import com.common.lib.demo.File.FileUtils;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/16
 * \* Time: 11:28 AM
 * \* Description:
 * \
 */
public class ConcurrentCycleBarrierTest {

    String path = "/Users/liuhuichao/Downloads/sourceProcessTest.txt"; //文件大小：941M
    int totalLine = FileUtils.getLineNum(path);
    int item = 100000;
    int group = totalLine / item;
    ExecutorService executor = Executors.newFixedThreadPool(group);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(group + 1, () -> System.out.println(Thread.currentThread().getId()));
    Queue<String[]> parsedData = new ConcurrentLinkedQueue();

    /**
     * 直接读报错
     *
     * @throws Exception
     */
    @Test
    public void test0() throws Exception {
        long begin = System.currentTimeMillis();
        Queue<String> queue = FileUtils.readAllFileByLine(path);
        while (!queue.isEmpty()) {
            String s = queue.poll();
            parseLine(s);
        }
        long end = System.currentTimeMillis();
        System.out.println("cost time = " + (end - begin)); //直接读的话：java.lang.OutOfMemoryError: Java heap space
    }

    @Test
    public void test() throws Exception {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < group; i++) {
            int start = i * item;
            int end = start + item;
            if (i == group - 1) {
                end = end + totalLine % item;
            }
            executor.submit(new ParseFileClass(start, end));
        }
        cyclicBarrier.await();
        long end = System.currentTimeMillis();
        System.out.println("cost time = " + (end - begin)); //直接读的话：java.lang.OutOfMemoryError: Java heap space
    }


    class ParseFileClass implements Runnable {
        int starLine;
        int endLine;

        ParseFileClass(int starLine, int endLine) {
            this.starLine = starLine;
            this.endLine = endLine;
        }

        @Override
        public void run() {
            try {
                Queue<String> queue = FileUtils.readFileByLines(path, starLine, endLine);
                while (!queue.isEmpty()) {
                    String s = queue.poll();
                    parsedData.add(parseLine(s));
                }
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 0 ：processId
     * 1 : homeworkId
     *
     * @param source
     * @return
     */
    private String[] parseLine(String source) {
        //处理每行的过程。。
        String[] result = new String[2];
        String[] sp = source.split("\t");
        int index = 0;
        for (String s : sp) {
            if (s == null || s.length() == 0) {
                continue;
            }
            if (index == 0) {
                result[1] = s;
            }
            if (index == 4) {
                result[0] = s;
            }
            index++;
        }
        return result;
    }
}
