package leetcode.ArrayAndString;

import com.google.inject.internal.cglib.reflect.$FastClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/30
 * \* Time: 29:38 下午
 * \* Description:矩阵中的幻方
 * \
 * 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
 * <p>
 * 给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>输入：
 * [[4,3,8,4],
 * [9,5,1,9],
 * [2,7,6,2]]
 * 输出: 1
 * 解释:
 * 下面的子矩阵是一个 3 x 3 的幻方：
 * 438
 * 951
 * 276
 * <p>
 * 而这一个不是：
 * 384
 * 519
 * 762
 * <p>
 * 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
 * 提示:
 * <p>
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * 0 <= grid[i][j] <= 15
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/magic-squares-in-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A840_MagicSquaresInGrid {

    public int numMagicSquaresInside(int[][] grid) {
        int sum = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (x + 2 > grid.length - 1) {
                    continue;
                }
                if (y + 2 > grid[0].length - 1) {
                    continue;
                }
                if (isMagic(grid, x, y)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public boolean isMagic(int[][] grid, int x, int y) {
        //判断中心是否为5
        if (grid[x + 1][y + 1] != 5) {
            return false;
        }
        int sum = 15;
        Set<Integer> data = new HashSet<>();
        //横行判断
        for (int i = x; i <= x + 2; i++) {
            int temp = 0;
            for (int j = y; j <= y + 2; j++) {
                if (grid[i][j] <= 0 || grid[i][j] >= 10 || data.contains(grid[i][j])) {
                    return false;
                } else {
                    data.add(grid[i][j]);
                }
                temp += grid[i][j];
            }
            if (temp != sum) {
                return false;
            }
        }
        //竖行判断
        for (int j = y; j <= y + 2; j++) {
            int temp = 0;
            for (int i = x; i <= x + 2; i++) {
                temp += grid[i][j];
            }
            if (temp != sum) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void test() {
        //int[][] grid = {{4, 3, 8, 4}, {9, 5, 1, 9}, {2, 7, 6, 2}};
        int[][] grid = {{1, 8, 6}, {10, 5, 0}, {4, 2, 9}};
        System.out.println(numMagicSquaresInside(grid));
    }
}
