package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/24
 * \* Time: 4:19 PM
 * \* Description: 杨辉三角
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * <p>
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 * \
 */
public class A119_YangHuiTrangleII {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex < 0) {
            return result;
        }
        if (rowIndex == 0) {
            result.add(1);
            return result;
        }
        if (rowIndex == 1) {
            result.add(1);
            result.add(1);
            return result;
        }
        List<Integer> preResult = getRow(rowIndex - 1);
        for (int i = 0; i < rowIndex + 1; i++) {
            if (i == 0) {
                result.add(1);
                continue;
            }
            if (i == rowIndex) {
                result.add(1);
                continue;
            }
            result.add(preResult.get(i - 1) + preResult.get(i));
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(JSONObject.toJSONString(getRow(3)));
    }

}
