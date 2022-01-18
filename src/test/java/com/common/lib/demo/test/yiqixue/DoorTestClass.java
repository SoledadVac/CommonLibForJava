package com.common.lib.demo.test.yiqixue;

import com.alibaba.fastjson.JSONObject;
import com.common.lib.demo.test.yiqixue.outputdata.CheckPointLabel;
import com.common.lib.demo.test.yiqixue.outputdata.CheckPointSpecialTopic;
import com.common.lib.demo.test.yiqixue.outputdata.CheckPointTemplate;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/1/8
 * \* Time: 3:42 下午
 * \* Description:
 * \
 */
public class DoorTestClass {

    //原地址
    String plansFileUrl = "/Users/liuhuichao/test/计划和方案导出";
    String studyPlanUrl = "/Users/liuhuichao/test/计划和方案导出/学习计划"; //大的学习计划文件
    String studyPlanDayUrl = "/Users/liuhuichao/test/计划和方案导出/planDay"; //天的数据
    String circleFileUrl = "/Users/liuhuichao/test/计划和方案导出/formateCircleData.js"; //每天挂的题目
    String refIdsUrl = "/Users/liuhuichao/test/"; //存放refIds

    //二级目的地址
    String studyPlanAbsPath = "/Users/liuhuichao/test/dataResult/studyPlan/";
    String studyPlanDayAbsPath = "/Users/liuhuichao/test/dataResult/studyPlanDay/";
    String studyPlanDayContentAbsPath = "/Users/liuhuichao/test/dataResult/studyPlanDayContent/";

    //对应数据库文件地址
    String finalResult = "/Users/liuhuichao/test/dataResult/finalResult/";

    String OSS_DATA = "https://oss-image.17zuoye.com/";
    String VIDEO_DATA_TEST = "https://17-pmc-test.oss-cn-beijing.aliyuncs.com/";

    String VIDEO_DATA = "https://cdn-17pmc.17zuoye.cn/";
    String COVER_DATA = "https://cdn-17pmc.17zuoye.cn/";

    /**
     * 从原始数据挑选出有用数据
     *
     * @throws Exception
     */
    @Test
    public void testPickUsefulData() throws Exception {
        //学习计划分年级
        generateStudyPlan();
        //生成每天数据
        List<StudyPlanDayData> studyPlanDayData = generateStudyPlanDayData(studyPlanDayUrl);
        exportStudyPlanDayData(studyPlanDayData);
        //生成题目数据
        exportDayContent(studyPlanDayData);
    }

