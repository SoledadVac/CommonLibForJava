package com.common.lib.demo.dataprocess;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/9/7
 * \* Time: 下午12:13
 * \* Description:
 * \
 */
public class MapProcess {

    /**
     * 按照值排序map
     */
    @Test
    public void sortMapByValue(){
        Map<String, Date> data = new LinkedHashMap<>();
        data.put("a",new Date("Sat, 12 Aug 1995 13:30:00 GMT"));
        data.put("b",new Date("Sat, 14 Aug 1995 13:30:00 GMT"));
        data.put("c",new Date("Sat, 10 Aug 1995 13:30:00 GMT"));
        data.put("cd",new Date("Sat, 10 Aug 1995 13:35:00 GMT"));
        Map<String, Date> result = new LinkedHashMap<>();
        data.entrySet().stream()
                .sorted(Map.Entry.<String, Date>comparingByValue()
                        .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 展开操作
     */
    @Test
    public void flatMap(){
        List<String> l1=Arrays.asList("1","22","33333","ff");
        List<String> l2=Arrays.asList("1e","e","fffff","ww");
        List<String>  result= Stream.of(l1,l2).flatMap(l->l.stream()).collect(Collectors.toList());
        System.out.println(result);
    }

}
