package com.common.lib.demo.designpatterns.chain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 上午11:35
 * \* Description: 测试职责链处理
 * \
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChainTest {

    @Autowired
    OffDutyTaskOffDutyProcessor processor;

    @Test
    public void chainTest(){
        OffDutyTemplateContext dataContext=new OffDutyTemplateContext();
        dataContext.setId(1000L);
        dataContext.setName("水田");
        processor.process(dataContext);
    }
}
