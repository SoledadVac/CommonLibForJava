package com.common.lib.demo.test.twoonecentury;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.common.lib.demo.test.twoonecentury.api.constant.APIConstant;
import com.common.lib.demo.test.twoonecentury.api.model.common.Book;
import com.common.lib.demo.test.twoonecentury.api.model.common.Chapter;
import com.common.lib.demo.test.twoonecentury.api.model.common.Subject;
import com.common.lib.demo.test.twoonecentury.api.model.common.Version;
import com.common.lib.demo.test.twoonecentury.api.service.APIDocumentService;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/24
 * \* Time: 6:12 PM
 * \* Description: 导数据test
 * \
 */
public class TwoOneCentury {

    APIDocumentService service = new APIDocumentService();


    @Test
    public void getSubject() throws Exception {
        //chapter序列号过滤器
        PropertyFilter filter = new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object value) {
                if ("id".equals(name)) {
                    return true;
                }
                if ("name".equals(name)) {
                    return true;
                }
                if ("stage".equals(name)) {
                    return true;
                }
                if ("subjectId".equals(name)) {
                    return true;
                }
                if ("upid".equals(name)) {
                    return true;
                }
                if ("childs".equals(name)) {
                    return true;
                }
                if ("parentpath".equals(name)) {
                    return true;
                }
                return false;
            }
        };

        // 获取小学教材科目(2:语文；3：数学；4：英语；9：政治思品(道德与法治))
        List<Subject> subjects = service.getSubjects(APIConstant.STAGE_XIAOXUE);
        subjects = subjects.stream().filter(s -> Lists.newArrayList(2, 3, 4, 9).contains(s.getSubjectId())).collect(Collectors.toList());
        Map<Integer, Subject> subjectMap = subjects.stream().collect(Collectors.toMap(Subject::getSubjectId, Function.identity()));
        //获取教程版本
        Map<String, List<Version>> subVerMap = new HashMap<>();
        for (Subject subject : subjects) {
            List<Version> versions = service.getVersions(APIConstant.STAGE_XIAOXUE, subject.getSubjectId());
            List<Integer> verIdList = new ArrayList<>();
            // 2: 语文 :  人教部编版
            // 3：数学；: 人教、北师、苏教
            // 4：英语； : 人教（PEP、新起点、精通）
            // 9：政治思品(道德与法治) : 人教部编
            if (subject.getSubjectId() == 2) {
                // verId : 141764
                verIdList = Lists.newArrayList(141764);
            }
            if (subject.getSubjectId() == 3) {
                verIdList = Lists.newArrayList(11740, 23313, 25571);
            }
            if (subject.getSubjectId() == 4) {
               verIdList = Lists.newArrayList(10427, 58236, 103780);
            }
            if (subject.getSubjectId() == 9) {
                verIdList = Lists.newArrayList(141762);
            }
            List<Version> usedVer = new ArrayList<>();
            for (Version ver : versions) {
                if (verIdList.contains(ver.getVersionId())) {
                    usedVer.add(ver);
                }
            }
            subVerMap.put(subject.getSubjectId().toString(), usedVer);
        }

        //获取所有book 信息
        List<Version> allVers = subVerMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
        Map<Integer, Version> versionMap = allVers.stream().collect(Collectors.toMap(Version::getVersionId, Function.identity()));
        List<Book> allBooks = new ArrayList<>();
        Map<Integer, Version> verBookMap = new HashMap<>(); //教程列表数据导出结构
        Map<Integer, List<Book>> verIdBookMap = new HashMap<>();
        for (Version v : allVers) {
            List<Book> books = service.getBooks(v.getVersionId());
            verIdBookMap.put(v.getVersionId(), books);
            List<Map<String, Object>> bookResultMap = new ArrayList<>();
            allBooks.addAll(books);
            List<Chapter> chaptersByVer = new ArrayList<>();
            for (Book b : books) {
                verBookMap.put(b.getBookId(), v);
                //导出教程列表数据
                /*Map<String, Object> bResult = new HashMap<>();
                bResult.put("bookId", b.getBookId());
                bResult.put("bookName", b.getBookName());
                bookResultMap.add(bResult);*/
                List<Chapter> chapters = service.getChapters(b.getBookId());
                chaptersByVer.addAll(chapters);
            }
            //导出教程列表数据代码
            /*String filePath = "/Users/liuhuichao/Downloads/" + subjectMap.get(v.getSubjectId()).getSubjectName() + "-" + v.getVersionName() + ".txt";
            FileOutputStream fos = new FileOutputStream(filePath);
            String source = JSONObject.toJSONString(bookResultMap);
            fos.write(source.getBytes());
            fos.close();*/

            //导出教程book数据代码
            String filePath = "/Users/liuhuichao/Downloads/" + subjectMap.get(v.getSubjectId()).getSubjectName() + "-" + v.getVersionName() + ".txt";
            FileOutputStream fos = new FileOutputStream(filePath);
            String source = JSONObject.toJSONString(chaptersByVer,filter);
            fos.write(source.getBytes());
            fos.close();
        }



        /*Map<Integer, Book> bookMap = allBooks.stream().collect(Collectors.toMap(Book::getBookId, Function.identity()));
        Map<Integer, List<Chapter>> bookChap = new HashMap<>();
        for (Book b : allBooks) {
            List<Chapter> chapters = service.getChapters(b.getBookId());
            bookChap.put(b.getBookId(), chapters);
        }

        List<Map<String, Object>> sourceMap = new ArrayList<>();
        for (Integer key : bookChap.keySet()) {
            List<Chapter> chapters = bookChap.get(key);
            Map<String, Object> data = new HashMap<>();
            data.put("bookId", key);
            data.put("bookName", bookMap.get(key).getBookName());
            data.put("versionId", verBookMap.get(key).getVersionId());
            data.put("versionName", verBookMap.get(key).getVersionName());
            data.put("chapters", chapters);
            data.put("subjectId", chapters.get(0).getSubjectId());
            data.put("subjectName", subjectMap.get(chapters.get(0).getSubjectId()).getSubjectName());
            sourceMap.add(data);
        }

        String filePath = "/Users/liuhuichao/Downloads/" + System.currentTimeMillis() + ".txt";
        FileOutputStream fos = new FileOutputStream(filePath);
        String source = JSONObject.toJSONString(sourceMap);
        fos.write(source.getBytes());
        fos.close();*/
    }


}
