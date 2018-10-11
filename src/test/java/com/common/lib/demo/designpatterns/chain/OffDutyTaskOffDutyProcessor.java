package com.common.lib.demo.designpatterns.chain;

import org.springframework.stereotype.Service;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 下午2:50
 * \* Description:
 * \
 */
@Service
@ChainTasks({
        StepOneCheckData.class,
        StepTwoProcessData.class,
        StepThreeProcessData.class
})
public class OffDutyTaskOffDutyProcessor extends AbsProcessor<OffDutyTemplateContext>{

}
