package com.common.lib.demo.algorithm.dag.build;

import lombok.Data;

import java.io.Serializable;

/**
 * 边关键数据
 */
@Data
public class ETLTaskEdgeInfo implements Serializable {
    private static final long serialVersionUID = 293185776150464728L;
    String id; //边id
    String taskId; // from任务id
    String nextTaskId; //to任务id
}
