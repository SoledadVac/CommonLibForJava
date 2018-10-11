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
        /**
         * 注：在写业务逻辑的时候，可以先构造数据上下文（数据上下文包含需要），
         * 之后将真个业务逻辑根据用途拆分成处理类，
         * 按照顺序放在注解里面(注解里面类放置的顺序就是处理顺序)
         * **/
        OffDutyTemplateContext dataContext=new OffDutyTemplateContext();
        dataContext.setId(1000L);
        dataContext.setName("水田");
        processor.process(dataContext);
        if(dataContext.isSucceed){
            //处理成功，巴拉巴拉。。。
        }
    }
}
