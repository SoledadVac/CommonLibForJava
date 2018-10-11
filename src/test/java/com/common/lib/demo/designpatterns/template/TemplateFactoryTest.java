package com.common.lib.demo.designpatterns.template;

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
 * \* Description:工厂+模板 使用测试
 * \
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateFactoryTest {

    @Autowired
    private ProcessByEnumFactory processByEnumFactory;


    @Test
    public void getProcessTest() {
        /**组装数据上下文**/
        ProcessContext processContext = new ProcessContext();
        Map<String, String> data = Maps.newHashMap();
        data.put("aa", "foo");
        processContext.setDemoDataMap(data);
        processContext.setProcessEnum(ProcessEnum.PROCESS_TYPE_ONE);

        /**根据数据上下文中放置的标记来选择模板**/
        AbsProcessTemplate oneTemplate = processByEnumFactory.getProcessTemple(processContext.getProcessEnum());
        oneTemplate.processDataByType(processContext);

        /**当增加新的类型时候，只需要继承模板抽象类，实现相应的处理方法即可，无需改动原业务代码,符合开放封闭的原则**/

    }
}
