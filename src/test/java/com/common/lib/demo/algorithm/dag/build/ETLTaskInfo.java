package com.common.lib.demo.algorithm.dag.build;

import lombok.Data;

import java.io.Serializable;

/**
 * 任务关键数据
 */
@Data
public class ETLTaskInfo implements Serializable {
    private static final long serialVersionUID = -8965767304205724195L;
    String id;
    String name;
    String exeJson;//配置json
}
