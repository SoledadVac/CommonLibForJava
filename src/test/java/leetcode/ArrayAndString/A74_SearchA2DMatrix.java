package leetcode.ArrayAndString;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/30
 * \* Time: 31:47 下午
 * \* Description: 搜索二维矩阵
 * \
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A74_SearchA2DMatrix {


    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length; //行数
        int n = matrix[0].length;//列数
        int leftM = 0;
        int rightM = m - 1;
        while (leftM <= rightM) {
            int midM = (rightM - leftM) / 2 + leftM;
            int leftN = 0;
            int rightN = n - 1;
            while (leftN < rightN) {
                int midN = (rightN - leftN) / 2 + leftN;
                if (matrix[midM][midN] == target) {
                    return true;
                }
                if (matrix[midM][midN] > target) {
                    //向前搜索
                    rightN = midN;
                }
                if (matrix[midM][midN] < target) {
                    //向后搜索
                    leftN = midN + 1;
                }
            }
            if (leftN != n && matrix[midM][leftN] == target) return true;
            if (leftN == n) {
                leftM = midM + 1;
            }
        }
        return false;
    }

    @Test
    public void test() {
        //int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 13;
        System.out.println(searchMatrix(matrix, target));
    }


}
