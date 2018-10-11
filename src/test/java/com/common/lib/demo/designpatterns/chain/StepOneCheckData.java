package com.common.lib.demo.designpatterns.chain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 上午11:47
 * \* Description: 第一步：校验要处理的数据
 * \
 */
@Service
public class StepOneCheckData implements ITemplateTask<OffDutyTemplateContext> {
    @Override
    public void execute(OffDutyTemplateContext context) {

        /**
         * 放置各种数据校验过程
         * **/
        if (context.getId() <= 0) {
            context.setSucceed(false);
            return;
        }
        System.out.println("第一步数据校验成功，将进行下一步的处理。。。。");
        System.out.println(JSONObject.toJSONString(context));
    }
}
