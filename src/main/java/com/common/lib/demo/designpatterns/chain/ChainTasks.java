package com.common.lib.demo.designpatterns.chain;


import java.lang.annotation.*;

/**
 * 定义职责链处理的步骤及顺序
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChainTasks {
    Class<? extends ITemplateTask>[] value();
}
