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
    private List<String> hospitalCodeList = Lists.newArrayList("234", "001");
    private String folderPath = "/Users/liuhuichao/JavaProj/1Yong/CDMTableList";
    private String folderTableSummaryPath = "/Users/liuhuichao/JavaProj/1Yong/CDMTableList/表单目录.xlsx";


    @Test
    public void test() throws Exception {
        //System.out.println(JSONObject.toJSONString(excelUtil.readExcelToObj(folderTableSummaryPath)));
        //输出表单目录insert sql
        // System.out.println(getTableInfo().stream().collect(Collectors.joining(";")));
        //System.out.println(JSONObject.toJSONString(getAllFilePath()));
        System.out.println(getTableInfoDetail().stream().collect(Collectors.joining(";")));
        ;
    }

    /**
     * 获取表数据
     *
     * @return
     */
    public List<String> getTableInfo() {
        List<String> result = new ArrayList<>();
        List<String> targetNames = new ArrayList<>();
        targetNames.add("hospital_code");
        targetNames.add("table_category_code");
        targetNames.add("table_category_name");
        targetNames.add("table_code");
        targetNames.add("table_name");
        targetNames.add("source_system");
        targetNames.add("in_use");
        targetNames.add("remark");
        ArrayList<Map<String, String>> excelList = excelUtil.readExcelToObj(folderTableSummaryPath);
        for (String hospitalCode : hospitalCodeList) {
            for (Map<String, String> lineData : excelList) {
                List<String> values = Lists.newArrayList();
                values.add(hospitalCode);//hospital_code
                values.add(lineData.get("0"));//table_category_code
                values.add(lineData.get("1"));//table_category_name
                values.add(lineData.get("2")); //table_code
                values.add(lineData.get("3")); //table_name
                values.add(lineData.get("4").replace("\\", "\\\\")); //source_system
                values.add("1"); //in_use
                values.add(lineData.get("6")); //remark
                String makeInsert = makeInsertToTableSql("yydi_gateway.table_dict", targetNames, values);
                result.add(makeInsert);
            }
        }
        return result;
    }


    /**
     * 获取表详细的数据
     *
     * @return
     */
    public List<String> getTableInfoDetails(String tableCode, String path) {
        List<String> result = new ArrayList<>();
        List<String> targetNames = new ArrayList<>();
        targetNames.add("`table_code`");
        targetNames.add("`field`");
        targetNames.add("`comment`");
        targetNames.add("`example`");
        targetNames.add("`explain`");
        targetNames.add("`type`");
        targetNames.add("`type_define`");
        targetNames.add("`required`");
        ArrayList<Map<String, String>> excelList = excelUtil.readExcelToObj(path);
        for (Map<String, String> lineData : excelList) {
            List<String> values = Lists.newArrayList();
            values.add(tableCode);//table_code
            if (StringUtils.isEmpty(lineData.get("0"))) {
                continue;
            }
            values.add(lineData.get("0"));//field
            values.add(lineData.get("1"));//comment
            values.add(lineData.get("2"));//example
            values.add(lineData.get("3"));//explain
            values.add(lineData.get("4"));//type
            values.add(lineData.get("5"));//type_define
            String reqStr = lineData.get("6");
            if (StringUtils.isEmpty(reqStr)) {
                reqStr = "";
            }
            values.add(reqStr.contains("是") ? "1" : "0");//required
            String makeInsert = makeInsertToTableSql("yydi_gateway.table_dict_detail", targetNames, values);
            result.add(makeInsert);
        }
        return result;
    }


    /**
     * 获取表的详细数据
     *
     * @return
     */
    public List<String> getTableInfoDetail() {
        List<String> result = new LinkedList<>(); // key : tableCode; value : sql
        Map<String, String> codePathMap = getAllFilePath();
        for (String tableCode : codePathMap.keySet()) {
            List<String> tableSql = getTableInfoDetails(tableCode, codePathMap.get(tableCode));
            result.addAll(tableSql);
        }
        return result;
    }


    public Map<String, String> getAllFilePath() {
        Map<String, String> result = new HashMap<>(); // // key : tableCode; value : abs path
        File parentFolder = new File(folderPath);
        File[] files = parentFolder.listFiles();
        for (File file : files) {
            if (file.getName().contains("表单目录")) {
                continue;
            }
            String tableCode = file.getName().substring(0, file.getName().indexOf("."));
            if (StringUtils.isEmpty(tableCode)) {
                continue;
            }
            result.put(tableCode, file.getAbsolutePath());
        }
        return result;
    }

    public static String makeInsertToTableSql(String tableName, Collection<String> names, List<String> values) {
        StringBuilder sql = (new StringBuilder()).append("insert into ").append(tableName).append("(");
        int nameCount = 0;

        for (Iterator var4 = names.iterator(); var4.hasNext(); ++nameCount) {
            String name = (String) var4.next();
            if (nameCount > 0) {
                sql.append(",");
            }

            sql.append(name);
        }

        sql.append(") values (");

        for (int i = 0; i < nameCount; ++i) {
            if (i != 0) {
                sql.append(",");
            }

            sql.append("'" + values.get(i) + "'");
        }

        sql.append(")");
        return sql.toString();
    }


}
