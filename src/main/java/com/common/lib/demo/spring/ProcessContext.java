package com.common.lib.demo.spring;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/10
 * \* Time: 下午2:59
 * \* Description: 处理中的数据上下文
 * \
 */
@Getter
@Setter
public class ProcessContext {
    /**
     * 类型
     */
    private ProcessEnum processEnum;

    /**
     * 其他数据，此定义只是为了做个示例
     */
    private Map<String, String> demoDataMap;

}
