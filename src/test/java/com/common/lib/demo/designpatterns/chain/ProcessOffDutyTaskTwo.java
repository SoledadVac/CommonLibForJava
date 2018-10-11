package com.common.lib.demo.designpatterns.chain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 下午2:49
 * \* Description:
 * \
 */
@Service
public class ProcessOffDutyTaskTwo implements ITemplateTask<OffDutyTemplateContext>{
    @Override
    public void execute(OffDutyTemplateContext context) {
        System.out.println("ProcessOffDutyTaskTwo");
        System.out.println(JSONObject.toJSONString(context));
    }
}