    /**
     * 生成数据库要存储数据
     *
     * @throws Exception
     */
    @Test
    public void testGenerateDbData() throws Exception {
        List<StudyPlanData> studyPlanDataList = new LinkedList<>(); //整个计划
        Map<String, StudyPlanData> studyPlanDataMap = new HashMap<>(); //文件名字:整个计划
        List<StudyPlanDayData> studyPlanDayData = new LinkedList<>(); //每天的
        List<StudyDayContentDetail> studyDayContentDetails = new LinkedList<>(); //详细的题目数据

        ArrayList<String> studyPlanFileUrlList = new ArrayList<>();
        getAllFileAbsPath(studyPlanAbsPath, studyPlanFileUrlList);
        for (String studyPlanPath : studyPlanFileUrlList) {
            String result = readAllFileToText(studyPlanPath);
            StudyPlanData studyPlanData = JSONObject.parseObject(result, StudyPlanData.class);
            studyPlanDataList.add(studyPlanData);
            studyPlanDataMap.put(studyPlanPath, studyPlanData);
        }

        ArrayList<String> studyPlanDayFileUrlList = new ArrayList<>();
        getAllFileAbsPath(studyPlanDayAbsPath, studyPlanDayFileUrlList);
        for (String path : studyPlanDayFileUrlList) {
            String result = readAllFileToText(path);
            StudyPlanDayData dayData = JSONObject.parseObject(result, StudyPlanDayData.class);
            studyPlanDayData.add(dayData);
        }

        ArrayList<String> studyPlanDayContentFileUrlList = new ArrayList<>();
        getAllFileAbsPath(studyPlanDayContentAbsPath, studyPlanDayContentFileUrlList);
        for (String path : studyPlanDayContentFileUrlList) {
            String result = readAllFileToText(path);
            List<StudyDayContentDetail> contentDetail = JSONObject.parseArray(result, StudyDayContentDetail.class);
            studyDayContentDetails.addAll(contentDetail);
        }

        Map<String, String> templateIDIconMap = studyPlanDataList.stream()
                .map(StudyPlanData::getStudyPlanningTasks)
                .flatMap(List::stream)
                .collect(Collectors.toMap(
                        StudyPlanData.StudyPlanningTask::getCourseId,
                        StudyPlanData.StudyPlanningTask::getBrightSquareIcon,
                        (k1, k2) -> k1));

        List<CheckPointSpecialTopic> specialTopics = new LinkedList<>(); //主题数据
        List<CheckPointTemplate> templates = new LinkedList<>(); //模板

        for (String path : studyPlanDataMap.keySet()) {
            StudyPlanData plan = studyPlanDataMap.get(path);
            String source = path.substring(path.lastIndexOf("/"), path.lastIndexOf("."));
            String[] ids = source.split("-");
            int grade = Integer.parseInt(ids[2]);
            //拼接专题数据
            CheckPointSpecialTopic topic = new CheckPointSpecialTopic();
            specialTopics.add(topic);
            topic.setId(plan.getId());
            topic.setSpecialTopicName(plan.getScheduleActivity().getName() + "-" + grade + "年级");
            topic.setHeadImageUrl(OSS_DATA + plan.getScheduleActivityExtensions().get(0).getSuggestBackgroundImg());
            topic.setCoverUrl("https://cdn-17pmc.17zuoye.cn/check_point/crm_template/2020/09/27/111.png"); // todo ： 先写死
            topic.setTitleColor(plan.getScheduleActivityExtensions().get(0).getSuggestTitleColor());
            topic.setTaskNums(plan.getScheduleActivityExtensions().get(0).getRangeCount() == null ? plan.getStudyPlanningTasks().size() : plan.getScheduleActivityExtensions().get(0).getRangeCount());
            String goalWords = plan.getScheduleActivityExtensions().get(0).getTarget();
            goalWords = goalWords.replaceAll("<p\\b[^<>]*>", "");
            goalWords = goalWords.replaceAll("</p>", "");
            goalWords = goalWords.replaceAll("<br/>", "");
            topic.setGoalWords(goalWords);
            String introducedWords = plan.getScheduleActivityExtensions().get(0).getIntroduced();
            introducedWords = introducedWords.replaceAll("<p\\b[^<>]*>", "");
            introducedWords = introducedWords.replaceAll("</p>", "");
            introducedWords = introducedWords.replaceAll("<br/>", "\n");
            topic.setIntroducedWords(introducedWords);
            topic.setSubtitle(plan.getScheduleActivityExtensions().get(0).getSubTitle());
            List<String> templateId = plan.getStudyPlanningTasks().stream().map(StudyPlanData.StudyPlanningTask::getCourseId).collect(Collectors.toList());
            topic.setTemplateIds(templateId);
            topic.setStudyPlanTags(plan.getScheduleActivityRecommendTags().stream().map(StudyPlanData.ScheduleActivityRecommendTags::getId).collect(Collectors.toList()));
        }
        //转换成为数据库实体:专题数据
       /* for (StudyPlanData plan : studyPlanDataList) {
            //拼接专题数据
            CheckPointSpecialTopic topic = new CheckPointSpecialTopic();
            specialTopics.add(topic);
            topic.setId(plan.getId());
            topic.setSpecialTopicName(plan.getScheduleActivity().getName() + "-" + plan.getStudyPlanningTasks().get(0).getGrades().get(0) + "年级");
            topic.setHeadImageUrl(OSS_DATA + plan.getScheduleActivityExtensions().get(0).getSuggestBackgroundImg());
            topic.setCoverUrl("https://cdn-17pmc.17zuoye.cn/check_point/crm_template/2020/09/27/111.png"); // todo ： 先写死
            topic.setTitleColor(plan.getScheduleActivityExtensions().get(0).getSuggestTitleColor());
            topic.setTaskNums(plan.getScheduleActivityExtensions().get(0).getRangeCount() == null ? plan.getStudyPlanningTasks().size() : plan.getScheduleActivityExtensions().get(0).getRangeCount());
            String goalWords = plan.getScheduleActivityExtensions().get(0).getTarget();
            goalWords = goalWords.replaceAll("<p\\b[^<>]*>", "");
            goalWords = goalWords.replaceAll("</p>", "");
            goalWords = goalWords.replaceAll("<br/>", "");
            topic.setGoalWords(goalWords);
            String introducedWords = plan.getScheduleActivityExtensions().get(0).getIntroduced();
            introducedWords = introducedWords.replaceAll("<p\\b[^<>]*>", "");
            introducedWords = introducedWords.replaceAll("</p>", "");
            introducedWords = introducedWords.replaceAll("<br/>", "\n");
            topic.setIntroducedWords(introducedWords);
            topic.setSubtitle(plan.getScheduleActivityExtensions().get(0).getSubTitle());
            List<String> templateId = plan.getStudyPlanningTasks().stream().map(StudyPlanData.StudyPlanningTask::getCourseId).collect(Collectors.toList());
            topic.setTemplateIds(templateId);
            topic.setStudyPlanTags(plan.getScheduleActivityRecommendTags().stream().map(StudyPlanData.ScheduleActivityRecommendTags::getId).collect(Collectors.toList()));
        }
        */

        String outFileName = finalResult + "specialTopic.json";
        File gradeFile = new File(outFileName);
        gradeFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(gradeFile);
        String source = JSONObject.toJSONString(specialTopics);
        fos.write(source.getBytes());
        fos.close();

        //拼接模板数据
        for (StudyPlanDayData planDayData : studyPlanDayData) {
            CheckPointTemplate template = new CheckPointTemplate();
            template.setRepeat(Arrays.asList(-1));
            templates.add(template);
            template.setId(planDayData.getProgramme().getId());
            template.setTemplateName(planDayData.getProgramme().getName());
            String teachingGoals = planDayData.getProgramme().getTarget();
            teachingGoals = teachingGoals.replaceAll("<span\\b[^<>]*>", "");
            teachingGoals = teachingGoals.replaceAll("<p\\b[^<>]*>", "");
            teachingGoals = teachingGoals.replaceAll("</p>", "");
            teachingGoals = teachingGoals.replaceAll("</span>", "");
            teachingGoals = teachingGoals.replaceAll("<br/>", "\n");
            template.setTeachingGoals(teachingGoals);
            String icon = templateIDIconMap.get(planDayData.getProgramme().getId());
            template.setCornerIcon(icon);
            List<CheckPointTemplate.StudyPlan> dayStudyList = new LinkedList<>();
            template.setDayStudyList(dayStudyList);
            //题目数据按照天分钟
            Map<String, List<StudyPlanDayData.Item>> dayIdRefIdMap = planDayData
                    .getItems()
                    .stream()
                    .collect(Collectors.groupingBy(StudyPlanDayData.Item::getDailyId));
            //所有题目
            Set<String> refIds = new HashSet<>(dayIdRefIdMap.values().stream().flatMap(List::stream).map(StudyPlanDayData.Item::getRefId).collect(Collectors.toList()));
            Map<String, StudyDayContentDetail> dayContentDetailMap = studyDayContentDetails.stream().filter(c -> refIds.contains(c.get_id())).collect(Collectors.toMap(StudyDayContentDetail::get_id, Function.identity()));
            Integer minStage = planDayData.getDailies().stream().map(StudyPlanDayData.Daily::getStageIndex).min(Integer::compareTo).orElse(1);
            Integer maxStage = planDayData.getDailies().stream().map(StudyPlanDayData.Daily::getStageIndex).max(Integer::compareTo).orElse(1);
            LinkedHashMap<Integer, List<StudyPlanDayData.Daily>> stageMap = planDayData.getDailies().stream().collect(Collectors.groupingBy(StudyPlanDayData.Daily::getStageIndex, LinkedHashMap::new, Collectors.toList()));
            for (int i = minStage; i <= maxStage; i++) {
                //每天数据按照阶段分
                List<StudyPlanDayData.Daily> stageDaily = stageMap.get(i).stream().sorted(Comparator.comparing(StudyPlanDayData.Daily::getSort)).collect(Collectors.toList()); //获取阶段下的天
                CheckPointTemplate.StudyPlan studyPlan = new CheckPointTemplate.StudyPlan();
                studyPlan.setStageName("阶段" + i + ":" + stageDaily.get(0).getStageDesc());
                List<CheckPointTemplate.StudyPlanDaily> studyPlanDailys = new LinkedList<>(); //阶段每天数据
                studyPlan.setStudyPlanDailys(studyPlanDailys);
                //阶段下有几天
                int dayIndex = 1;
                for (StudyPlanDayData.Daily day : stageDaily) {
                    CheckPointTemplate.StudyPlanDaily target = new CheckPointTemplate.StudyPlanDaily();
                    List<CheckPointTemplate.StudyPlanDailyLearn> studyPlanDailyLearns = new LinkedList<>(); //每天数据
                    target.setStudyPlanDailyLearns(studyPlanDailyLearns);
                    target.setDayTitle("第" + dayIndex++ + "天：" + day.getDesc());
                    target.setDayId(day.getId());
                    List<StudyPlanDayData.Item> dayContent = dayIdRefIdMap.get(day.getId());
                    dayContent = dayContent
                            .stream()
                            .filter(d -> "COMPONENT".equals(d.getType()) || "OFFLINE".equals(d.getType()))
                            .sorted(Comparator.comparing(StudyPlanDayData.Item::getSort))
                            .collect(Collectors.toList());
                    for (StudyPlanDayData.Item itemContent : dayContent) {
                        CheckPointTemplate.StudyPlanDailyLearn learn = new CheckPointTemplate.StudyPlanDailyLearn();
                        if (itemContent.getType().equals("OFFLINE")) {
                            learn.setType("CHECK_POINT_TAKE_PHOTO");
                            learn.setTitle(itemContent.getTitle());
                            learn.setDailyLearnId(itemContent.getId());
                            studyPlanDailyLearns.add(learn);
                            continue;
                        }
                        //来自上单系统的数据
                        StudyDayContentDetail content = dayContentDetailMap.get(itemContent.getRefId());
                        if (content == null) {
                            continue;
                        }
                        studyPlanDailyLearns.add(learn);
                        learn.setDailyLearnId(itemContent.getId());
                        learn.setTitle(content.getName());
                        if (content.getContent().get(0).getChooseContent() != null) {
                            //问题的 question
                            learn.setQuestionIds(content.getContent().get(0).getChooseContent().getQuestionId());
                            learn.setType("CHECK_POINT_EXAM");
                            continue;
                        }
                        if (content.getContent().get(0).getApplicationLinkContent() != null) {
                            //绘本
                            learn.setPictureBookId(content.getContent().get(0).getApplicationLinkContent().getContentId());
                            learn.setType("CHECK_POINT_READING");
                            continue;
                        }
                        if (content.getContent().get(0).getDirectVideoContent() != null) {
                            //直接看的视频
                            learn.setVideoUrl(VIDEO_DATA + content.getContent().get(0).getDirectVideoContent().getVideoUrl());
                            learn.setCoverUrl(COVER_DATA + content.getContent().get(0).getDirectVideoContent().getVideoImgUrl());
                            learn.setDuration(content.getContent().get(0).getDirectVideoContent().getDuration());
                            learn.setType("CHECK_POINT_VIDEO");
                            continue;
                        }
                        if (content.getContent().get(0).getIndirectVideoContent() != null) {
                            //互动视频
                            learn.setVideoId(content.getContent().get(0).getIndirectVideoContent().getVideoId());
                            learn.setType("CHECK_POINT_INDIRECT_VIDEO");
                            continue;
                        }
                        System.out.println(JSONObject.toJSONString(content));
                    }
                    studyPlanDailys.add(target);
                }
                dayStudyList.add(studyPlan);
            }
        }

        String templateFileName = finalResult + "templates.json";
        File templateFile = new File(templateFileName);
        templateFile.createNewFile();
        FileOutputStream templateFileFos = new FileOutputStream(templateFile);
        String templateSource = JSONObject.toJSONString(templates);
        templateFileFos.write(templateSource.getBytes());
        templateFileFos.close();
    }


