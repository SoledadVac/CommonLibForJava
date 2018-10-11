package com.common.lib.demo.designpatterns.chain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 下午2:49
 * \* Description:第二步：处理数据
 * \
 */
@Service
public class StepTwoProcessData implements ITemplateTask<OffDutyTemplateContext>{
    @Override
    public void execute(OffDutyTemplateContext context) {
        /**
         * 处理上下文的数据过程
         *  比如从系统【A】查数据，拼装到要返回的结果中，再放入数据上下文
         * **/
        System.out.println("第二步处理完成，将进行下一步的处理。。。。");
        System.out.println(JSONObject.toJSONString(context));
    }
}
