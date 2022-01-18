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
 * \* Time: 1:54 下午
 * \* Description:
 * \
 */
@Getter
@Setter
public class CheckPointTemplate implements Serializable {
    private static final long serialVersionUID = -4058508120476870454L;
    private String id;                          // 模板id
    private Date createAt;                      // 创建时间
    private Date updateAt;                      // 修改时间
    private String templateName;//模板名称
    private String templateNote;//模板备注说明
    private String templateWords;//模板文字内容
    private String coverUrl;//封面图
    private String cornerIcon;//角标icon
    private Integer rank;//排序值
    private String createUserName; //创建人
    private String updateUserName; //最近更新人
    private String onlineStatus = "ONLINE"; // 状态：ONLINE 在线 OFFLINE 下线
    private List<Files> picVideoFiles;//图片，视频文件
    private Files voiceFile;//语音
    private String templateType = "STUDY_PLAN";  //模板类型
    private List<StudyPlan> dayStudyList;//学习计划相关资源
    private List<Integer> repeat;

    /**
     * 音视频文件信息
     */
    @Getter
    @Setter
    public static class Files implements Serializable {
        private static final long serialVersionUID = -2245806669251047450L;
        private int type;// 0:图片 ； 1：视频 ； 2：音频；
        private String url;
        private Long duration;
    }

    // 根据模板类型而来的类型内容
    private String teachingGoals;//教学目标(学习计划)
    private String subTitle; //方案小标题
    private String coverGotoUrl;//头图链接
    private String learnTemplateId;//关联一起学的模板ID

    /**
     * 学习计划阶段资源
     */
    @Getter
    @Setter
    public static class StudyPlan implements Serializable {
        private static final long serialVersionUID = -2245806669251047451L;
        private String stageName; //阶段名称
        private List<StudyPlanDaily> studyPlanDailys; //阶段每天数据
    }

    /**
     * 学习计划阶段每天资源
     */
    @Getter
    @Setter
    public static class StudyPlanDaily implements Serializable {
        private static final long serialVersionUID = -2245806669251047452L;
        private String dayTitle; //学习目标
        private String dayId;    //当天id
        private List<StudyPlanDailyLearn> studyPlanDailyLearns; //阶段每天数据
    }

    /**
     * 学习计划阶段每天每个学习资源
     */
    @Getter
    @Setter
    public static class StudyPlanDailyLearn implements Serializable {
        private static final long serialVersionUID = -2245806669251047453L;
        private String type; //学习类型 视频  绘本  习题
        private String title; //环节副标题
        private String dailyLearnId;    // 用于定位某天题目
        private List<String> questionIds;
        private String pictureBookId; //绘本id
        private String videoId; // 互动视频 videoId
        private String previewUrl;//资源地址
        private String videoUrl; //视频资源的URL
        private String coverUrl;//视频封面图
        private Long duration;//视频时长
    }
}