    /**
     * 获取行数
     *
     * @param path
     * @return
     */
    public int getLineNum(String path) {
        File file = new File(path);
        int count = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) {
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return count;
    }

    public Queue<String> readFileByLines(String path, int starLine, int endLine) {
        Queue<String> queue = new LinkedBlockingQueue<>();
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {
                if (starLine <= line && line < endLine) {
                    queue.add(tempString);
                }
                line++;
                if (line >= endLine) {
                    break;
                }
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return queue;
    }

    public Queue<String> readAllFileByLine(String path) {
        Queue<String> queue = new LinkedBlockingQueue<>();
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                queue.add(tempString.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return queue;
    }

    public String readAllFileToText(String path) {
        StringBuilder sb = new StringBuilder();
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    public void getAllFileAbsPath(String path, Collection<String> fileNameList) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                if (tempList[i].getName().indexOf("json") < 0) {
                    continue;
                }
                fileNameList.add(tempList[i].getAbsolutePath());
            }
        }
    }


    /**
     * 任务数据分年级生成
     *
     * @throws Exception
     */
    public void generateStudyPlan() throws Exception {
        //获取学习计划
        ArrayList<String> studyPlanFileUrlList = new ArrayList<>();
        getAllFileAbsPath(studyPlanUrl, studyPlanFileUrlList);
        List<StudyPlanData> planDataList = new LinkedList<>();
        for (String studyPlanPath : studyPlanFileUrlList) {
            String result = readAllFileToText(studyPlanPath);
            StudyPlanData studyPlanData = JSONObject.parseObject(result, StudyPlanData.class);
            planDataList.add(studyPlanData);
            List<StudyPlanData.StudyPlanningTask> studyPlanningTasks = studyPlanData.getStudyPlanningTasks();
            List<StudyPlanData.ScheduleActivityTaskRefs> scheduleActivityTaskRefs = studyPlanData.getScheduleActivityTaskRefs();
            //将refs里面的排序值赋值给task里面
            for (StudyPlanData.ScheduleActivityTaskRefs ref : scheduleActivityTaskRefs) {
                StudyPlanData.StudyPlanningTask itemTask = studyPlanningTasks.stream().filter(task -> task.getId().equals(ref.getTaskId())).findFirst().orElse(null);
                itemTask.setSortIndex(ref.getSort());
            }
            for (Integer grade : studyPlanData.getScheduleActivity().getGrades()) {
                String outFileName = studyPlanAbsPath + studyPlanData.getScheduleActivity().getName() + "-" + studyPlanData.getScheduleActivity().getId() + "-" + grade + ".json";
                List<StudyPlanData.StudyPlanningTask> gradeTask = studyPlanningTasks
                        .stream()
                        .filter(task -> task.getGrades().contains(grade))
                        .sorted(Comparator.comparing(StudyPlanData.StudyPlanningTask::getSortIndex))
                        .collect(Collectors.toList());
                studyPlanData.setStudyPlanningTasks(gradeTask);
                File gradeFile = new File(outFileName);
                gradeFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(gradeFile);
                studyPlanData.setId(studyPlanData.getScheduleActivity().getId() + "-" + grade);
                String source = JSONObject.toJSONString(studyPlanData);
                fos.write(source.getBytes());
                fos.close();
            }
        }

        //提取标签数据
        List<CheckPointLabel> labels = new LinkedList<>();
        List<StudyPlanData.ScheduleActivityRecommendTags> tags = planDataList.stream().map(StudyPlanData::getScheduleActivityRecommendTags).flatMap(List::stream).collect(Collectors.toList());
        for (StudyPlanData.ScheduleActivityRecommendTags tag : tags) {
            CheckPointLabel label = new CheckPointLabel();
            label.set_id(tag.getId());
            label.setLabelName(tag.getTitle());
            label.setLabelBackGround(tag.getBackgroundColor());
            label.setLabelNameColor(tag.getTitleColor());
            labels.add(label);
        }
        File labelFile = new File(finalResult + "label.json");
        labelFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(labelFile);
        String source = JSONObject.toJSONString(labels);
        fos.write(source.getBytes());
        fos.close();
    }

