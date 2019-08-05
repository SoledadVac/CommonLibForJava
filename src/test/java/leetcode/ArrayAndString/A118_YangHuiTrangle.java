package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/22
 * \* Time: 5:09 PM
 * \* Description: 杨辉三角
 * <p>
 * 题目描述：给定一个非负整数  numRows行，生成杨辉三角的前  numRows行 行。
 * \
 */
public class A118_YangHuiTrangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows < 1) {
            return result;
        }
        if (numRows == 1) {
            List<Integer> item1 = new ArrayList<>();
            item1.add(1);
            result.add(item1);
            return result;
        }
        result.addAll(generate(numRows - 1));
        if (numRows == 2) {
            List<Integer> item1 = new ArrayList<>();
            item1.add(1);
            item1.add(1);
            result.add(item1);
            return result;
        }
        List<Integer> itemNPre = result.get(numRows - 2); //倒数第二行
        // >=3  :求解第numRows行
        List<Integer> itemN = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                itemN.add(1);
                continue;
            }
            if (i == numRows - 1) {
                itemN.add(1);
                continue;
            }
            itemN.add(itemNPre.get(i - 1) + itemNPre.get(i));
        }
        result.add(itemN);
        return result;
    }

    @Test
    public void test() {
        List<List<Integer>> result = generate(6);
        System.out.println(JSONObject.toJSONString(result));
    }
}
