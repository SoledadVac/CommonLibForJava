package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/7/2
 * \* Time: 28:57 下午
 * \* Description:  零矩阵
 * \
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * 输出：
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * 示例 2：
 * <p>
 * 输入：
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * 输出：
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zero-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M_01_08_Zero_Matrix_LCCI {

    public void setZeroes(int[][] matrix) {
        Set<Integer> xSet = new HashSet<>();//行号
        Set<Integer> ySet = new HashSet<>();//列号
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                if (matrix[x][y] == 0) {
                    xSet.add(x);
                    ySet.add(y);
                }
            }
        }
        for (Integer x : xSet) {
            for (int y = 0; y < matrix[0].length; y++) {
                matrix[x][y] = 0;
            }
        }
        for (Integer y : ySet) {
            for (int x = 0; x < matrix.length; x++) {
                matrix[x][y] = 0;
            }
        }
    }


    @Test
    public void test() {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(matrix);
        System.out.println(JSONObject.toJSONString(matrix));
    }
}