    /**
     * 生成每天的学习计划
     *
     * @param studyPlanDayAbsPath
     * @return
     */
    public List<StudyPlanDayData> generateStudyPlanDayData(String studyPlanDayAbsPath) {
        LinkedList<String> allStudyPlanDayPath = new LinkedList<>();
        getAllFileAbsPath(studyPlanDayAbsPath, allStudyPlanDayPath);
        List<StudyPlanDayData> result = new LinkedList<>();
        for (String path : allStudyPlanDayPath) {
            String resultText = readAllFileToText(path);
            StudyPlanDayData studyPlanDayData = JSONObject.parseObject(resultText, StudyPlanDayData.class);
            result.add(studyPlanDayData);
        }
        return result;
    }

    /**
     * 导出每天学习计划
     *
     * @param studyPlanDayData
     * @throws Exception
     */
    public void exportStudyPlanDayData(List<StudyPlanDayData> studyPlanDayData) throws Exception {
        for (StudyPlanDayData planDay : studyPlanDayData) {
            String outFileName = studyPlanDayAbsPath + planDay.getProgramme().getName() + "-" + planDay.getProgramme().getId() + ".json";
            File gradeFile = new File(outFileName);
            gradeFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(gradeFile);
            String source = JSONObject.toJSONString(planDay);
            fos.write(source.getBytes());
            fos.close();
        }
    }


