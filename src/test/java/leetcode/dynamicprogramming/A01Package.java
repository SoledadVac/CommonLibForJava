package leetcode.dynamicprogramming;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/2
 * \* Time: 28:36 PM
 * \* Description: 01 package
 * \
 */
public class A01Package {

    public Integer getMaxPackageValue(Integer[] vList, Integer[] wList, Integer W) {
        //构造子问题题解表
        int[][] table = new int[W + 1][vList.length + 1];
        for (int vIndex = 0; vIndex <= vList.length; vIndex++) {
            for (int w = 0; w <= W; w++) {
                if (w == 0 || vIndex == 0) {
                    table[w][vIndex] = 0;
                    continue;
                }
                if (wList[vIndex - 1] > w) {
                    table[w][vIndex] = table[w][vIndex - 1];
                } else {
                    Integer v1 = vList[vIndex - 1] + table[w - wList[vIndex - 1]][vIndex - 1];
                    Integer v2 = table[w][vIndex - 1];
                    table[w][vIndex] = Math.max(v1, v2);
                }
            }
        }
        return table[W][vList.length];
    }

    @Test
    public void test() {
        Integer[] vList = {4, 5, 10, 11, 13};
        Integer[] wList = {3, 4, 7, 8, 9};
        Integer W = 17;//背包限重
        System.out.println(JSONObject.toJSONString(getMaxPackageValue(vList, wList, W)));
    }
}
