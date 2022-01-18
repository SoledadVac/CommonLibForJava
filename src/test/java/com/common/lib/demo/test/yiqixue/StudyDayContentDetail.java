package com.common.lib.demo.test.yiqixue;

import com.google.inject.internal.cglib.proxy.$ProxyRefDispatcher;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/1/9
 * \* Time: 11:24 上午
 * \* Description:
 * \
 */
@Getter
@Setter
public class StudyDayContentDetail implements Serializable {
    private static final long serialVersionUID = 6922270850969499387L;
    private String _id;
    private String name;
    private List<Content> content;
    private String type;

    @Getter
    @Setter
    public static class Content implements Serializable {
        private static final long serialVersionUID = -3698876288210002096L;
        private DirectVideoContent directVideoContent;
        private ChooseContent chooseContent;
        private ApplicationLinkContent applicationLinkContent;
        private IndirectVideoContent indirectVideoContent;
    }

    @Getter
    @Setter
    public static class DirectVideoContent implements Serializable {
        private static final long serialVersionUID = 5198247362621718641L;
        private Long duration;//时长
        private String videoImgUrl;
        private String pic;
        private String videoUrl;
        private String title;
    }

    @Getter
    @Setter
    public static class ChooseContent implements Serializable {
        private static final long serialVersionUID = -2171669875524305610L;
        private List<String> questionId;
        private Boolean needScore;
        private Boolean needResult;
        private String pic;
        private String title;
    }

    @Getter
    @Setter
    public static class ApplicationLinkContent implements Serializable {
        private static final long serialVersionUID = 8542295331643804418L;
        private String contentId;//pid
        private Boolean needScore;
        private Boolean needResult;
        private String pic;
        private String title;
    }

    @Getter
    @Setter
    public static class IndirectVideoContent implements Serializable {
        private static final long serialVersionUID = 451193387535170505L;
        private String videoId;
        private String pic;
        private String title;
    }


}
