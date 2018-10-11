package com.common.lib.demo.designpatterns.template;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/10
 * \* Time: 下午5:03
 * \* Description:
 * \
 */
@Service
public class DemoTwoProcessTemplate extends AbsProcessTemplate  {
    @Override
    public ProcessEnum getProcessEnum() {
        return ProcessEnum.PROCESS_TYPE_TWO;
    }

    @Override
    public void processDataByType(ProcessContext processContext) {
        if (processContext == null) {
            return;
        }
        System.out.println("处理数据：" + JSONObject.toJSONString(processContext));
    }
}
