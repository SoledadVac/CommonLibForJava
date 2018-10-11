package com.common.lib.demo.designpatterns.chain;

import com.common.lib.demo.spring.SpringContainerSupport;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 上午10:36
 * \* Description: abs 处理链
 * \
 */
public abstract class AbsProcessor<E extends ITemplateContext> extends SpringContainerSupport {

    private final List<ITemplateTask> tasks = new LinkedList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        ChainTasks chainTasksAnnotation = getClass().getAnnotation(ChainTasks.class);
        Objects.requireNonNull(chainTasksAnnotation, "@ChainTasks is required");
        for (Class<? extends ITemplateTask> beanClass : chainTasksAnnotation.value()) {
            ITemplateTask task = null;
            Map<String, ? extends ITemplateTask> beans = applicationContext.getBeansOfType(beanClass);
            for (ITemplateTask bean : beans.values()) {
                if (bean.getClass() == beanClass) {
                    task = bean;
                    break;
                }
            }
            if (task == null) throw new IllegalStateException("No bean " + beanClass.getName() + " defined");
            tasks.add(task);
        }
    }

    /**
     * 处理数据过程
     * @param context
     * @return
     */
    protected E process(E context) {
        if (context == null) {
            return null;
        }
        for(ITemplateTask task : tasks){
            task.execute(context);
        }
        return context;
    }
}
