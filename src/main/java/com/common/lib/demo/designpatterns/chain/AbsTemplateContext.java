package com.common.lib.demo.designpatterns.chain;

import lombok.Getter;
import lombok.Setter;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 下午6:27
 * \* Description: 数据上下文抽象类，添加处理标记
 * \
 */
@Getter
@Setter
public abstract class AbsTemplateContext implements ITemplateContext {
    /**
     * 处理是否成功
     *
     **/
    protected boolean isSucceed = true;
}
