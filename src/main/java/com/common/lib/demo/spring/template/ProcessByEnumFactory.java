package com.common.lib.demo.spring.template;

import com.common.lib.demo.spring.SpringContainerSupport;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/10
 * \* Time: 下午2:53
 * \* Description: 获取处理器的工厂方法
 * \
 */
@Service
public class ProcessByEnumFactory extends SpringContainerSupport {

    private Map<ProcessEnum, AbsProcessTemplate> processTemplateMap = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        this.processTemplateMap = Maps.newHashMap();
        Map<String, AbsProcessTemplate> processMap = applicationContext.getBeansOfType(AbsProcessTemplate.class);
        processMap.values().forEach(bean -> processTemplateMap.put(bean.getProcessEnum(), bean));
    }

    /**
     * 根据类型获取处理模板
     *
     * @param processEnum
     * @return
     */
    public AbsProcessTemplate getProcessTemple(ProcessEnum processEnum) {
        if (processEnum == null) {
            processEnum = ProcessEnum.DEFAULT;
        }
        if (!processTemplateMap.containsKey(processEnum)) {
            processEnum = ProcessEnum.DEFAULT;
        }
        return this.processTemplateMap.get(processEnum);
    }

}
