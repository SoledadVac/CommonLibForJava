package com.common.lib.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.lib.demo.network.HttpClientUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/15
 * \* Time: 5:34 PM
 * \* Description:
 * \
 */
public class OcrMentalImageDetailTest {
    String path = "/Users/liuhuichao/Downloads/sourceProcess.txt";
    int totalLine = getLineNum(path); //10000
    int item = 1000;
    int group = totalLine / item;
    ExecutorService executor = Executors.newFixedThreadPool(group);
    CountDownLatch countDownLatch = new CountDownLatch(group);
    Queue<String[]> queue = new ConcurrentLinkedQueue();

    @Test
    public void test() throws Exception {
        long begin = System.currentTimeMillis();
        // FileOutputStream fos = new FileOutputStream("/Users/liuhuichao/Downloads/processresult1.txt");
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
       /* while (!queue.isEmpty()) {
            fos.write(JSONObject.toJSONBytes(queue.poll()));
        }
        fos.close();*/
       // processQueue(queue);
        long end = System.currentTimeMillis();
        System.out.println("cost time = " + (end - begin));
    }

    @Test
    public void test2() throws Exception {
        String url = "http://10.7.8.40:23970/hydra/component/service/invoke.do";
        String serviceInterface = "com.voxlearning.utopia.service.newhomework.api.NewHomeworkProcessResultLoader";
        String serviceGroup = "alps-hydra-staging";
        String serviceVersion = "20170215";
        String methodId = "2";
        String p0 = "201910_5da41ff8373be2e943d52f56_2";
        String p1 = "5da439120d1efae95002a9c1-1571037176532";
        NameValuePair serviceInterfaceMap = new BasicNameValuePair("serviceInterface", serviceInterface);
        NameValuePair serviceGroupMap = new BasicNameValuePair("serviceGroup", serviceGroup);
        NameValuePair serviceVersionMap = new BasicNameValuePair("serviceVersion", serviceVersion);
        NameValuePair methodIdMap = new BasicNameValuePair("methodId", methodId);
        NameValuePair p0Map = new BasicNameValuePair("p0", p0);
        NameValuePair p1Map = new BasicNameValuePair("p1", p1);
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(serviceInterfaceMap);
        nameValuePairList.add(serviceGroupMap);
        nameValuePairList.add(serviceVersionMap);
        nameValuePairList.add(methodIdMap);
        nameValuePairList.add(p0Map);
        nameValuePairList.add(p1Map);
        String result = HttpClientUtil.post(url, nameValuePairList, HttpClientUtil.getResponseHandler());
        JSONObject obj = JSON.parseObject(result);
        Object id = obj.get("id");
        System.out.println(id);
        Map wordPaperDictationImageDetailObj = (Map) obj.get("wordPaperDictationImageDetail");
        String imgUrl = (String) wordPaperDictationImageDetailObj.get("img_url");
        System.out.println(imgUrl);
    }

    public String getDataItem(String p0, String p1) throws Exception {
        String url = "http://10.7.8.40:23970/hydra/component/service/invoke.do";
        String serviceInterface = "com.voxlearning.utopia.service.newhomework.api.NewHomeworkProcessResultLoader";
        String serviceGroup = "alps-hydra-staging";
        String serviceVersion = "20170215";
        String methodId = "2";
        NameValuePair serviceInterfaceMap = new BasicNameValuePair("serviceInterface", serviceInterface);
        NameValuePair serviceGroupMap = new BasicNameValuePair("serviceGroup", serviceGroup);
        NameValuePair serviceVersionMap = new BasicNameValuePair("serviceVersion", serviceVersion);
        NameValuePair methodIdMap = new BasicNameValuePair("methodId", methodId);
        NameValuePair p0Map = new BasicNameValuePair("p0", p0);
        NameValuePair p1Map = new BasicNameValuePair("p1", p1);
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(serviceInterfaceMap);
        nameValuePairList.add(serviceGroupMap);
        nameValuePairList.add(serviceVersionMap);
        nameValuePairList.add(methodIdMap);
        nameValuePairList.add(p0Map);
        nameValuePairList.add(p1Map);
        String result = HttpClientUtil.post(url, nameValuePairList, HttpClientUtil.getResponseHandler());
        JSONObject obj = JSON.parseObject(result);
        Map wordPaperDictationImageDetailObj = (Map) obj.get("wordPaperDictationImageDetail");
        String imgUrl = (String) wordPaperDictationImageDetailObj.get("img_url");
        return imgUrl;
    }

    private void processQueue(Queue<String[]> queue) throws Exception {
        FileOutputStream fos = new FileOutputStream("/Users/liuhuichao/Downloads/urlResult1.txt");
        while (!queue.isEmpty()) {
            String[] ids = queue.poll();
            String url = getDataItem(ids[0], ids[1]);
            String write = url + "\n";
            fos.write(write.getBytes());
        }
        fos.close();
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
