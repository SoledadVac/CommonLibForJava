package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/28
 * \* Time: 5:24 PM
 * \* Description: 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * \
 * 输入：
 * [
 * [ 1(0,0),  2(0,1),   3(0,2),  4(0,3),  5(0,4)],
 * [ 16(1,0), 17(1,1),  18(1,2), 19(1,3), 6(1,4)],
 * [ 15(2,0), 24(2,1),  25(2,2), 20(2,3), 7(2,4)],
 * [ 14(3,0), 23(3,1),  22(3,2), 21(3,3), 8(3,4)]
 * [ 13(4,0), 12(4,1),  11(4,2), 10(4,3), 9(4,4)]
 * ]
 * <p>
 * <p>
 * 输入：
 * [
 * [1（0，0）,  2（0，1）,   3（0，2）,  4（0，3）],
 * [10（1，0）, 11（1，1）, 12（1，2）, 5（1，3）],
 * [9（2，0）,  8（2，1）,   7（2，2）,  6（2，3）]
 * ]
 */
public class A54_SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        /**
         举个例子自己从头到尾把数字列出来，很容易就找到规律了：
         假设一维数组的坐标为x，取值范围是xMin~xMax；二维数组的坐标为y，取值范围是yMin~yMax。（也就是数组表示为int[y][x]）
         1. 从左到右，y=yMin，x: xMin->xMax，yMin++
         2. 从上到下，x=xMax，y: yMin->yMax，xMax--
         3. 从右到左，y=yMax，x: xMax->xMin，yMax--
         4. 从下到上，x=xMin，y: yMax->uMin，xMin++
         结束条件，xMin==xMax或者yMin==yMax
         **/
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }
        int xMin = 0;
        int yMin = 0;
        int xMax = matrix[0].length - 1;
        int yMax = matrix.length - 1;
        int i = 0, j = 0;
        result.add(matrix[j][i]);
        while (true) {
            while (i < xMax) result.add(matrix[j][++i]);
            if (++yMin > yMax) break;

            while (j < yMax) result.add(matrix[++j][i]);
            if (xMin > --xMax) break;

            while (i > xMin) result.add(matrix[j][--i]);
            if (yMin > --yMax) break;

            while (j > yMin) result.add(matrix[--j][i]);
            if (++xMin > xMax) break;
        }
        return result;
    }

    @Test
    public void test() {
        // TODO:螺旋矩阵问题
        int[][] matrix = {{1, 2, 3, 4}, {10, 11, 12, 5}, {9, 8, 7, 6}};
        System.out.println(JSONObject.toJSONString(spiralOrder(matrix)));
    }

}
