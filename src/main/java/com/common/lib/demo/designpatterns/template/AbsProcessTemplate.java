package com.common.lib.demo.designpatterns.template;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/10
 * \* Time: 下午2:57
 * \* Description: 处理器
 * \
 */
public abstract class AbsProcessTemplate {
    /**
     * 获取处理类型
     *
     * @return
     */
    abstract public ProcessEnum getProcessEnum();

    /**
     * 处理数据的方法
     *
     * @param processContext
     */
    abstract public void processDataByType(ProcessContext processContext);

    //其他一些公用方法。。。。

}
