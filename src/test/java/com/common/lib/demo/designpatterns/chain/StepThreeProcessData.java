package com.common.lib.demo.designpatterns.chain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 下午2:50
 * \* Description: 第三步：处理数据
 * \
 */
@Service
public class StepThreeProcessData implements ITemplateTask<OffDutyTemplateContext> {
    @Override
    public void execute(OffDutyTemplateContext context) {
        /**
         * 处理上下文的数据过程
         *  比如从系统【B】查数据，拼装到要返回的结果中，再放入数据上下文
         * **/
        System.out.println("第三步处理完成，将进行下一步的处理。。。。");
        System.out.println(JSONObject.toJSONString(context));
    }
}
