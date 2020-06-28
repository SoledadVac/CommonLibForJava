package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/28
 * \* Time: 36:10 下午
 * \* Description:转置矩阵
 * \
 */
public class A867_TransposeMatrix {

    public int[][] transpose(int[][] A) {
        int[][] result = new int[A[0].length][A.length];
        for (int j = 0; j < A[0].length; j++) {
            int[] item = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                item[i] = A[i][j];
            }
            result[j] = item;
        }
        return result;
    }

    @Test
    public void test() {
        int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //int[][] A = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(JSONObject.toJSONString(transpose(A)));

    }
}
