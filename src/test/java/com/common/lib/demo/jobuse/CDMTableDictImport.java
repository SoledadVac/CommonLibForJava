package com.common.lib.demo.jobuse;

import com.alibaba.druid.util.JdbcUtils;
import com.alibaba.fastjson.JSONObject;
import com.common.lib.demo.io.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;


public class CDMTableDictImport {

    ExcelUtil excelUtil = new ExcelUtil();
    private String folderTableSummaryPath = "/Users/liuhuichao/Downloads/字段字典说明(一院多区)-20220729.xlsx";


    @Test
    public void test() throws Exception {
        //System.out.println(JSONObject.toJSONString(excelUtil.readExcelToObj(folderTableSummaryPath)));
        //输出表单目录insert sql
        // System.out.println(getTableInfo().stream().collect(Collectors.joining(";")));
        //System.out.println(JSONObject.toJSONString(getAllFilePath()));
        Map<String, ArrayList<Map<String, String>>> resultMap = excelUtil.readAllSheetExcelToObj(folderTableSummaryPath);
        //表单目录
        List<String> tableListSql = new LinkedList<>();
        ArrayList<Map<String, String>> tableList = resultMap.get("表单目录");
        for (Map<String, String> item : tableList) {
            tableListSql.add(buildTableInfoInsertSql(item));
        }
        //表单数据
        List<String> tableDetailListSql = new LinkedList<>();
        for (String sheetName : resultMap.keySet()) {
            if ("表单目录".equals(sheetName)) {
                continue;
            }
            String tableCode = sheetName;
            ArrayList<Map<String, String>> tableDetailList = resultMap.get(tableCode);
            for (Map<String, String> item : tableDetailList) {
                if (StringUtils.isEmpty(item.get("0"))) {
                    continue;
                }
                tableDetailListSql.add(buildTableDetailInsertSql(tableCode, item));
            }
        }
        //System.out.println(tableListSql.stream().collect(Collectors.joining(";")));
        //System.out.println(tableDetailListSql.stream().collect(Collectors.joining(";")));
        List<String> result = new LinkedList<>();
        //result.addAll(tableListSql);
        result.addAll(tableDetailListSql);
        String insertSql = result.stream().collect(Collectors.joining(";"));
        writeToFile(
                insertSql, "/Users/liuhuichao/Downloads/insertSql.txt"
        );


    }

    public void writeToFile(String str, String path) {
        FileWriter writer;
        try {
            writer = new FileWriter(path);
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String buildTableInfoInsertSql(Map<String, String> item) {
        String sql = "";
        List<String> targetNames = new ArrayList<>();
        targetNames.add("table_category_code");
        targetNames.add("table_category_name");
        targetNames.add("field_num");
        targetNames.add("source_system");
        targetNames.add("source_info");
        List<String> values = Lists.newArrayList();
        values.add(item.get("2"));
        values.add(item.get("3"));
        values.add(item.get("4"));
        values.add(item.get("5"));
        values.add(item.get("6"));
        sql = makeInsertToTableSql("yydi_gateway.table_dict", targetNames, values);
        return sql;
    }

    public String buildTableDetailInsertSql(String tableCode, Map<String, String> item) {
        String sql = "";
        List<String> targetNames = new ArrayList<>();
        targetNames.add("table_code");
        targetNames.add("field_name");
        targetNames.add("field_cn_name");
        targetNames.add("example");
        targetNames.add("comment");
        targetNames.add("ref_dict");
        targetNames.add("dict_source");
        targetNames.add("type_define");
        targetNames.add("pk_mark");
        targetNames.add("required");
        List<String> values = Lists.newArrayList();
        values.add(tableCode);
        values.add(item.get("0"));
        values.add(item.get("1"));
        values.add(item.get("2"));
        values.add(item.get("3"));
        values.add(item.get("4"));
        values.add(item.get("5"));
        values.add(item.get("6"));
        values.add(item.get("7"));
        values.add(item.get("8"));
        values.add(item.get("9"));
        sql = makeInsertToTableSql("yydi_gateway.table_dict_detail", targetNames, values);
        return sql;
    }


    public static String makeInsertToTableSql(String tableName, Collection<String> names, List<String> values) {
        StringBuilder sql = (new StringBuilder()).append("insert into ").append(tableName).append("(");
        int nameCount = 0;

        for (Iterator var4 = names.iterator(); var4.hasNext(); ++nameCount) {
            String name = (String) var4.next();
            if (nameCount > 0) {
                sql.append(",");
            }
            sql.append("`");
            sql.append(name);
            sql.append("`");
        }

        sql.append(") values (");

        for (int i = 0; i < nameCount; ++i) {
            if (i != 0) {
                sql.append(",");
            }
            String v = values.get(i);
            if (StringUtils.isEmpty(v)) {
                v = "";
            }
            v = v.replaceAll("'", "\\\\'");
            sql.append("'" + v.replaceAll("/n|/t", "") + "'");
        }

        sql.append(")");
        return sql.toString();
    }


}
