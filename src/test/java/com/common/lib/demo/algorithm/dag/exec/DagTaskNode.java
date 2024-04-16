package com.common.lib.demo.algorithm.dag.exec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.lib.demo.algorithm.dag.build.ETLTaskEdgeInfo;
import com.common.lib.demo.algorithm.dag.build.ETLTaskInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class DagTaskNode implements Serializable, Runnable {
    String id; //节点id
    String nodeName;//节点名称
    DagTaskNode previews;//前置节点
    List<DagTaskNode> tails = new LinkedList<>();
    volatile boolean finished;//节点是否执行完成
    volatile boolean succeed;//节点是否执行成功
    Map<String, Object> nodeResultMap = new HashMap<>();//节点输出数据
    JSONObject exeJson;//节点配置数据

    /**
     * 节点的具体执行
     *
     * @throws Exception
     */
    public void executeNode() throws Exception {
        while (previews != null && !previews.isFinished()) {
            synchronized (previews) {
                previews.wait();
            }
        }
        //前置节点都执行完了，可以开始执行本节点任务了


        //本节点执行完了，通知下
        synchronized (this) {
            this.notifyAll();
        }
    }


    private static final long serialVersionUID = 3637220767545316789L;

    @Override
    public void run() {
        try {
            executeNode();
            this.succeed = true;
        } catch (Exception e) {
            //一些失败的处理....
        } finally {
            this.finished = true;
        }

    }

    public static void main(String[] args) {
        List<ETLTaskInfo> taskInfoList = new LinkedList<>();//这里数据一般是读取的配置
        List<ETLTaskEdgeInfo> edgeInfoList = new LinkedList<>();//这里数据一般是读取的配置

        Collection<DagTaskNode> roots = new LinkedList<>(); //头结点们
        Map<String, DagTaskNode> nodeMap = new HashMap<>();
        //构建图关系
        for (ETLTaskInfo task : taskInfoList) {
            DagTaskNode taskNode = new DagTaskNode();
            taskNode.setId(task.getId());
            taskNode.setNodeName(task.getName());
            taskNode.setExeJson(JSON.parseObject(task.getExeJson()));
            nodeMap.put(task.getId(), taskNode);
        }
        List<DagTaskNode> dagRoots = new LinkedList<>();
        //构建边关系
        for (ETLTaskInfo task : taskInfoList) {
            DagTaskNode taskNode = nodeMap.get(task.getId());
            //前置节点设置
            String prevId = CollectionUtils.isEmpty(edgeInfoList) ?
                    null :
                    edgeInfoList
                            .stream()
                            .filter(edge -> task.getId().equals(edge.getNextTaskId()))
                            .map(ETLTaskEdgeInfo::getTaskId)
                            .findFirst().orElse(null);
            if (prevId == null) {
                dagRoots.add(taskNode);
            } else {
                taskNode.setPreviews(nodeMap.get(prevId));
            }
            //后置节点设置
            List<String> tailIds = CollectionUtils.isEmpty(edgeInfoList) ?
                    null :
                    edgeInfoList
                            .stream()
                            .filter(edge -> task.getId().equals(edge.getId()))
                            .map(ETLTaskEdgeInfo::getNextTaskId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(tailIds)) {
                for (String id : tailIds) {
                    List<DagTaskNode> tails = taskNode.getTails();
                    tails.add(nodeMap.get(id));
                }
            }
        }
        DagExecutor.bfsWithMoreRoots(roots); //处理图任务
    }
}
