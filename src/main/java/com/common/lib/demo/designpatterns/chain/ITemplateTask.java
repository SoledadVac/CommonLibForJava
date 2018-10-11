package com.common.lib.demo.designpatterns.chain;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/10
 * \* Time: 下午6:46
 * \* Description: 处理抽象类
 * \
 */
public interface ITemplateTask<E extends ITemplateContext> {
    void execute(E context);
}
