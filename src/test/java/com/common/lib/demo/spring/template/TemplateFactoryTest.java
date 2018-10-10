package com.common.lib.demo.spring.template;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/10
 * \* Time: 下午5:23
 * \* Description:
 * \
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateFactoryTest {

    @Autowired
    private ProcessByEnumFactory processByEnumFactory;


    @Test
    public void getProcessTest() {
        ProcessContext processContext = new ProcessContext();
        Map<String, String> data = Maps.newHashMap();
        data.put("aa", "foo");
        processContext.setDemoDataMap(data);

        AbsProcessTemplate oneTemplate = processByEnumFactory.getProcessTemple(ProcessEnum.PROCESS_TYPE_ONE);
        oneTemplate.processDataByType(processContext);

    }
}
