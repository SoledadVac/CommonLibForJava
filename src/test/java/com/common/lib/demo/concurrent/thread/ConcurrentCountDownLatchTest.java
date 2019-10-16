package com.common.lib.demo.concurrent.thread;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/16
 * \* Time: 10:49 AM
 * \* Description: test sth
 * \
 */
public class ConcurrentCountDownLatchTest {

    String path = "/Users/liuhuichao/Downloads/sourceProcessTest.txt";
    int totalLine = getLineNum(path);
    int item = 100000;
    int group = totalLine / item;
    ExecutorService executor = Executors.newFixedThreadPool(group);
    CountDownLatch countDownLatch = new CountDownLatch(group);
    Queue<String[]> queue = new ConcurrentLinkedQueue();


    @Test
    public void test() throws Exception {
        long begin = System.currentTimeMillis();
        System.out.println("源文件行数 ：" + totalLine);
        if (group == 0) {
            long end = System.currentTimeMillis();
            System.out.println("cost time = " + (end - begin));
            return;
        }
        for (int i = 0; i < group; i++) {
            int start = i * item;
            int end = start + item;
            if (i == group - 1) {
                end = end + totalLine % item;
            }
            executor.submit(new ParseFileClass(start, end));
        }
        countDownLatch.await();
        System.out.println("total size = " + queue.size());
        long end = System.currentTimeMillis();
        System.out.println("cost time = " + (end - begin));
    }

    /**
     * 获取行数
     *
     * @param path
     * @return
     */
    public int getLineNum(String path) {
        File file = new File(path);
        int count = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) {
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return count;
    }

    public void readFileByLines(int starLine, int endLine) throws Exception {
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {
                if (starLine <= line && line < endLine) {
                    //System.out.println(JSONObject.toJSONString(parseLine(tempString)));
                    queue.add(parseLine(tempString));
                }
                line++;
                if (line >= endLine) {
                    break;
                }
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        countDownLatch.countDown();
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
                readFileByLines(starLine, endLine);
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
