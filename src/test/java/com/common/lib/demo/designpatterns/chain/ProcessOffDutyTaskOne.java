package com.common.lib.demo.designpatterns.chain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 上午11:47
 * \* Description: 处理离职任务链
 * \
 */
@Service
public class ProcessOffDutyTaskOne implements ITemplateTask<OffDutyTemplateContext> {
    @Override
    public void execute(OffDutyTemplateContext context) {
        context.setSucceed(true); //设置处理情况标记
        System.out.println("ProcessOffDutyTaskOne");
        System.out.println(JSONObject.toJSONString(context));
    }
}
