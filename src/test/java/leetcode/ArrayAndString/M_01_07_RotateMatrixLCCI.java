package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/7/2
 * \* Time: 28:01 下午
 * \* Description: 旋转矩阵
 * \
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * <p>
 * 不占用额外内存空间能否做到？
 */
public class M_01_07_RotateMatrixLCCI {


    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return;
        for(int i = 0; i < Math.ceil((double)matrix.length/2d); ++i){
            for(int j = 0; j < matrix.length/2; ++j){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i] = matrix[matrix.length-1-i][matrix.length-1-j];
                matrix[matrix.length-1-i][matrix.length-1-j] = matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i] = tmp;
            }
        }
    }

    @Test
    public void test() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(JSONObject.toJSONString(matrix));
    }
}
