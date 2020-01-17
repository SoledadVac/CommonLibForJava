package com.common.lib.demo.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/16
 * \* Time: 11:29 AM
 * \* Description:
 * \
 */
public class FileUtils {
    /**
     * 获取行数
     *
     * @param path
     * @return
     */
    public static int getLineNum(String path) {
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

    public static Queue<String> readFileByLines(String path, int starLine, int endLine) throws Exception {
        Queue<String> queue = new LinkedBlockingQueue<>();
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {
                if (starLine <= line && line < endLine) {
                    queue.add(tempString);
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
        return queue;
    }

    public static Queue<String> readAllFileByLine(String path) throws Exception {
        Queue<String> queue = new LinkedBlockingQueue<>();
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                queue.add(tempString);
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
        return queue;
    }

}
