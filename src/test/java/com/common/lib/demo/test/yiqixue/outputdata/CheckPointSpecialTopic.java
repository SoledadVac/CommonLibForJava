package com.common.lib.demo.test.yiqixue.outputdata;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/1/9
 * \* Time: 1:52 下午
 * \* Description:
 * \
 */
@Getter
@Setter
public class CheckPointSpecialTopic implements Serializable {
    private static final long serialVersionUID = 800353812449326812L;
    private String id;                          // 模板id
    private String specialTopicName;            // 名称
    private String specialTopicNote;            // 说明
    private String coverUrl;//封面图
    private String cornerIcon;//角标icon
    private String createUserName; //创建人
    private String updateUserName; //最近更新人
    private String onlineStatus = "ONLINE"; // 状态：ONLINE 在线 OFFLINE 下线
    private List<String> templateIds;//专题下的模板id
    private Integer rank;//排序值
    private Boolean disabled;// 默认false，删除true

    //其他依赖于专题内容类型的字段
    private List<String> studyPlanTags; //一起学习的标签
    private String headImageUrl;     //头图
    private String titleColor;   //标题颜色
    private String gotoUrl;   //跳转链接
    private Integer taskNums; //预计任务数量
    private String goalWords; //学习目标
    private String introducedWords; //学习计划介绍
    private String subtitle; //学习计划副标题
    private Boolean canSkipToTemplate = true; //是否跳转至模板专题页
}
