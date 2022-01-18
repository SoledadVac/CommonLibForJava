package com.common.lib.demo.test.yiqixue;

import lombok.Getter;
import lombok.Setter;
import org.springframework.test.context.junit4.SpringRunner;
import sun.tools.tree.PreIncExpression;

import java.io.Serializable;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/1/8
 * \* Time: 4:07 下午
 * \* Description:
 * \
 */
@Getter
@Setter
public class StudyPlanData implements Serializable {
    private String id;
    private static final long serialVersionUID = 8555644251266713608L;
    private ScheduleActivity scheduleActivity;
    private List<ScheduleActivityExtensions> scheduleActivityExtensions;
    private List<ScheduleActivityRecommendTags> scheduleActivityRecommendTags;
    private List<StudyPlanningTask> studyPlanningTasks;
    private List<ScheduleActivityTaskRefs> scheduleActivityTaskRefs;

    @Getter
    @Setter
    public static class ScheduleActivity implements Serializable {
        private static final long serialVersionUID = 8773582764851870022L;
        private String id;
        private String name; //名称
        private List<Integer> grades;
    }

    @Getter
    @Setter
    public static class ScheduleActivityExtensions implements Serializable {
        private static final long serialVersionUID = 3786175702201696397L;
        private String suggestBackgroundImg;//活动页面图片
        private String subTitle; //二级标题
        private String introduced;//介绍
        private Integer rangeCount; //更新数
        private String suggestTitleColor;//标题颜色
        private String target;//目标
    }

    @Getter
    @Setter
    public static class ScheduleActivityRecommendTags implements Serializable {
        private static final long serialVersionUID = -6084667592815894140L;
        private String id;
        private String title;//名字
        private String titleColor;
        private String backgroundColor;
    }

    @Getter
    @Setter
    public static class StudyPlanningTask implements Serializable {
        private static final long serialVersionUID = 2021221788159507396L;
        private String id;
        private String name;
        private String brightSquareIcon; //圆的亮图
        private List<Integer> grades;
        private Integer sortIndex;
        private String courseId; //根据courseId去找学习规划
    }

    @Getter
    @Setter
    public static class ScheduleActivityTaskRefs implements Serializable {
        private static final long serialVersionUID = -8699349956233752477L;
        private String taskId;
        private Integer sort;
    }
}