    /**
     * 题目数据
     *
     * @throws Exception
     */
    public void exportDayContent(List<StudyPlanDayData> studyPlanDayData) throws Exception {
        //生成 refIds file
        Set<String> refIds = new HashSet<>();
        for (StudyPlanDayData dayData : studyPlanDayData) {
            List<StudyPlanDayData.Item> items = dayData.getItems();
            for (StudyPlanDayData.Item item : items) {
                if (item.getRefId() == null) {
                    continue;
                }
                refIds.add(item.getRefId());
            }
        }
        List<String> noContent = new LinkedList<>();
        List<StudyDayContentDetail> filterEdDayContentData = new LinkedList<>(); //输出文件
        String allCircleData = readAllFileToText(circleFileUrl);
        List<StudyDayContentDetail> studyDayContentDetails = new LinkedList<>();
        studyDayContentDetails = JSONObject.parseArray(allCircleData, StudyDayContentDetail.class);
        System.out.println("共读取到--------" + studyDayContentDetails.size());
        for (StudyDayContentDetail contentDetail : studyDayContentDetails) {
            if (!refIds.contains(contentDetail.get_id())) {
                continue;
            }
            //过滤题目数据
            if (contentDetail.getContent().get(0).getApplicationLinkContent() == null
                    && contentDetail.getContent().get(0).getChooseContent() == null
                    && contentDetail.getContent().get(0).getDirectVideoContent() == null
                    && contentDetail.getContent().get(0).getIndirectVideoContent() == null) {
                noContent.add(contentDetail.get_id());
                continue;
            }
            System.out.println("已添加到目标----" + filterEdDayContentData.size());
            filterEdDayContentData.add(contentDetail);
        }

        System.out.println("开始写入目标----" + filterEdDayContentData.size());
        String outFileName = studyPlanDayContentAbsPath + "dayContent.json";
        File gradeFile = new File(outFileName);
        gradeFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(gradeFile);
        String source = JSONObject.toJSONString(filterEdDayContentData);
        fos.write(source.getBytes());
        fos.close();

/*
        String outFileName = refIdsUrl + "refIds.json";
        File gradeFile = new File(outFileName);
        gradeFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(gradeFile);
        String source = JSONObject.toJSONString(refIds);
        fos.write(source.getBytes());
        fos.close();*/

    }
}
