package com.common.lib.demo.designpatterns.chain;

import org.junit.Before;
import org.springframework.context.annotation.Bean;
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
        ProcessOffDutyTaskOne.class,
        ProcessOffDutyTaskTwo.class,
        ProcessOffDutyTaskThree.class
})
public class OffDutyTaskOffDutyProcessor extends AbsProcessor<OffDutyTemplateContext>{

}
