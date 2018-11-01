package com.common.lib.demo.concurrent.thread;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/30
 * \* Time: 下午2:43
 * \* Description: 线程进程检查
 * \
 */

public class MultiThreadTest {
    /**
     * 获取线程信息
     */
    @Test
    public void getThreadInfo(){
        ThreadMXBean mxBean= ManagementFactory.getThreadMXBean();
        ThreadInfo[] infos=mxBean.dumpAllThreads(false,false);
        for(ThreadInfo info : infos){
            System.out.println("id="+info.getThreadId()+" ; name="+info.getThreadName());
        }
    }

    /**
     * 获取进程信息
     */
    @Test
    public void getProcessInfo(){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("进程名称："+runtimeMXBean.getName());
        System.out.println("进程id:"+runtimeMXBean.getName().split("@")[0]);
    }
}
