package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/29
 * \* Time: 35:57 上午
 * \* Description: 图片平滑器
 * \
 * 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [[1,1,1],
 * [1,0,1],
 * [1,1,1]]
 * 输出:
 * [[0, 0, 0],
 * [0, 0, 0],
 * [0, 0, 0]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 * 注意:
 * <p>
 * 给定矩阵中的整数范围为 [0, 255]。
 * 矩阵的长和宽的范围均为 [1, 150]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/image-smoother
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A661_ImageSmoother {
    public int[][] imageSmoother(int[][] M) {
        int[][] result = new int[M.length][M[0].length];
        for (int x = 0; x < M.length; x++) {
            for (int y = 0; y < M[0].length; y++) {
                result[x][y] = getTotalValue(M, x, y);
            }
        }
        return result;
    }

    int getTotalValue(int[][] M, int x, int y) {
        int xLength = M.length;
        int yLength = M[0].length;
        int total = M[x][y];
        int num = 1;
        if (x - 1 >= 0) {
            //上面的值
            total += M[x - 1][y];
            num++;
        }
        if (x + 1 < xLength) {
            //下面的值
            total += M[x + 1][y];
            num++;
        }
        if (y - 1 >= 0) {
            //左边
            total += M[x][y - 1];
            num++;
        }
        if (y + 1 < yLength) {
            //右边
            total += M[x][y + 1];
            num++;
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            //左上
            total += M[x - 1][y - 1];
            num++;
        }
        if (x - 1 >= 0 && y + 1 < yLength) {
            //右上
            total += M[x - 1][y + 1];
            num++;
        }
        if (x + 1 < xLength && y - 1 >= 0) {
            //左下
            total += M[x + 1][y - 1];
            num++;
        }
        if (x + 1 < xLength && y + 1 < yLength) {
            //右下
            total += M[x + 1][y + 1];
            num++;
        }
        return total / num;
    }

    @Test
    public void test() {
        // int[][] M = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] M = {{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}, {14, 15, 16}};
        System.out.println(JSONObject.toJSON(imageSmoother(M)));
    }


}
