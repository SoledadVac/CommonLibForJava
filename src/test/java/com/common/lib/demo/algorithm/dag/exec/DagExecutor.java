package com.common.lib.demo.algorithm.dag.exec;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DagExecutor {

    public static DagExecuteResult bfsWithMoreRoots(Collection<DagTaskNode> roots) {
        if (CollectionUtils.isEmpty(roots)) {
            return null;
        }
        DagExecuteResult dagExecuteResult = new DagExecuteResult();
        Queue<DagTaskNode> allTask = new LinkedBlockingQueue<>(); //所有任务队列
        Queue<DagTaskNode> queue = new LinkedBlockingQueue<>(); //等待处理队列
        queue.addAll(roots);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                DagTaskNode cur = queue.poll();
                try {
                    allTask.add(cur);
                    //线程池执行节点...
                } catch (Exception e) {
                    // handle error
                }
                if (CollectionUtils.isEmpty(cur.getTails())) {
                    continue;
                }
                queue.addAll(cur.getTails());
            }
        }
        while (!allTask.stream().allMatch(DagTaskNode::isSucceed)) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                //处理下
            }
        }
        //完成了的话
        if (allTask.stream().allMatch(DagTaskNode::isSucceed)) {
            //都成功了
            //处理结果赋值
        } else {
            //有失败的任务
            //处理结果赋值
        }
        return dagExecuteResult;
    }
}
