package com.common.lib.demo.spring.template;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/10
 * \* Time: 下午5:04
 * \* Description:
 * \
 */
@Service
public class DefaultProcessTemplate extends AbsProcessTemplate {
    @Override
    public ProcessEnum getProcessEnum() {
        return ProcessEnum.DEFAULT;
    }

    @Override
    public void processDataByType(ProcessContext processContext) {
        if (processContext == null) {
            return;
        }
        System.out.println("处理数据：" + JSONObject.toJSONString(processContext));
    }
}
