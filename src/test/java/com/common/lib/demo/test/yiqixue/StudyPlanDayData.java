package com.common.lib.demo.test.yiqixue;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/1/9
 * \* Time: 10:58 上午
 * \* Description:
 * \
 */
@Getter
@Setter
public class StudyPlanDayData implements Serializable {
    private static final long serialVersionUID = -4338647458595517215L;
    private Programme programme;
    private List<Daily> dailies;
    private List<Item> items;


    @Getter
    @Setter
    public static class Programme implements Serializable {
        private static final long serialVersionUID = 6208223182658875850L;
        private String id;
        private String name;
        private String image;
        private String target;
    }

    @Getter
    @Setter
    public static class Daily implements Serializable {
        private static final long serialVersionUID = -5165565553914588698L;
        private String id;
        private String desc; //天的描述
        private Integer stageIndex;//第几个阶段
        private String stageDesc;//阶段描述
        private String programmeId;//方案ID
        private Integer sort;
    }

    @Getter
    @Setter
    public static class Item implements Serializable {
        private static final long serialVersionUID = 428577729770113875L;
        private String id;
        private String programmeId;
        private String refId;//具体每天配置的题目
        private String dailyId;// 天,匹配上面的Daily的id
        private Integer sort;
        private String type;//type=COMPONENT 表示一定是上单环节，type=OFFINE的代表就是那种拍照的
        private String title;//拍照标题

    }
}
